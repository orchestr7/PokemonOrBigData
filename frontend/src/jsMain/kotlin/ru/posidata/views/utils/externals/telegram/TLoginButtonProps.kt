package ru.posidata.views.utils.externals.telegram

import react.PropsWithChildren

data class TUser(
    val auth_date: Int,
    val first_name: String,
    val last_name: String?,
    val hash: String,
    val id: Int,
    val photo_url: String?,
    val username: String?
)

@JsName("TLoginButtonSize")
object TLoginButtonSize {
    val Large = "large"
    val Medium = "medium"
    val Small = "small"
}

@JsName("TLoginButtonProps")
external interface TLoginButtonProps : PropsWithChildren {
    var botName: String
    var buttonSize: String
    var onAuthCallback: (user: TUser) -> Unit
    var redirectUrl: String?
    var cornerRadius: Double
    var requestAccess: String
    var usePic: Boolean?
    var lang: String?
    var additionalClassNames: String
}
