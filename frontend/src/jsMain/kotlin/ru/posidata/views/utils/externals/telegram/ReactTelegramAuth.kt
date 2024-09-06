@file:JsModule("react-telegram-auth")
@file:JsNonModule

package ru.posidata.views.utils.externals.telegram

import react.Component
import react.ReactElement
import react.State

/**
 * External declaration of [PieChart] react component
 */
@JsName("TLoginButton")
external class TLoginButton : Component<TLoginButtonProps, State> {
    override fun render(): ReactElement<TLoginButtonProps>?
}
