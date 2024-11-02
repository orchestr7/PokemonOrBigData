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
                roundNumber = 0,
                result = 0,
                user = newUser
            )

            newUser.roundResults += roundResult
            userRepository.save(newUser)
        }
    }

    fun getResultsByUser(userFromTg: UserDataFromTelegram): List<RoundResultsEntity>? {
        return getUserByUserName(userFromTg)?.roundResults ?: return null
    }

    fun getUserByUserName(userFromTg: UserDataFromTelegram): UserEntityFromDb? =
        userRepository.findByUsername(userFromTg.username)

    fun updateResultsForUser(user: UserEntityFromDb) {
        // as we have One-to-Many relation - we are getting the id of the last round (there can be many rounds)
        val lastRoundId = user.roundResults.last().id
        // requesting this record with the last results
        val lastRoundResultsEntity = roundResultsRepository.findById(lastRoundId).get()
        // incrementing the last result in the list
        lastRoundResultsEntity.result++
        roundResultsRepository.save(lastRoundResultsEntity)
    }

    fun getRandomUser(): UserEntityFromDb {
        return userRepository.findAll().random()
    }
}
