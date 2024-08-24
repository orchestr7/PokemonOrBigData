package ru.posidata.views.main

import js.objects.jso
import react.FC
import react.dom.html.ReactHTML.div
import react.react
import react.useState
import ru.posidata.views.utils.internals.Answer.NONE
import ru.posidata.views.utils.internals.Selection
import ru.posidata.views.utils.internals.Selection.QUESTION
import ru.posidata.views.utils.internals.Selection.ANSWER
import ru.posidata.views.utils.internals.Selection.RESULTS
import ru.thetax.views.utils.externals.particles.Particles
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
                    when (selection) {
                        Selection.NONE -> {
                            welcomeCard {
                                this.setSelection = setSelection
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
