package com.example.test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Test

class KtTest {

    @Test
    fun test() {
        GlobalScope.launch {
            delay(1000)
            println("${Thread.currentThread().name}======全局携程～")
        }
    }

    @Test
    fun test2() {
        GlobalScope.launch {
            delay(1000)
            println("${Thread.currentThread().name}======全局携程～")
        }
        Thread.sleep(2000L)
        println("${Thread.currentThread().name}======我是最后的倔犟～")
    }
}