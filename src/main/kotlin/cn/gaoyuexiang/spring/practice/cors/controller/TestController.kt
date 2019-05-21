package cn.gaoyuexiang.spring.practice.cors.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("hello")
    fun hello(): String{
        return "Hello, CORS!"
    }
}