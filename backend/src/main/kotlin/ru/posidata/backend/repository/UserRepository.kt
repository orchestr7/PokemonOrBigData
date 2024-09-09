package ru.posidata.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.posidata.backend.entity.OurUserEntityFromDb

@Repository
interface UserRepository : JpaRepository<OurUserEntityFromDb, Long> {
    fun findByUsername(username: String): OurUserEntityFromDb?
}
