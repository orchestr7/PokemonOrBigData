@file:JsModule("react-telegram-auth")
@file:JsNonModule

package ru.posidata.views.utils.externals.telegram

import react.PropsWithChildren


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
