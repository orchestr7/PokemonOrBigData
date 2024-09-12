package ru.posidata.views.main

import js.objects.jso
import kotlinx.browser.window
import react.*
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h6
import react.dom.html.ReactHTML.img
import ru.posidata.views.utils.externals.telegram.TLoginButton
import ru.posidata.common.UserDataFromTelegram
import ru.posidata.common.Selection
import ru.posidata.common.UserForSerializationDTO
import ru.posidata.views.utils.internals.*
import web.cssom.*

val welcomeCard = FC<WelcomeCardProps> { props ->
    val getUser = useDeferredRequest {
        if (props.tgUser != null) {
            val response = get(
                url = "${window.location.origin}/api/get",
                params = jso<dynamic> {
                    authDate = props.tgUser!!.authDate
                    firstName = props.tgUser!!.firstName
                    lastName = props.tgUser!!.lastName
                    hash = props.tgUser!!.hash
                    id = props.tgUser!!.id
                    photoUrl = props.tgUser!!.photoUrl
                    username = props.tgUser!!.username
                },
                headers = jsonHeaders,
                loadingHandler = ::noopLoadingHandler,
                responseHandler = ::noopResponseHandler,
            )

            when {
                response.ok -> {
                    props.setUser(response.decodeFromJsonString<UserForSerializationDTO>())
                }

                else -> window.alert("Failed to authorize you with telegram")
            }
        }
        props.setSelection(Selection.QUESTION)
    }

    div {
        className = ClassName("row justify-content-center mt-1 px-0")
        div {
            className = ClassName("col-11 px-0 text-center")
            h1 {
                style = jso {
                    fontFamily = "'Pokemon Solid', sans-serif".unsafeCast<FontFamily>()
                    display = "inline".unsafeCast<Display>()
                    color = "rgb(114, 206, 224)".unsafeCast<Color>()
                }
                +"BigData"
            }
            h1 {
                className = ClassName("text-white")
                style = jso {
                    display = "inline".unsafeCast<Display>()
                    color = "rgb(0, 206, 224)".unsafeCast<Color>()
                }
                +" or "
            }
            h1 {
                style = jso {
                    fontFamily = "'Pokemon Solid', sans-serif".unsafeCast<FontFamily>()
                    color = "yellow".unsafeCast<Color>()
                    display = "inline".unsafeCast<Display>()
                }
                +"Pokémon?"
            }

            h6 {
                className = ClassName("mt-3 mb-3 text-start")
                +("A humorous test of 12 questions, made over a couple of nights to illustrate how large a zoo of names " +
                        "has formed in the Big Data world. The original idea was born "
                        )
                a {
                    href =
                        "https://docs.google.com/forms/d/e/1FAIpQLScRsfRHXPTuEXdNvUcI8DzJIU5iazqlpksWucPF0d8l2ztkkA/viewform"
                    className = ClassName("text-info")
                    +"here"
                }

                +" and "

                a {
                    href = "https://pixelastic.github.io/pokemonorbigdata/"
                    className = ClassName("text-info")
                    +"here"
                }

                +". The source code of this site can be found on the "

                a {
                    href = "https://github.com/orchestr7/PokemonOrBigData"
                    className = ClassName("text-info")
                    +"GitHub"
                }

                +". ${if (props.tgUser == null) "If you wish to share your results - you can register with Telegram using the button below:" else ""}"
            }


            if (props.tgUser == null) {
                TLoginButton {
                    botName = "PosiDataBot"
                    buttonSize = "small"
                    redirectUrl = null
                    cornerRadius = 15.0
                    requestAccess = "write"
                    usePic = null
                    lang = null
                    additionalClassNames = "d-flex justify-content-center zIndex1000 mb-2"
                    onAuthCallback = { user ->
                        val feUser = UserDataFromTelegram(
                            authDate = user.auth_date,
                            firstName = user.first_name,
                            lastName = user.last_name,
                            hash = user.hash,
                            id = user.id,
                            photoUrl = user.photo_url,
                            username = user.username,
                        )
                        console.log(feUser.convertToMap())
                        props.setTgUser(feUser)
                    }
                }
            } else {
                h6 {
                    className = ClassName("mb-2")
                    style = jso {
                        color = "yellow".unsafeCast<Color>()
                    }
                    +"Hello, ${props.tgUser?.username}! Now your results are being recorded, if you don't want this - just refresh the page."
                }
            }

            img {
                className =
                    ClassName("mt-1 mb-2")
                src = "img/pokemon-vs-data.jpeg"
                style = jso {
                    width = "100%".unsafeCast<Width>()
                    borderRadius = "2px 2px 2px 2px".unsafeCast<BorderRadius>()
                    boxShadow = "10px 10px 20px rgba(0, 0, 0, 0.5)".unsafeCast<BoxShadow>()
                }
            }

            img {
                className =
                    ClassName("animate__animated animate__shakeX border border-info border-4 img-glow3 mb-3")
                src = if (props.tgUser == null) "img/lets-start.png" else "img/lets-start-registered.png"
                style = jso {
                    width = "100%".unsafeCast<Width>()
                    borderRadius = "15px 15px 15px 15px".unsafeCast<BorderRadius>()
                    boxShadow = "10px 10px 20px rgba(0, 0, 0, 0.5)".unsafeCast<BoxShadow>()
                    cursor = "pointer".unsafeCast<Cursor>()
                }
                onClick = {
                    getUser()
                }
            }
        }
    }
}

external interface WelcomeCardProps : Props {
    var setSelection: StateSetter<Selection>
    var setUser: StateSetter<UserForSerializationDTO?>
    var tgUser: UserDataFromTelegram?
    var setTgUser: StateSetter<UserDataFromTelegram?>
}
