package ru.posidata.backend.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.posidata.backend.entity.User

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findByTelegramId(telegramId: Long): User?
}