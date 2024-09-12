package ru.posidata.views.main

import js.objects.jso
import kotlinx.browser.window
import react.FC
import react.Props
import react.StateSetter
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.a
import ru.posidata.common.Answer.CORRECT
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.h5
import react.dom.html.ReactHTML.img
import react.useState
import ru.posidata.views.utils.externals.fontawesome.faGithub
import ru.posidata.views.utils.externals.fontawesome.fontAwesomeIcon
import ru.posidata.common.UserDataFromTelegram
import ru.posidata.common.Answer
import ru.posidata.common.Answer.NONE
import ru.posidata.common.Selection
import ru.posidata.common.UserForSerializationDTO
import ru.posidata.views.utils.internals.*
import web.cssom.*

val resultCard = FC<ResultProps> { props ->
    val correctAnswers = props.answers.count { it == CORRECT }
    val (loading, setLoading) = useState(true)

    val updateRound = useDeferredRequest {
        if (props.tgUser != null) {
            val response = get(
                url = "${window.location.origin}/api/update",
                params = jso<dynamic> {
                    authDate = props.tgUser?.authDate
                    firstName = props.tgUser?.firstName
                    lastName = props.tgUser?.lastName
                    hash = props.tgUser?.hash
                    id = props.tgUser?.id
                    photoUrl = props.tgUser?.photoUrl
                    username = props.tgUser?.username
                    isNextRound = false
                },
                headers = jsonHeaders,
                loadingHandler = ::noopLoadingHandler,
                responseHandler = ::noopResponseHandler,
            )

            when {
                response.ok -> props.setUser(response.decodeFromJsonString<UserForSerializationDTO>())
                else -> window.alert("Failed to validate with telegram")
            }
        }
    }

    div {
        style = jso {
            display = (if (loading) "none" else "block").unsafeCast<Display>()
        }
        div {
            className = ClassName("col-12 text-center")
            style = jso {
                zIndex = "1000".unsafeCast<ZIndex>()
            }
            h1 {
                className = ClassName("text-center")
                style = jso {
                    fontSize = 50.unsafeCast<FontSize>()
                    color = "yellow".unsafeCast<Color>()
                    display = "inline".unsafeCast<Display>()
                }
                +"$correctAnswers"
            }
            h1 {
                style = jso {
                    fontSize = 35.unsafeCast<FontSize>()
                    color = "white".unsafeCast<Color>()
                    display = "inline".unsafeCast<Display>()
                }
                +"/${props.answers.size}"
            }

            val textMeme = when (correctAnswers) {
                in 0..4 -> Pair("Are you related to IT?", "areyousure.png")
                in 5..8 -> Pair("You need to learn more about the world of Data (and Pokemons)", "end.jpeg")
                in 9..11 -> Pair("You are good, but not the best", "end.jpeg")
                12 -> Pair("I choose you!", "choose-you.webp")
                else -> Pair("", "")
            }

            h5 {
                +textMeme.first
            }

            img {
                className = ClassName("mb-3 mt-3")
                src = "img/${textMeme.second}"
                style = jso {
                    width = "100%".unsafeCast<Width>()
                    boxShadow = "10px 10px 20px rgba(0, 0, 0, 0.5)".unsafeCast<BoxShadow>()
                    borderRadius = "20px 20px 20px 20px".unsafeCast<BorderRadius>()
                }
                onLoad = {
                    setLoading(false)
                }
            }
        }

        div {
            className = ClassName("row justify-content-center")
            div {
                className = ClassName("col-6 text-end")
                style = jso {
                    zIndex = "1000".unsafeCast<ZIndex>()
                }
                ReactHTML.button {
                    className = ClassName("btn btn-outline-info btn-lg")
                    onClick = {
                        props.setSelection(Selection.QUESTION)
                        props.setCounter(0)
                        props.setAnswers(MutableList(12) { NONE })
                        props.setUniqueRandom(listOf())
                        updateRound()
                    }
                    +"Once more!"
                }
            }
            div {
                className = ClassName("col-6 text-center")
                style = jso {
                    zIndex = "1000".unsafeCast<ZIndex>()
                }
                a {
                    ReactHTML.button {
                        className = ClassName("btn btn-light btn-lg")
                        fontAwesomeIcon(faGithub)
                    }
                    href = "https://github.com/orchestr7/PokemonOrBigData"
                }
            }
        }
    }
}


external interface ResultProps : Props {
    var counter: Int
    var answers: MutableList<Answer>
    var setSelection: StateSetter<Selection>
    var setCounter: StateSetter<Int>
    var setAnswers: StateSetter<MutableList<Answer>>
    var setUniqueRandom: StateSetter<List<Int>>
    var user: UserForSerializationDTO?
    var tgUser: UserDataFromTelegram?
    var setUser: StateSetter<UserForSerializationDTO?>
}