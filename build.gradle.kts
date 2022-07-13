/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
    id("org.springframework.boot") version "2.7.1"
    id("io.freefair.lombok") version "6.5.0.2"
    kotlin("jvm") version "1.5.30"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    api(platform("net.mamoe:mirai-bom:2.9.1"))
    api("net.mamoe:mirai-core-api-jvm") {
        exclude("net.mamoe", "mirai-core-utils")
    }
    runtimeOnly("net.mamoe:mirai-core-jvm") {
        exclude("net.mamoe", "mirai-core-api")
        exclude("net.mamoe", "mirai-core-utils")
    }
    implementation("net.mamoe:mirai-core-utils-jvm")
    implementation("com.google.code.gson:gson")
    implementation("cn.hutool:hutool-http:5.8.4")
}

group = "com.para"
version = "0.0.1"
description = "tranzai"
java.sourceCompatibility = JavaVersion.VERSION_11

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}