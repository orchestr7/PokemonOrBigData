package ru.posidata.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.posidata.backend.entity.User

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByTelegramId(telegramId: Long): User?
}