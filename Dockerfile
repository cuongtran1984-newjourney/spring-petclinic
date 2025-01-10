FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=tools -jar app.jar extract --layers --launcher

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy extracted layers
ARG UID=10001
RUN adduser \
    --disabled-password \
    --gecos "" \
    --home "/nonexistent" \
    --shell "/sbin/nologin" \
    --no-create-home \
    --uid "${UID}" \
    spring
USER spring

COPY --from=build /app/app/dependencies/ ./
COPY --from=build /app/app/spring-boot-loader/ ./
COPY --from=build /app/app/snapshot-dependencies/ ./
COPY --from=build /app/app/application/ ./
ENV JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000"
ENTRYPOINT [ "java", "org.springframework.boot.loader.launch.JarLauncher" ]