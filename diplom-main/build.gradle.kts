import com.github.spotbugs.snom.Confidence
import com.github.spotbugs.snom.Effort

plugins {
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.flywaydb.flyway") version "11.8.2"
    id("com.bmuschko.docker-java-application") version "9.4.0"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    id("checkstyle")
    id("pmd")
    id("com.github.spotbugs") version "6.0.8"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("io.gitlab.arturbosch.detekt") version "1.23.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

checkstyle {
    toolVersion = "10.23.1"
    configFile = file("config/checkstyle/checkstyle.xml")
}

pmd {
    toolVersion = "7.13.0"
    isConsoleOutput = true
    ruleSets = listOf()
    ruleSetFiles = files("config/pmd/pmd-rules.xml")
}

detekt {
    config = files("config/detekt.yml")
    buildUponDefaultConfig = true
    allRules = false
}

spotbugs {
    toolVersion.set("4.8.6")
    effort.set(Effort.MAX)
    reportLevel.set(Confidence.LOW)
    ignoreFailures = false
}

tasks.withType<com.github.spotbugs.snom.SpotBugsTask>().configureEach {
    excludeFilter.set(file("spotbugs-exclude.xml"))
    reports {
        create("html") {
            required.set(true)
            outputLocation.set(layout.buildDirectory.file("reports/spotbugs.html"))
        }
    }
}

tasks.named("check") {
    dependsOn(tasks.withType<com.github.spotbugs.snom.SpotBugsTask>())
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot and Web Dependencies
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.integration:spring-integration-mail")

    // Database dependencies
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("org.hibernate.orm:hibernate-core:6.6.13.Final")

    // Validation dependencies
    implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation("jakarta.validation:jakarta.validation-api:3.0.0")

    // spotbugs
    implementation("com.github.spotbugs:spotbugs-annotations:4.8.6")

    // Development tools (only in development mode)
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // Testing dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.springframework.security:spring-security-test")

    // Security dependencies
    implementation("org.springframework.security:spring-security-crypto")

    // Thymeleaf Security Support
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.2.RELEASE")

    // Lombok for reducing boilerplate code
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    // Flyway for database migrations
    implementation("org.flywaydb:flyway-core:11.8.2")
    testImplementation("org.flywaydb:flyway-core:11.8.2")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:11.8.2")

    // Docker deploy
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Kotlin standard library
    implementation(kotlin("stdlib-jdk8"))

    // Testing Kotlin
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.mockito", module = "mockito-core")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.mockk:mockk:1.13.9")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    testImplementation("org.junit.platform:junit-platform-commons:1.10.2")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
}

docker {
    javaApplication {
        baseImage.set("eclipse-temurin:21-jdk")
        maintainer.set("Parfenix <Parfenix@gmail.com>")
        ports.set(listOf(8080))
        images.set(setOf("diplom:latest"))
        mainClassName.set("com.example.diplom.Application")
    }
}

tasks.named<com.bmuschko.gradle.docker.tasks.image.DockerBuildImage>("dockerBuildImage") {
    dependsOn(tasks.named("bootJar"))
    inputDir.set(file("build/docker"))
    images.set(setOf("diplom:latest"))
}

flyway {
    url = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5432/ezyskills"
    user = System.getenv("DB_USER") ?: "postgres"
    password = System.getenv("DB_PASSWORD") ?: "123"
    locations = arrayOf("classpath:db/migration")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
