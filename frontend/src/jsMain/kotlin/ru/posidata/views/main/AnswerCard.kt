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
import ru.posidata.views.components.ResourceType.BIG_DATA
import ru.posidata.views.components.ResourceType.POKEMON
import ru.posidata.views.components.Resources
import ru.posidata.views.utils.internals.Answer
import ru.posidata.views.utils.internals.Selection
import ru.posidata.views.utils.internals.Selection.QUESTION
import ru.posidata.views.utils.internals.Selection.RESULTS
import web.cssom.*

val answerCard = FC<AnswerProps> { props ->
    val pokemon = Resources.getById(props.pokemonId)
    div {
        className = ClassName("row")
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
                            "${pokemon.name}.${if (pokemon.type == POKEMON) "jpeg" else "png"}"
                    style = jso {
                        width = "100%".unsafeCast<Width>()
                        borderRadius = "40px 40px 40px 40px".unsafeCast<BorderRadius>()
                    }
                }
            }
        }

        div {
            className = ClassName("row text-center mt-2 px-0")
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

external interface AnswerProps : Props {
    var setSelection: StateSetter<Selection>
    var counter: Int
    var pokemonId: Int
    var answers: List<Answer>
}