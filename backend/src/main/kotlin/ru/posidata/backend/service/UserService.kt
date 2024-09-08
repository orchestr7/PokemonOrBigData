package ru.posidata.backend.service

import org.springframework.stereotype.Service
import ru.posidata.backend.entity.OurUserEntityFromDb
import ru.posidata.backend.repository.UserRepository
import ru.posidata.common.UserDataFromTelegram

@Service
class UserService(private val userRepository: UserRepository) {
    fun findOrCreateUser(user: UserDataFromTelegram): OurUserEntityFromDb {
        return userRepository.findByUsername(user.username) ?: userRepository.save(
            OurUserEntityFromDb(
                username = user.username,
                firstName = user.firstName,
                lastName = user.lastName,
                firstGameScore = -1,
                secondGameScore = -1,
                thirdGameScore = -1
            )
        )
    }

    fun updateGameRound(username: String?, isNextRound: Boolean): OurUserEntityFromDb? {
        if (username != null) {
            val value = userRepository.findByUsername(username)
            if (value != null) {
                value.updateResultIn(value.currentGameNumber(), isNextRound)
                return userRepository.save(value)
            } else {
                println("Not able to find $username in DB")
                return null
            }
        } else {
            println("Tried to update user with null username")
            return null
        }
    }
}
