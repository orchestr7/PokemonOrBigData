package ru.posidata.backend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class TestController {
    @GetMapping("/test")
    fun get(): ResponseEntity<Any> {
        println("Hi!")
        return ResponseEntity.status(HttpStatus.OK).body("HI!")
    }
}
