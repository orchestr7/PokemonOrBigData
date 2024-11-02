package ru.posidata.backend.entity

import jakarta.persistence.*
import ru.posidata.common.UserForSerializationDTO

@Entity
@Table(name = "users")
class UserEntityFromDb(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long = 0,
    var firstName: String?,
    var lastName: String?,
    var username: String?,
    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "user",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    var roundResults: List<RoundResultsEntity> = emptyList()
) {
    fun toDTO(): UserForSerializationDTO {
        return UserForSerializationDTO(
            firstName = this.firstName,
            lastName = this.lastName,
            username = this.username,
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserEntityFromDb

        if (userId != other.userId) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (username != other.username) return false
        if (roundResults != other.roundResults) return false

        return true
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + roundResults.hashCode()
        return result
    }
}
