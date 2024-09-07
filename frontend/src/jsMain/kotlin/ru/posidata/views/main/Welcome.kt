package ru.posidata.views.main

import js.objects.jso
import react.*
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h6
import react.dom.html.ReactHTML.img
import ru.posidata.views.utils.externals.telegram.TLoginButton
import ru.posidata.views.utils.externals.telegram.User
import ru.posidata.views.utils.internals.Selection
import web.cssom.*

val welcomeCard = FC<WelcomeCardProps> { props ->
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
                +("Шуточный тест из 12 вопросов, чтобы показать " +
                        "насколько большой зоопарк из названий и технологий образовался в отрасли. " +
                        "Изначальная идея родилась из "
                        )
                a {
                    href =
                        "https://docs.google.com/forms/d/e/1FAIpQLScRsfRHXPTuEXdNvUcI8DzJIU5iazqlpksWucPF0d8l2ztkkA/viewform"
                    className = ClassName("text-info")
                    +"этой"
                }

                +" гугл-формы и "

                a {
                    href = "https://pixelastic.github.io/pokemonorbigdata/"
                    className = ClassName("text-info")
                    +"этого"
                }

                +" проекта. А исходный код этого сайта открыт "

                a {
                    href = "https://github.com/orchestr7/PokemonOrBigData"
                    className = ClassName("text-info")
                    +"тут"
                }

                +"."

            }

            if (props.user == null) {
                TLoginButton {
                    botName = "PosiDataBot"
                    buttonSize = "large"
                    onAuthCallback = { user ->
                        val feUser = User(
                            authDate = user.auth_date,
                            firstName = user.first_name,
                            lastName = user.last_name,
                            hash = user.hash,
                            id = user.id,
                            photoUrl = user.photo_url,
                            username = user.username,
                        )
                        props.setUser(
                            feUser
                        )
                    }
                    redirectUrl = null
                    cornerRadius = 15.0
                    requestAccess = "write"
                    usePic = null
                    lang = null
                    additionalClassNames = "d-flex justify-content-center zIndex1000"
                }
            } else {
                h6 {
                    +"Привет, ${props.user?.username}"
                }
            }

            img {
                className = ClassName("animate__animated animate__shakeX mt-1 border border-info border-5 img-glow3 ")
                src = "img/pokemonVSBigData.jpeg"
                style = jso {
                    width = "100%".unsafeCast<Width>()
                    borderRadius = "20px 20px 20px 20px".unsafeCast<BorderRadius>()
                    boxShadow = "10px 10px 20px rgba(0, 0, 0, 0.5)".unsafeCast<BoxShadow>()
                    cursor = "pointer".unsafeCast<Cursor>()
                }
                onClick = {
                    props.setSelection(Selection.QUESTION)
                }
            }
        }
    }
}

external interface WelcomeCardProps : Props {
    var setSelection: StateSetter<Selection>
    var user: User?
    var setUser: StateSetter<User?>
}
