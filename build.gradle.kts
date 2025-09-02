import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("java")
    id("application")
    id("jacoco")
}

group = "com.exercises"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}


application {
    mainClass.set("com.exercises.vehicles.Boot")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}


tasks.jar {
    enabled = false
}

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    //Database
    runtimeOnly("com.mysql:mysql-connector-j")


    // Lombok
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")

    // MapStruct
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")
    testAnnotationProcessor("org.mapstruct:mapstruct-processor:1.4.2.Final")

    // Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    // Messaging
    implementation("org.springframework.kafka:spring-kafka")


    // Testing, Rest Assured
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.tngtech.archunit:archunit-junit5:0.22.0")
    testImplementation("io.rest-assured:json-path:4.5.1")
    testImplementation("io.rest-assured:rest-assured-common:4.5.1")
    testImplementation("io.rest-assured:xml-path:4.5.1")
    testImplementation("io.rest-assured:rest-assured:4.5.1")
    testImplementation("org.springframework.kafka:spring-kafka-test")

    // Test Dependencies
    testImplementation("org.mockito:mockito-inline:2.13.0")

    // Easy Random
    implementation("org.jeasy:easy-random-core:5.0.0")

}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(PASSED, SKIPPED, FAILED)
    }
}


tasks.jacocoTestReport {
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}
