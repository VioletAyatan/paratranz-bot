package org.paratranz.bot.web.controller

import org.paratranz.bot.web.service.MyServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/web")
class WebController {
    @Autowired
    private lateinit var myServices: MyServices
}
