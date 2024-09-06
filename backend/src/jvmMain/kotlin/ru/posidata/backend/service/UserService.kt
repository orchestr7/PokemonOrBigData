package ru.posidata.backend.service

import org.springframework.stereotype.Service
import ru.posidata.backend.entity.User
import ru.posidata.backend.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {
    fun findOrCreateUser(telegramData: Map<String, Any>): User {
        val telegramId = telegramData["id"] as Long
        return userRepository.findByTelegramId(telegramId) ?: createUser(telegramData)
    }

    private fun createUser(telegramData: Map<String, Any>): User {
        val user = User(
            telegramId = telegramData["id"] as Long,
            firstName = telegramData["first_name"] as String,
            lastName = telegramData["last_name"] as? String,
            username = telegramData["username"] as? String
        )
        return userRepository.save(user)
    }
}