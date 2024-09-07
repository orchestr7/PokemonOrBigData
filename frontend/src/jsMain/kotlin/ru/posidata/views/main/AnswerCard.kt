package ru.posidata.views.main

import js.objects.jso
import react.FC
import react.Props
import react.StateSetter
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.h6
import react.dom.html.ReactHTML.img
import react.useState
import ru.posidata.common.ResourceType.BIG_DATA
import ru.posidata.common.ResourceType.POKEMON
import ru.posidata.common.Resources
import ru.posidata.common.Answer
import ru.posidata.common.Selection
import ru.posidata.common.Selection.QUESTION
import ru.posidata.common.Selection.RESULTS
import web.cssom.*

val answerCard = FC<AnswerProps> { props ->
    var (loading, setLoading) = useState(true)

    val pokemon = Resources.getById(props.pokemonId)
    div {
        style = jso {
            display = (if (loading) "none" else "block").unsafeCast<Display>()
        }
        div {
            className = ClassName("row mb-2")
            div {
                className = ClassName("col-12 text-center")
                h2 {
                    className = ClassName("text-center")
                    style = jso {
                        display = "inline".unsafeCast<Display>()
                    }
                    +"${pokemon.getName()} - это "
                }
                h2 {
                    style = jso {
                        fontFamily = "'Pokemon Solid', sans-serif".unsafeCast<FontFamily>()
                        color = "yellow".unsafeCast<Color>()
                        display = "inline".unsafeCast<Display>()
                    }
                    +(if (pokemon.type == BIG_DATA) "из БигДаты!" else "Pokémon!")
                }
            }
        }

        div {
            className = ClassName("col")
            style = jso {
                minHeight = "33vh".unsafeCast<MinHeight>()
            }
            div {
                className = ClassName("row justify-content-center")
                div {
                    className = ClassName("col-8")
                    img {
                        src = "img/" +
                                "${if (pokemon.type == POKEMON) "pokemons" else "bigdata"}/" +
                                "${pokemon.name}-min.${if (pokemon.type == POKEMON) "jpeg" else "png"}"
                        style = jso {
                            width = "100%".unsafeCast<Width>()
                            borderRadius = "40px 40px 40px 40px".unsafeCast<BorderRadius>()
                            animation = "fadeIn 0.5s".unsafeCast<Animation>()
                        }

                        onLoad = {
                            setLoading(false)
                        }
                    }
                }
            }

            div {
                className = ClassName("row text-center mt-2 mb-1 px-0")
                h6 {
                    +pokemon.description
                }
            }
        }

        div {
            className = ClassName("row justify-content-center")
            div {
                className = ClassName("col-12 text-center")
                style = jso {
                    zIndex = "1000".unsafeCast<ZIndex>()
                }
                button {
                    className = ClassName("btn btn-outline-info")

                    if (props.counter < props.answers.size) {
                        +"Следующий вопрос"
                        onClick = {
                            props.setSelection(QUESTION)
                        }
                    } else {
                        +"Результаты"
                        onClick = {
                            props.setSelection(RESULTS)
                        }
                    }
                }
            }
        }
    }
}

external interface AnswerProps : Props {
    var setSelection: StateSetter<Selection>
    var counter: Int
    var pokemonId: Int
    var answers: List<Answer>
}