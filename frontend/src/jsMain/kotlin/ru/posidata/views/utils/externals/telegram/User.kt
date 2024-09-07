package ru.posidata.views.utils.externals.telegram

data class User (
    val authDate: Int,
    val firstName: String,
    val lastName: String?,
    val hash: String,
    val id: Int,
    val photoUrl: String?,
    val username: String?,
)
