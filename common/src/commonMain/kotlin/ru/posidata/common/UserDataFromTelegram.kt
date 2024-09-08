package ru.posidata.common

import kotlinx.serialization.Serializable

@Serializable
data class UserDataFromTelegram (
    val authDate: Long,
    val firstName: String,
    val lastName: String,
    val hash: String,
    val id: Long,
    val photoUrl: String,
    val username: String,
) {
    fun convertToMap(): Map<String, String> =
        mapOf(
            "auth_date" to "$authDate",
            "first_name" to firstName,
            "hash" to hash,
            "id" to "$id",
            "last_name" to lastName,
            "photo_url" to photoUrl,
            "username" to username
        )
}
