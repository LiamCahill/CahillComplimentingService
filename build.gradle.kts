plugins {
    id("java")
    id("application")
    id("io.freefair.lombok") version "8.4"
    id("org.springframework.boot") version "2.7.18"
    id("io.spring.dependency-management") version "1.1.4"
    //id("org.springframework.boot") version "3.4.3"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

group = "org.cahilll.css"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-jdbc:3.1.0.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("org.projectlombok:lombok:1.18.32")
    implementation("org.springframework.boot:spring-boot-configuration-processor")


    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.jeasy:easy-random-core:5.0.0")
    testImplementation("org.springframework:spring-test:5.3.29")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testcontainers:testcontainers:1.20.5")
    testImplementation("org.testcontainers:junit-jupiter:1.20.5")
    testImplementation("org.testcontainers:postgresql:1.20.5")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.18")


    "developmentOnly"("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    //runtimeOnly("org.springframework.boot:spring-boot-docker-compose")
}

tasks.test {
    useJUnitPlatform()
}

springBoot {
    mainClass.set("org.cahilll.css.Controller.Main")
}

application {
    mainClass.set("org.cahilll.css.Controller.Main")
}