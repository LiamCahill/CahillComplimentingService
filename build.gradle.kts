plugins {
    id("java")
}

group = "org.css"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework:spring-jdbc:3.1.0.RELEASE")}

tasks.test {
    useJUnitPlatform()
}