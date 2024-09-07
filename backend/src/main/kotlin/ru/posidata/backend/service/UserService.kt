package ru.posidata.backend.service

import org.springframework.stereotype.Service
import ru.posidata.backend.entity.User
import ru.posidata.backend.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {
    fun findOrCreateUser(
        username: String?,
        telegramId: Long,
        firstName: String?,
        lastName: String?
    ): User {
        return userRepository.findByTelegramId(telegramId) ?: userRepository.save(
            User(
                telegramId = telegramId,
                username = username,
                firstName = firstName,
                lastName = lastName,
                firstGameScore = 0,
                secondGameScore = null,
                thirdGameScore = null
            )
        )
    }
}
