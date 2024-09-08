package ru.posidata.backend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.posidata.backend.service.TelegramAuthService
import ru.posidata.backend.service.UserService
import ru.posidata.common.ResourceType
import ru.posidata.common.Resources
import ru.posidata.common.UserDataFromTelegram


@RestController
@RequestMapping("api")
class UserController(
    private val telegramAuthService: TelegramAuthService,
    private val userService: UserService
) {
    @GetMapping("/get")
    fun getResults(
        @RequestParam authDate: Long,
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestParam hash: String,
        @RequestParam id: Long,
        @RequestParam photoUrl: String,
        @RequestParam username: String,
    ): ResponseEntity<Any> {
        val user = UserDataFromTelegram(
            authDate = authDate,
            firstName = firstName,
            lastName = lastName,
            hash = hash,
            id = id,
            photoUrl = photoUrl,
            username = username,
        )
        println("Received a request to get results from $user. Converted to map: ${user.convertToMap()}")

        if(!telegramAuthService.isValidHash(user.convertToMap(), user.hash)) {
            println("Validation unsuccessful for ${user.username}")
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }

        println("Validation successful for ${user.username}")

        val responseUser = userService.findOrCreateUser(user)
        return ResponseEntity.status(HttpStatus.OK).body(responseUser.toDTO())
    }

    @GetMapping("/update")
    fun submitAnswer(
        @RequestParam authDate: Long,
        @RequestParam firstName: String,
        @RequestParam lastName: String,
        @RequestParam hash: String,
        @RequestParam id: Long,
        @RequestParam photoUrl: String,
        @RequestParam username: String,
        @RequestParam isNextRound: Boolean
    ): ResponseEntity<Any> {
        val user = UserDataFromTelegram(
            authDate = authDate,
            firstName = firstName,
            lastName = lastName,
            hash = hash,
            id = id,
            photoUrl = photoUrl,
            username = username,
        )
        println("Received a request to get results from $user. Converted to map: ${user.convertToMap()}")

        if(!telegramAuthService.isValidHash(user.convertToMap(), user.hash)) {
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }

        val responseUser = userService.updateGameRound(user.username, isNextRound)
        return ResponseEntity.status(HttpStatus.OK).body(responseUser?.toDTO())
    }
}
