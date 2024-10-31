package com.example

import com.example.command.AuditCommandHandler
import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.extension.PluginComponentStorage
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin

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
        log.info("plugin Enable...")
        log.info("Plugin register in progress...")
        CommandManager.registerCommand(AuditCommandHandler())
        log.info("Ok...")
    }

    override fun onDisable() {
        log.info("onDisable...")
    }

    override fun PluginComponentStorage.onLoad() {
        log.info("onLoad...")
    }
}