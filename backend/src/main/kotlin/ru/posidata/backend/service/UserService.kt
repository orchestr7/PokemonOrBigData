package ru.posidata.backend.service

import org.springframework.stereotype.Service
import ru.posidata.backend.entity.RoundResultsEntity
import ru.posidata.backend.entity.UserEntityFromDb
import ru.posidata.backend.repository.UserRepository
import ru.posidata.common.UserDataFromTelegram

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun findOrCreateUser(user: UserDataFromTelegram): UserEntityFromDb {
        return userRepository.findByUsername(user.username) ?: run {
            val newUser = UserEntityFromDb(
                username = user.username,
                firstName = user.firstName,
                lastName = user.lastName,
            )
            val roundResult = RoundResultsEntity(
                roundNumber = 0,
                result = 0,
                user = newUser
            )

            newUser.roundResults += roundResult
            userRepository.save(newUser)
        }
    }

    fun getResultsByUser(userFromTg: UserDataFromTelegram): List<RoundResultsEntity>? {
        return userRepository.findByUsername(userFromTg.username)?.roundResults ?: return null
    }

    fun getRandomUser(): UserEntityFromDb {
        return userRepository.findAll().random()
    }
}
