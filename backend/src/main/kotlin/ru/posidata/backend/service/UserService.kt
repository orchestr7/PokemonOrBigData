package ru.posidata.backend.service

import org.springframework.stereotype.Service
import ru.posidata.backend.entity.RoundResultsEntity
import ru.posidata.backend.entity.UserEntityFromDb
import ru.posidata.backend.repository.RoundResultsRepository
import ru.posidata.backend.repository.UserRepository
import ru.posidata.common.UserDataFromTelegram

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roundResultsRepository: RoundResultsRepository
) {
    fun findOrCreateUser(user: UserDataFromTelegram): UserEntityFromDb {
        return userRepository.findByUsername(user.username) ?: run {
            val newUser = UserEntityFromDb(
                username = user.username,
                firstName = user.firstName,
                lastName = user.lastName,
            )
            val roundResult = RoundResultsEntity(
                serialNumber = 0,
                result = 0,
                user = newUser
            )

            newUser.roundResults += roundResult
            userRepository.save(newUser)
        }
    }

    fun getRandomUser(): UserEntityFromDb {
        return userRepository.findAll().random()
    }
}
