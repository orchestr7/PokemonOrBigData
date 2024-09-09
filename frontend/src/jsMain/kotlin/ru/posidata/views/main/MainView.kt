package ru.posidata.views.main

import js.objects.jso
import react.*
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import ru.posidata.common.Answer.NONE
import ru.posidata.common.Selection
import ru.posidata.common.Selection.QUESTION
import ru.posidata.common.Selection.ANSWER
import ru.posidata.common.Selection.RESULTS
import ru.posidata.views.utils.externals.particles.Particles
import ru.posidata.common.UserDataFromTelegram
import ru.posidata.common.UserForSerializationDTO
import web.cssom.*

val mainView = FC {
    Particles::class.react {
        id = "tsparticles"
        url = "${kotlinx.browser.window.location.origin}/particles.json"
    }

    val (selection, setSelection) = useState(Selection.NONE)
    val (counter, setCounter) = useState(0)
    val (answers, setAnswers) = useState(MutableList(12) { NONE })
    val (pokemonId, setPokemonId) = useState(0)
    val (uniqueRandom, setUniqueRandom) = useState<List<Int>>(listOf())
    val (user, setUser) = useState<UserForSerializationDTO?>(null)
    val (tgUser, setTgUser) = useState<UserDataFromTelegram?>(null)

    div {
        className = ClassName("full-width-container")
        div {
            className = ClassName("row justify-content-center align-items-center")
            style = jso {
                minHeight = "100vh".unsafeCast<MinHeight>()
            }

            div {
                id = "back"
                className = ClassName("card col-xl-4 col-lg-5 col-md-7 col-sm-8 col-12")
                style = jso {
                    minHeight = "80vh".unsafeCast<MinHeight>()
                    borderRadius = "40px 40px 40px 40px".unsafeCast<BorderRadius>()
                    boxShadow = "10px 10px 20px rgba(0, 0, 0, 0.5)".unsafeCast<BoxShadow>()
                }

                headerRow {}

                div {
                    className = ClassName("row justify-content-center text-white mt-3 ")
                    style = jso {
                        zIndex = "1000".unsafeCast<ZIndex>()
                        minHeight = "53vh".unsafeCast<MinHeight>()
                        display = Display.flex
                    }
                    if (user != null && user.gameNumber() == 4) {
                        ReactHTML.h6 {
                            className = ClassName("mb-2 text-white mx-2")
                            +"Ты отыграл уже три раза, в рейтинге участвовать больше не получится, обнови страницу если хочешь просто пройти тест. Твои результаты:"

                        }
                        ReactHTML.h6 {
                            className = ClassName("mb-2 text-center")
                            style = jso {
                                color = "yellow".unsafeCast<Color>()
                            }
                            +"${user.firstGameScore}/12"
                        }
                        ReactHTML.h6 {
                            className = ClassName("mb-2 text-center")
                            style = jso {
                                color = "yellow".unsafeCast<Color>()
                            }
                            +"${user.firstGameScore}/12"
                        }
                        ReactHTML.h6 {
                            className = ClassName("mb-2 text-center")
                            style = jso {
                                color = "yellow".unsafeCast<Color>()
                            }
                            +"${user.thirdGameScore}/12"
                        }
                    } else {
                        when (selection) {
                            Selection.NONE -> {
                                welcomeCard {
                                    this.setSelection = setSelection
                                    this.setUser = setUser
                                    this.tgUser = tgUser
                                    this.setTgUser = setTgUser
                                }
                            }

                            QUESTION -> questionCard {
                                this.counter = counter
                                this.setCounter = setCounter
                                this.answers = answers
                                this.setAnswers = setAnswers
                                this.setPokemonId = setPokemonId
                                this.pokemonId = pokemonId
                                this.setSelection = setSelection
                                this.uniqueRandom = uniqueRandom
                                this.setUniqueRandom = setUniqueRandom

                                this.user = user
                                this.tgUser = tgUser
                                this.setUser = setUser
                            }

                            ANSWER -> {
                                answerCard {
                                    this.setSelection = setSelection
                                    this.counter = counter
                                    this.pokemonId = pokemonId
                                    this.answers = answers
                                }
                            }

                            RESULTS -> {
                                resultCard {
                                    this.counter = counter
                                    this.answers = answers
                                    this.setSelection = setSelection
                                    this.setCounter = setCounter
                                    this.setAnswers = setAnswers
                                    this.setUniqueRandom = setUniqueRandom
                                    this.setSelection = setSelection

                                    this.user = user
                                    this.tgUser = tgUser
                                    this.setUser = setUser
                                }
                            }
                        }
                    }
                }

                if (selection != Selection.NONE) {
                    progressBar {
                        this.counter = counter
                        this.selection = selection
                        this.answers = answers
                    }
                }
            }
        }
    }
}
