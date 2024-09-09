package ru.posidata.backend.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.posidata.backend.service.UserService

@RestController
@RequestMapping("api")
class RandomController(
    private val userService: UserService
) {
    @GetMapping("/random")
    fun random(): ResponseEntity<Any> {
        val randomUser = userService.getRandomUser()
        return ResponseEntity.ok(randomUser.toDTO())
    }
}
