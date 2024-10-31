package org.paratranz.bot.tools

import cn.hutool.core.io.FileUtil
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationPreparedEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.io.ClassPathResource
import java.io.File

/**
 * pre-processing the configuration file.
 * if is not existed. configuration-file will be created.
 *
 * @author Ankol
 */
class ConfigInitializer : ApplicationListener<ApplicationPreparedEvent> {
    private val log = LoggerFactory.getLogger(ConfigInitializer::class.java)

    override fun onApplicationEvent(event: ApplicationPreparedEvent) {
        val file = File("config${File.separator}config.properties")
        if (!file.exists()) {
            log.warn("Warning! Configuration file not existed! creat it....")
            ClassPathResource("template/config.properties").inputStream.use {
                FileUtil.writeFromStream(it, file)
            }
        }
    }
}