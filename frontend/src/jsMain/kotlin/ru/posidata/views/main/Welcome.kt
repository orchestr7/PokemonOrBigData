package ru.posidata.views.main

import js.objects.jso
import kotlinx.browser.window
import react.*
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.button
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
                response.ok -> props.setUser(response.decodeFromJsonString<UserForSerializationDTO>())
                else -> window.alert("Failed to login with telegram")
            }
        }
    }

    // just a small test
    /*div {
        button {
            onClick = {
                console.log(props.tgUser)
                getUser()
            }
        }
    }

    div {
        button {
            onClick = {
                val feUser = UserDataFromTelegram(
                    authDate = 1725741859,
                    firstName = "Андрей",
                    lastName = "Кулешов",
                    hash = "4a8cb14838a9797d8994bf73ac9734e3fd634a5abec5be7371f83737d1dc82e8",
                    id = 221298772,
                    photoUrl = "https://t.me/i/userpic/320/d3fKyG306aXHDBCxZXfWTpGlii6fZqZMo1tBmMPEl_E.jpg",
                    username = "akuleshov7",
                )
                props.setTgUser(feUser)
            }
        }
    }
*/

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
                +" или "
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
                // (https://docs.google.com/a/octo.com/forms/d/1kckcq_uv8dk9-W5rIdtqRwCHN4Uh209ELPUjTEZJDxc/viewform)
                // (https://github.com/pixelastic/pokemonorbigdata)
                className = ClassName("mt-3 mb-3 text-start")
                +("Шуточный тест из 12 вопросов, сделаный за пару ночей, чтобы показать " +
                        "насколько большой зоопарк из названий образовался в дате. " +
                        "Изначальная идея родилась "
                        )
                a {
                    href =
                        "https://docs.google.com/forms/d/e/1FAIpQLScRsfRHXPTuEXdNvUcI8DzJIU5iazqlpksWucPF0d8l2ztkkA/viewform"
                    className = ClassName("text-info")
                    +"тут"
                }

                +" и "

                a {
                    href = "https://pixelastic.github.io/pokemonorbigdata/"
                    className = ClassName("text-info")
                    +"тут"
                }

                +" Иходный код этого сайта открыт "

                a {
                    href = "https://github.com/orchestr7/PokemonOrBigData"
                    className = ClassName("text-info")
                    +"здесь"
                }

                +". ${if (props.tgUser == null) "Чтобы участвовать в розыгрыше и рейтинге - залогинься:" else ""}"
            }

            if (props.tgUser == null) {
                TLoginButton {
                    botName = "PosiDataBot"
                    buttonSize = "large"
                    redirectUrl = null
                    cornerRadius = 15.0
                    requestAccess = "write"
                    usePic = null
                    lang = null
                    additionalClassNames = "d-flex justify-content-center zIndex1000"
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
                        props.setTgUser(feUser)
                    }
                }
            } else {
                h6 {
                    className = ClassName("mb-2")
                    style = jso {
                        color = "yellow".unsafeCast<Color>()
                    }
                    +"Привет, ${props.tgUser?.username}! Теперь ты участвуешь в розыгрыше на SmartData. Если захочешь просто поиграть - обнови страницу."
                }
            }

            img {
                className =
                    ClassName("animate__animated animate__shakeX mt-1 border border-info border-5 img-glow3 mb-3")
                src = "img/pokemonVSBigData.jpeg"
                style = jso {
                    width = "100%".unsafeCast<Width>()
                    borderRadius = "20px 20px 20px 20px".unsafeCast<BorderRadius>()
                    boxShadow = "10px 10px 20px rgba(0, 0, 0, 0.5)".unsafeCast<BoxShadow>()
                    cursor = "pointer".unsafeCast<Cursor>()
                }
                onClick = {
                    getUser()
                    props.setSelection(Selection.QUESTION)
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
