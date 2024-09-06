package ru.posidata.backend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import ru.posidata.backend.service.TelegramAuthService


@RestController
@RequestMapping(path = ["/api/v1/"])
class TelegramAuthController(private val telegramAuthService: TelegramAuthService) {
    @GetMapping("/user-info")
    fun getSelfUserInfo(authentication: Authentication?): String {
        return authentication?.principal.toString()
    }

    @PostMapping("/test")
    fun test(@RequestBody request: Map<String, Any>): String {
        return "$request"
    }

    @PostMapping("auth/telegram")
    fun telegramAuth(@RequestBody request: Map<String, Any>): ResponseEntity<Any> {
        return try {
            val authResult = telegramAuthService.authenticate(request)
            if (authResult.isAuthenticated) {
                ResponseEntity.ok(authResult.token)
            } else {
                ResponseEntity.status(HttpStatus.FORBIDDEN).body("Login info hash mismatch")
            }
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body("Server error while authenticating")
        }
    }
}
