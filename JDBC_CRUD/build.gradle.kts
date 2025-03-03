plugins {
    id("org.sonarqube") version "6.0.1.5171"
    id("java")
    id("war")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation ("org.postgresql:postgresql:42.7.2")

    implementation("jakarta.servlet:jakarta.servlet-api:5.0.0")

    implementation ("org.slf4j:slf4j-api:1.7.32")
    implementation ("ch.qos.logback:logback-classic:1.4.12")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

sonar {
    properties {
        property("sonar.projectKey", "D1aI3L0_Computer-Components-Shop")
        property("sonar.organization", "d1ai3l0")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

tasks.war {
    archiveFileName.set("ROOT.war")
}
