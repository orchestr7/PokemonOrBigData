package ru.posidata.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "round_results")
class RoundResultsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var result: Int,
    var currentQuestion: Int,

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    var user: UserEntityFromDb
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RoundResultsEntity

        if (id != other.id) return false
        if (result != other.result) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result1 = id.hashCode()
        result1 = 31 * result1 + result.hashCode()
        result1 = 31 * result1 + user.hashCode()
        return result1
    }
}
