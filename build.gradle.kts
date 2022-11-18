plugins {
    java
}

// 所有子模块通用配置
allprojects {
    group = "org.paratranz.bot"
    version = "0.0.1"

    apply(plugin = "java")
    // 仓库配置
    repositories {
        mavenLocal()
        maven {
            url = uri("https://maven.aliyun.com/repository/public/")
        }
        mavenCentral()
    }
}