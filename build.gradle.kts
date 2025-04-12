plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
    id("org.flywaydb.flyway") version "11.6.0"
    kotlin("jvm") version "1.9.20"

}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
    // Spring Boot and Web Dependencies
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Database dependencies
    implementation("org.postgresql:postgresql:42.7.5")

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
    implementation("org.flywaydb:flyway-core:9.5.0")

    // Kotlin standard library
    implementation(kotlin("stdlib-jdk8"))
}

flyway {
    url = "jdbc:postgresql://localhost:5432/ezyskills"
    user = "postgres"
    password = "123"
    locations = arrayOf("classpath:db/migration")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
