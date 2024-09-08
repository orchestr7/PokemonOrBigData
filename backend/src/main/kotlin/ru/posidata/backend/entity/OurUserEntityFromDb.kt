package ru.posidata.backend.entity

import jakarta.persistence.*
import ru.posidata.common.UserForSerializationDTO

@Entity
@Table(name = "users")
class OurUserEntityFromDb(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var firstName: String?,
    var lastName: String?,
    var username: String?,

    var firstGameScore: Int,
    var secondGameScore: Int,
    var thirdGameScore: Int,
) {
    fun toDTO(): UserForSerializationDTO {
        return UserForSerializationDTO(
            firstName = this.firstName,
            lastName = this.lastName,
            username = this.username,
            firstGameScore = this.firstGameScore,
            secondGameScore = this.secondGameScore,
            thirdGameScore = this.thirdGameScore
        )
    }

    fun currentGameNumber() =
        when {
            firstGameScore == -1 -> 1
            secondGameScore == -1 && firstGameScore != -1 -> 2
            thirdGameScore == -1 && firstGameScore != -1 && secondGameScore != -1 -> 3
            else -> 4
        }

    fun updateResultIn(currentGameNumber: Int, isNextRound: Boolean) {
        when(currentGameNumber) {
            1 -> if(!isNextRound) ++firstGameScore else ++secondGameScore
            2 -> if(!isNextRound) ++secondGameScore else ++thirdGameScore
            3 -> if(!isNextRound) ++thirdGameScore
        }
        println(firstGameScore)
        println(secondGameScore)
        println(thirdGameScore)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OurUserEntityFromDb

        if (id != other.id) return false
        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (username != other.username) return false
        if (firstGameScore != other.firstGameScore) return false
        if (secondGameScore != other.secondGameScore) return false
        if (thirdGameScore != other.thirdGameScore) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (firstName?.hashCode() ?: 0)
        result = 31 * result + (lastName?.hashCode() ?: 0)
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (firstGameScore?.hashCode() ?: 0)
        result = 31 * result + (secondGameScore?.hashCode() ?: 0)
        result = 31 * result + (thirdGameScore?.hashCode() ?: 0)
        return result
    }
}
