package org.paratranz.bot.bot

import kotlinx.coroutines.runBlocking
import lombok.RequiredArgsConstructor
import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.network.LoginFailedException
import net.mamoe.mirai.utils.BotConfiguration
import net.mamoe.mirai.utils.BotConfiguration.MiraiProtocol
import net.mamoe.mirai.utils.LoggerAdapters.asMiraiLogger
import org.paratranz.bot.api.ParatranzApi
import org.paratranz.bot.bot.core.CommandProcessorHandler
import org.paratranz.bot.bot.schedules.ApplicationSchedule
import org.paratranz.bot.properties.ExternalProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = ["system.module-control.mirai-active"], havingValue = "true", matchIfMissing = true)
open class MiraiBotConfig : ApplicationListener<ApplicationReadyEvent> {
    private val log: Logger = LoggerFactory.getLogger(MiraiBotConfig::class.java)

    @Bean
    open fun paratranzClient(): ParatranzApi {
        return ParatranzApi("5828fb55756dbf6ebb4f76937c16e530")
    }

    @Bean
    open fun miraiBot(
        externalProperties: ExternalProperties
    ): Bot {
        val bot = BotFactory.newBot(
            externalProperties.qq,
            externalProperties.password,
            BotConfiguration {
                autoReconnectOnForceOffline = true
                protocol = MiraiProtocol.ANDROID_PHONE
                isShowingVerboseEventLog = false
                //断线重连时间
                reconnectionRetryTimes = 30
                botLoggerSupplier = { log.asMiraiLogger() }
            }
        )
        //群聊事件处理
        bot.eventChannel.registerListenerHost(MiraiEventListener(bot.coroutineContext))
        return bot
    }

    @Bean
    open fun applicationSchedule(
        paraTranzApi: ParatranzApi,
        externalProperties: ExternalProperties,
        bot: Bot
    ): ApplicationSchedule {
        return ApplicationSchedule(paraTranzApi, externalProperties, bot)
    }

    @Bean
    open fun commandProcessorHandler(): CommandProcessorHandler {
        return CommandProcessorHandler()
    }

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        val bot = event.applicationContext.getBean(Bot::class.java)
        val properties = event.applicationContext.getBean(ExternalProperties::class.java)
        runBlocking {
            try {
                bot.login()
            } catch (e: LoginFailedException) {
                log.error("账号 {} 登录失败，原因：{}", properties.qq, e.message)
            }
        }
    }
}
