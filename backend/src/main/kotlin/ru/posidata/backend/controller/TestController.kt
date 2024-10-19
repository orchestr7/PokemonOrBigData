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
        val a = userService.getResultsByUser(
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
        return ResponseEntity.status(HttpStatus.OK).body(a?.map { RoundResult(it.roundNumber, it.result) })
    }
}
