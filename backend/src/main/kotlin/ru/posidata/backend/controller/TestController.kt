package ru.posidata.backend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.posidata.backend.service.UserService
import ru.posidata.common.RoundResult
import ru.posidata.common.UserDataFromTelegram

@RestController
@RequestMapping("test")
class TestController(
    private val userService: UserService
) {
    @GetMapping("/test1")
    fun createUser(): ResponseEntity<Any> {
        userService.findOrCreateUser(
            UserDataFromTelegram(
                1234567,
                "a",
                "a",
                "",
                123,
                "",
                "blabla",
            )
        )
        return ResponseEntity.status(HttpStatus.OK).body("HI!")
    }

    @GetMapping("/test2")
    fun getResults(): ResponseEntity<Any> {
        val test = userService.getResultsByUser(
            UserDataFromTelegram(
                1234567,
                "a",
                "a",
                "",
                123,
                "",
                "blabla",
            )
        )
        return ResponseEntity.status(HttpStatus.OK).body(test?.mapIndexed { i, v -> RoundResult(i, v.result) })
    }

    @GetMapping("/test3")
    fun nextRound(): ResponseEntity<Any> {
        val test = userService.getResultsByUser(
            UserDataFromTelegram(
                1234567,
                "a",
                "a",
                "",
                123,
                "",
                "blabla",
            )
        )
        return ResponseEntity.status(HttpStatus.OK).body(test?.mapIndexed { i, v -> RoundResult(i, v.result) })
    }

    @GetMapping("/test4")
    fun randomUser(): String? {
        return userService.getRandomUser().username
    }

    @GetMapping("/test5")
    fun incrementResult(): ResponseEntity<Any> {
        val test = UserDataFromTelegram(
                1234567,
                "a",
                "a",
                "",
                123,
                "",
                "blabla",
            )

        val userEntityFromDb = userService.getUserByUserName(test) ?: return ResponseEntity(HttpStatus.FORBIDDEN)
        userService.updateResultsForUser(userEntityFromDb, true)

        return ResponseEntity.status(HttpStatus.OK).body("")
    }

    @GetMapping("/test6")
    fun nextRoundTest(): ResponseEntity<Any> {
        val test = UserDataFromTelegram(
            1234567,
            "a",
            "a",
            "",
            123,
            "",
            "blabla",
        )

        val userEntityFromDb = userService.getUserByUserName(test) ?: return ResponseEntity(HttpStatus.FORBIDDEN)

        userService.nextRound(userEntityFromDb)
        return ResponseEntity.status(HttpStatus.OK).body("")

    }
}
