package ru.posidata.backend.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.posidata.backend.entity.OurUserEntityFromDb

@Repository
interface UserRepository : CrudRepository<OurUserEntityFromDb, Long> {
    fun findByUsername(username: String): OurUserEntityFromDb?
}