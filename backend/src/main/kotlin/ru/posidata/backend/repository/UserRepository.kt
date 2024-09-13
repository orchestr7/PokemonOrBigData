package ru.posidata.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.posidata.backend.entity.UserEntityFromDb

@Repository
interface UserRepository : JpaRepository<UserEntityFromDb, Long> {
    fun findByUsername(username: String): UserEntityFromDb?
}
