plugins {
    java
    id("org.springframework.boot") version "3.0.1"
    id("io.freefair.lombok") version "6.5.1"
    kotlin("jvm") version "1.8.0"
    id("io.spring.dependency-management") version "1.1.0"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    api(platform("net.mamoe:mirai-bom:2.13.3"))
    api("net.mamoe:mirai-core-api-jvm") {
        exclude("net.mamoe", "mirai-core-utils")
    }
    runtimeOnly("net.mamoe:mirai-core-jvm") {
        exclude("net.mamoe", "mirai-core-api")
        exclude("net.mamoe", "mirai-core-utils")
    }
    implementation("net.mamoe:mirai-core-utils-jvm")
    implementation("com.google.code.gson:gson")
    implementation("cn.hutool:hutool-http:5.8.11")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}