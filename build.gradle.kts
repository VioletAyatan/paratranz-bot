plugins {
    val kotlinVersion = "1.8.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("net.mamoe.mirai-console") version "2.16.0"
}

group = "com.example"
version = "0.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}

mirai {
    jvmTarget = JavaVersion.VERSION_17
}

dependencies {
    //定义一些版本号
    val hutoolVersion = "5.8.26"
    val jackSonVersion = "2.17.2"
    //指定依赖
    implementation("cn.hutool:hutool-http:${hutoolVersion}")
    implementation("com.fasterxml.jackson.core:jackson-core:${jackSonVersion}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${jackSonVersion}")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:${jackSonVersion}")

}