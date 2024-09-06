package ru.posidata.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(unique = true)
    val telegramId: Long,
    val firstName: String,
    val lastName: String?,
    val username: String?
)
