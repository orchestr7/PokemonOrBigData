package ru.posidata.common

import kotlinx.serialization.Serializable

@Serializable
data class UserForSerializationDTO(
    val firstName: String?,
    val lastName: String?,
    val username: String?,
    val firstGameScore: Int?,
    val secondGameScore: Int?,
    val thirdGameScore: Int?
) {
    fun gameNumber() =
        when {
            firstGameScore == -1 -> 1
            secondGameScore == -1 && firstGameScore != -1 -> 2
            thirdGameScore == -1 && firstGameScore != -1 && secondGameScore != -1 -> 3
            else -> 4
        }
}
