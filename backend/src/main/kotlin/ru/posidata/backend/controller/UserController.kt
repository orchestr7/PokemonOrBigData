package ru.posidata.backend.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.posidata.backend.service.TelegramAuthService
import ru.posidata.backend.service.UserService
import ru.posidata.common.ResourceType
import ru.posidata.common.Resources


@RestController
@RequestMapping("api")
class UserController(
    private val telegramAuthService: TelegramAuthService,
    private val userService: UserService
) {
    @GetMapping("/get")
    fun getResults(
        @RequestParam username: String?,
        @RequestParam telegramId: Long,
        @RequestParam hash: String,
        @RequestParam firstName: String?,
        @RequestParam lastName: String?,
    ): ResponseEntity<Any> {
        println("Received a request to get results from $username, $telegramId, $hash, $firstName, $lastName")
        userService.findOrCreateUser(
            username = username,
            telegramId = telegramId,
            firstName = firstName,
            lastName = lastName
        )
        return ResponseEntity.status(HttpStatus.OK).body("")
    }

    @GetMapping("/answer")
    fun submitAnswer(
        @RequestParam pokemonName: String,
        @RequestParam resourceType: ResourceType,
        // @RequestParam gameNumber: Int,
        @RequestParam username: String,
        @RequestParam hash: String,
    ): ResponseEntity<Any> {
        // user
        println(pokemonName)
        var count: Int = 0
        val pokemon = Resources.getByName(pokemonName)
            ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid pokemon name $pokemonName")
        if (pokemon.type == resourceType) {
            // correct answer
            ++count
        } else {
            // incorrect answer
        }
        return ResponseEntity.status(HttpStatus.OK).body(resourceType)
    }
}
