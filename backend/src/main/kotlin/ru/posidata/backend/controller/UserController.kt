package ru.posidata.backend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.posidata.backend.service.TelegramAuthService
import ru.posidata.backend.service.UserService
import ru.posidata.common.QuestionAndAnswer
import ru.posidata.common.Question
import ru.posidata.common.UserDataFromTelegram


@RestController
@RequestMapping("user")
class UserController(
    private val telegramAuthService: TelegramAuthService,
    private val userService: UserService
) {
    @GetMapping("/new")
    fun createAndReturnUser(
        userDataFromTelegram: UserDataFromTelegram,
    ): ResponseEntity<Any> {
        if (!telegramAuthService.isValidHash(userDataFromTelegram)) {
            println("Validation unsuccessful for ${userDataFromTelegram.username}")
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }

        val responseUser = userService.findOrCreateUser(userDataFromTelegram)
        return ResponseEntity.status(HttpStatus.OK).body(responseUser.toDTO())
    }

    @PutMapping("/answer")
    fun submitAnswer(
        @RequestBody
        userDataFromTelegram: UserDataFromTelegram,
        @RequestBody
        questionAndAnswer: QuestionAndAnswer
    ): ResponseEntity<Any> {
        if (!telegramAuthService.isValidHash(userDataFromTelegram)) {
            println("Validation unsuccessful for ${userDataFromTelegram.username}")
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }

        val userEntityFromDb =
            userService.getUserByUserName(userDataFromTelegram) ?: return ResponseEntity(HttpStatus.FORBIDDEN)

        userService.updateResultsForUser(
            userEntityFromDb,
            Question.getById(questionAndAnswer.questionId).pokemonType == questionAndAnswer.pokemonTypeAnswer
        )

        return ResponseEntity.status(HttpStatus.OK).body("")
    }

    @PutMapping("/again")
    fun tryAgainNewRound(
        @RequestBody
        userDataFromTelegram: UserDataFromTelegram,
    ): ResponseEntity<Any> {
        if (!telegramAuthService.isValidHash(userDataFromTelegram)) {
            println("Validation unsuccessful for ${userDataFromTelegram.username}")
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }

        val userEntityFromDb = userService.getUserByUserName(userDataFromTelegram) ?: return ResponseEntity(HttpStatus.FORBIDDEN)

        userService.nextRound(userEntityFromDb)

        return ResponseEntity.status(HttpStatus.OK).body("")
    }
}
