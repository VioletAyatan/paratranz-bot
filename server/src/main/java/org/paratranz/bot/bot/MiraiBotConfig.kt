package org.paratranz.bot.bot

import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.Bot
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.utils.BotConfiguration.MiraiProtocol
import org.paratranz.bot.api.ParatranzApi
import org.paratranz.bot.bot.core.CommandProcessorBeanPostProcessor
import org.paratranz.bot.properties.ExternalProperties
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(name = ["system.module-control.mirai-active"], havingValue = "true", matchIfMissing = true)
open class MiraiBotConfig : ApplicationListener<ApplicationReadyEvent> {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Bean
    open fun paraApi(): ParatranzApi {
        return ParatranzApi("sadddddddd")
    }

    @Bean
    open fun miraiBot(
        externalProperties: ExternalProperties
    ): Bot {
        val bot = BotFactory.newBot(externalProperties.qq, externalProperties.password) {
            this.autoReconnectOnForceOffline = true
            this.reconnectionRetryTimes = 30
            this.protocol = MiraiProtocol.ANDROID_PHONE
            this.isShowingVerboseEventLog = true
            this.loginCacheEnabled = true
        }
        //群聊事件处理
        bot.eventChannel.registerListenerHost(MiraiEventListener(bot.coroutineContext))
        return bot
    }

    @Bean
    open fun commandProcessorHandler(): CommandProcessorBeanPostProcessor {
        return CommandProcessorBeanPostProcessor()
    }

    /**
     * 开始执行登录
     */
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        runBlocking {
            event.applicationContext.getBean(Bot::class.java).login()
        }
    }
}
