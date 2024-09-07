package ru.posidata.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(unique = true)
    val telegramId: Long,
    val firstName: String?,
    val lastName: String?,
    val username: String?,

    @Column(nullable = true)
    var firstGameScore: Int?,

    @Column(nullable = true)
    var secondGameScore: Int?,

    @Column(nullable = true)
    var thirdGameScore: Int?,
)
