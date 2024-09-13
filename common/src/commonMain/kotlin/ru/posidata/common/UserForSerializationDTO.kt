package ru.posidata.common

import kotlinx.serialization.Serializable

@Serializable
data class UserForSerializationDTO(
    val firstName: String?,
    val lastName: String?,
    val username: String?,
)
