package ru.posidata.views.main

import js.objects.jso
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.img
import ru.posidata.views.utils.internals.Answer.WRONG
import ru.posidata.views.utils.internals.Answer.CORRECT
import ru.posidata.views.utils.internals.Selection.ANSWER
import ru.posidata.views.components.ResourceType.BIG_DATA
import ru.posidata.views.components.ResourceType.POKEMON
import ru.posidata.views.components.Resources
import ru.posidata.views.components.neonLightingText
import ru.posidata.views.utils.internals.Answer
import ru.posidata.views.utils.internals.Selection
import web.cssom.*
import kotlin.random.Random

val questionCard = FC<QuestionCard> { props ->
    useEffectOnce {
        var randomNumber = Random.nextInt(0, Resources.entries.size)
        while (true) {
            if (!props.uniqueRandom.contains(randomNumber)) {
                props.setUniqueRandom(props.uniqueRandom + randomNumber)
                props.setPokemonId(randomNumber)
                break
            }
            randomNumber = Random.nextInt(0, Resources.entries.size)
        }
    }

    val pokemon = Resources.getById(props.pokemonId)

    div {
        className = ClassName("row justify-content-center logo-main mt-3 px-0")
        style = jso {
            zIndex = "1000".unsafeCast<ZIndex>()
            minHeight = "40vh".unsafeCast<MinHeight>()
        }
        div {
            className = ClassName("col-12 px-0 text-center")
            neonLightingText(
                pokemon.getName()
            )
        }

        div {
            className = ClassName("row justify-content-center mt-3")
            div {
                className = ClassName("col-6 d-flex justify-content-center text-center ps-0")
                img {
                    className = ClassName("img-glow1")
                    src = "/img/bigdata-logo-bg.jpeg"
                    style = jso {
                        width = "100%".unsafeCast<Width>()
                        border = "0.1rem solid hsl(186 100% 69%)".unsafeCast<Border>()
                        borderRadius = 1.rem
                    }
                    onClick = {
                        // FixMe: Duplication
                        props.setSelection(ANSWER)
                        props.setCounter(props.counter + 1)
                        if (pokemon.type == BIG_DATA) {
                            props.answers[props.counter] = CORRECT
                        } else {
                            props.answers[props.counter] = WRONG
                        }
                        props.setAnswers(props.answers)
                    }
                }
            }
            div {
                className = ClassName("col-6 d-flex justify-content-center text-center pe-0")
                img {
                    className = ClassName("img-glow2")
                    src = "/img/pokemon-logo-bg.jpeg"
                    style = jso {
                        width = "100%".unsafeCast<Width>()
                        border = "0.1rem solid hsl(186 100% 69%)".unsafeCast<Border>()
                        borderRadius = 1.rem
                    }
                    onClick = {
                        // FixMe: Duplication
                        props.setSelection(ANSWER)
                        props.setCounter(props.counter + 1)
                        if (pokemon.type == POKEMON) {
                            props.answers[props.counter] = CORRECT
                        } else {
                            props.answers[props.counter] = WRONG
                        }
                        props.setAnswers(props.answers)
                    }
                }
            }

            div {
                className = ClassName("row justify-content-center px-0 mt-3")
                div {
                    className = ClassName("col-12 px-0 text-center")
                    h1 {
                        onClick = {
                            // FixMe: Duplication
                            props.setSelection(ANSWER)
                            props.setCounter(props.counter + 1)
                            if (pokemon.type == BIG_DATA) {
                                props.answers[props.counter] = CORRECT
                            } else {
                                props.answers[props.counter] = WRONG
                            }
                            props.setAnswers(props.answers)
                        }
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
                        onClick = {
                            // FixMe: Duplication
                            props.setSelection(ANSWER)
                            props.setCounter(props.counter + 1)
                            if (pokemon.type == POKEMON) {
                                props.answers[props.counter] = CORRECT
                            } else {
                                props.answers[props.counter] = WRONG
                            }
                            props.setAnswers(props.answers)
                        }
                        style = jso {
                            fontFamily = "'Pokemon Solid', sans-serif".unsafeCast<FontFamily>()
                            color = "yellow".unsafeCast<Color>()
                            display = "inline".unsafeCast<Display>()
                        }
                        +"Pokémon?"
                    }
                }
            }
        }
    }
}

external interface QuestionCard : Props {
    var counter: Int
    var setCounter: StateSetter<Int>

    var setSelection: StateSetter<Selection>

    var setAnswers: StateSetter<MutableList<Answer>>
    var answers: MutableList<Answer>

    var setPokemonId: StateSetter<Int>
    var pokemonId: Int

    var setUniqueRandom: StateSetter<List<Int>>
    var uniqueRandom: List<Int>
}
