plugins {
  java
  id("org.springframework.boot") version "3.4.1"
  id("io.spring.dependency-management") version "1.1.7"
}

group = "org.springframework.samples"
version = "3.3.0"

java {
  toolchain {
    languageVersion.set(JavaLanguageVersion.of(21))
  }
}

repositories {
  mavenCentral()
}

val webjarsFontawesomeVersion by extra("4.7.0")
val webjarsBootstrapVersion by extra("5.3.3")

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-cache")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("javax.cache:cache-api")
  implementation("jakarta.xml.bind:jakarta.xml.bind-api")
  runtimeOnly("org.springframework.boot:spring-boot-starter-actuator")
  runtimeOnly("org.webjars.npm:bootstrap:${webjarsBootstrapVersion}")
  runtimeOnly("org.webjars.npm:font-awesome:${webjarsFontawesomeVersion}")
  runtimeOnly("com.github.ben-manes.caffeine:caffeine")
  runtimeOnly("com.h2database:h2")
  runtimeOnly("org.postgresql:postgresql")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.springframework.boot:spring-boot-docker-compose")
  testImplementation("org.testcontainers:junit-jupiter")
}

tasks.named<Test>("test") {
  useJUnitPlatform()
}