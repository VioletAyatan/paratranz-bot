package org.paratranz.bot.bot.core

import cn.hutool.core.util.ClassUtil
import cn.hutool.core.util.TypeUtil
import net.mamoe.mirai.event.events.MessageEvent
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.boot.SpringApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import java.util.*
import java.util.function.BiConsumer
import java.util.function.Predicate

/**
 * 对[GroupMessageCommandProcessor]进行处理并解析。
 *
 * @author ankol
 */
class CommandProcessorBeanPostProcessor : BeanPostProcessor, ApplicationContextAware {
    private val log = LoggerFactory.getLogger(CommandProcessorBeanPostProcessor::class.java)

    private lateinit var applicationContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {
        if (bean is GroupMessageCommandProcessor) {
            try {
                //检查指令处理器的参数是否正确
                checkArgument(bean, beanName)
                //注册指令处理器
                CommandManger.registerCommandProcessor(resolveCommand(bean), bean)
            } catch (e: RuntimeException) {
                log.error(e.message)
                SpringApplication.exit(applicationContext)
            }
        }
        return bean
    }

    /**
     * 解析指令
     */
    private fun resolveCommand(commandProcessor: GroupMessageCommandProcessor): List<String> {
        val command = ArrayList<String>(commandProcessor.subKey.size + 1)
        command.addAll(listOf(*commandProcessor.subKey))
        command.add(commandProcessor.key)
        return command
    }

    @Throws(RuntimeException::class)
    private fun checkArgument(bean: Any, beanName: String) {
        if (ClassUtil.isAssignable(BiConsumer::class.java, bean.javaClass)) {
            //验证指令解析器参数是否正确.
            val typeArguments = TypeUtil.getTypeArguments(bean.javaClass)
            for (i in typeArguments.indices) {
                if (!argumentValidators[i].test(typeArguments[i] as Class<*>)) {
                    log.error("Bean [{}] Illegal TypeArgument [" + typeArguments[i] + "]", beanName)
                }
            }
        } else {
            log.error("Resolve 'CommandProcessor' bean [${beanName}] error. it must be implement 'BiConsumer' interface.")
        }
    }

    companion object {
        /**
         * 参数校验器
         */
        private val argumentValidators: List<Predicate<Class<*>>> = listOf(
            Predicate { type: Class<*> -> ClassUtil.isAssignable(MessageEvent::class.java, type) },
            Predicate { type: Class<*> -> ClassUtil.isAssignable(Array<String>::class.java, type) }
        )
    }
}
