package org.paratranz.bot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class TranzaiApplication

fun main(args: Array<String>) {
    runApplication<TranzaiApplication>(*args)
}