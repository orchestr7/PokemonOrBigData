package ru.posidata.backend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.posidata.backend.service.TelegramAuthService
import ru.posidata.backend.service.UserService
import ru.posidata.common.QuestionAndAnswer
import ru.posidata.common.UserDataFromTelegram


@RestController
@RequestMapping("api")
class UserController(
    private val telegramAuthService: TelegramAuthService,
    private val userService: UserService
) {
    @GetMapping("/get")
    fun createAndReturnUser(
        userDataFromTelegram: UserDataFromTelegram,
    ): ResponseEntity<Any> {

        println("Received a request to get results from $userDataFromTelegram. Converted to map: ${userDataFromTelegram.convertToMap()}")

        if(!telegramAuthService.isValidHash(userDataFromTelegram.convertToMap(), userDataFromTelegram.hash)) {
            println("Validation unsuccessful for ${userDataFromTelegram.username}")
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }

        println("Validation successful for ${userDataFromTelegram.username}")

        val responseUser = userService.findOrCreateUser(userDataFromTelegram)
        return ResponseEntity.status(HttpStatus.OK).body(responseUser.toDTO())
    }

    @GetMapping("/again")
    fun tryAgainNextRound(): ResponseEntity<Any> {
        // TODO: add logic for incrementing to next round
        return ResponseEntity.status(HttpStatus.OK).body("")
    }

    @GetMapping("/update")
    fun submitAnswer(
        userDataFromTelegram: UserDataFromTelegram,
        questionAndAnswer: QuestionAndAnswer
    ): ResponseEntity<Any> {

        println("Received a request to get results from $userDataFromTelegram. " +
                "Converted to map: ${userDataFromTelegram.convertToMap()}")

        if(!telegramAuthService.isValidHash(userDataFromTelegram.convertToMap(), userDataFromTelegram.hash)) {
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }

        // TODO: add logic for checking questionAndAnswer
        // and incrementing result

        return ResponseEntity.status(HttpStatus.OK).body("")
    }
}
