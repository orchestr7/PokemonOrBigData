package ru.posidata.views.main

import js.objects.jso
import react.FC
import react.Props
import react.StateSetter
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.a
import ru.posidata.views.utils.internals.Answer.CORRECT
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.h5
import react.dom.html.ReactHTML.img
import ru.posidata.views.utils.externals.fontawesome.faGithub
import ru.posidata.views.utils.externals.fontawesome.fontAwesomeIcon
import ru.posidata.views.utils.internals.Answer
import ru.posidata.views.utils.internals.Answer.NONE
import ru.posidata.views.utils.internals.Selection
import web.cssom.*

val resultCard = FC<ResultProps> { props ->
    val correctAnswers = props.answers.count { it == CORRECT }

    div {
        className = ClassName("col-12 text-center")
        style = jso {
            zIndex = "1000".unsafeCast<ZIndex>()
        }
        h1 {
            className = ClassName("text-center")
            style = jso {
                color = "yellow".unsafeCast<Color>()
                display = "inline".unsafeCast<Display>()
            }
            +"$correctAnswers"
        }
        h2 {
            style = jso {
                color = "white".unsafeCast<Color>()
                display = "inline".unsafeCast<Display>()
            }
            +"/${props.answers.size}"
        }

        val textMeme = when (correctAnswers) {
            in 0..3 -> Pair("А ты точно пришел на SmartData?", "choose-you.webp")
            in 4..8 -> Pair("Тебе надо поисследовать мир Даты (и Покемонов)", "choose-you.webp")
            in 9..11 -> Pair("Ты хорош, но не идеален", "choose-you.webp")
            12 -> Pair("Я выбираю тебя!", "choose-you.webp")
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
                }
                +"Еще раз!"
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


external interface ResultProps : Props {
    var counter: Int
    var answers: MutableList<Answer>
    var setSelection: StateSetter<Selection>
    var setCounter: StateSetter<Int>
    var setAnswers: StateSetter<MutableList<Answer>>
    var setUniqueRandom: StateSetter<List<Int>>
}