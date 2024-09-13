package ru.posidata.backend.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.posidata.backend.entity.RoundResultsEntity

@Repository
interface RoundResultsRepository : JpaRepository<RoundResultsEntity, Long>