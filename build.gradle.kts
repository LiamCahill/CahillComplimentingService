plugins {
    id("java")
    id("checkstyle")
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
    implementation("org.springframework:spring-jdbc:3.1.0.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("org.projectlombok:lombok:1.18.32")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.jeasy:easy-random-core:5.0.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    toolVersion = "10.16.0"
    config = resources.text.fromFile("$rootDir/config/checkstyle/checkstyle.xml")
}
