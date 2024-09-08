@file:JsModule("react-telegram-auth")
@file:JsNonModule

package ru.posidata.views.utils.externals.telegram

@JsName("TUser")
external interface TUser {
    var auth_date: Int
    var first_name: String
    var last_name: String
    var hash: String
    var id: Int
    var photo_url: String
    var username: String
}
