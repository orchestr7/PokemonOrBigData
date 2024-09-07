package ru.posidata.views.utils.externals.telegram

data class TUser(
    val auth_date: Int,
    val first_name: String,
    val last_name: String?,
    val hash: String,
    val id: Int,
    val photo_url: String?,
    val username: String?
)