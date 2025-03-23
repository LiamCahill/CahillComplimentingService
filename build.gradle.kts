plugins {
    id("java")
    id("io.freefair.lombok") version "8.4"
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

group = "org.cahilll.css"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("mysql:mysql-connector-java:8.0.28")

    implementation ("com.googlecode.lanterna:lanterna:3.1.1")

    // implementation("org.springframework:spring-jdbc:6.1.8")
    // implementation("org.springframework.boot:spring-boot-starter-validation:3.3.4")
    // implementation("org.springframework.boot:spring-boot-starter-jdbc:3.3.4")
    // implementation("org.projectlombok:lombok:1.18.28")

    // annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    // testImplementation("org.jeasy:easy-random-core:5.0.0")
    // testImplementation(platform("org.junit:junit-bom:5.10.0"))
    // testImplementation("org.junit.jupiter:junit-jupiter")
}
