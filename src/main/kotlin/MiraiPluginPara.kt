package com.example

import com.example.api.ParaApi
import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object MiraiPluginPara : KotlinPlugin(
    JvmPluginDescription(
        id = "com.example.para",
        name = "mirai-plugin-para",
        version = "0.1.0",
    ) {
        author("ankol")
        info("这是一个测试的Mirai-console插件，paratranz网页接口实现")
    }
) {
    private val log = logger

    override fun onEnable() {
        log.info { "Plugin loaded" }
    }

    override fun onDisable() {
        log.info("onDisable...")
    }

    override fun PluginComponentStorage.onLoad() {
        log.info("onLoad...")
    }
}