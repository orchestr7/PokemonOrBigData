package ru.posidata.views.main

import js.objects.jso
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import ru.posidata.views.utils.externals.fontawesome.faThumbsUp
import ru.posidata.views.utils.externals.fontawesome.faXmark
import ru.posidata.views.utils.externals.fontawesome.fontAwesomeIcon
import ru.posidata.views.utils.internals.Answer.NONE
import ru.posidata.views.utils.internals.Answer.WRONG
import ru.posidata.views.utils.internals.Answer.CORRECT
import ru.posidata.views.utils.internals.Selection.QUESTION
import ru.posidata.views.utils.internals.Answer
import ru.posidata.views.utils.internals.Selection
import web.cssom.*

val progressBar = FC<ProgressBarProps> { props ->
    div {
        className = ClassName("row justify-content-center mt-3 mb-3")
        props.answers.forEachIndexed { i, answer ->
            div {
                val bgColor = when (answer) {
                   NONE -> if (QUESTION == props.selection && (i == props.counter)) {
                        "bg-warning"
                    } else {
                        "bg-light"
                    }
                   WRONG -> "bg-danger"
                   CORRECT -> "bg-success-light"

                }
                className = ClassName("card col-1 $bgColor d-flex align-items-center justify-content-center")
                style = jso {
                    minHeight = 1.5.rem
                    when (i) {
                        0 -> {
                            borderTopRightRadius = 0.unsafeCast<BorderTopRightRadius>()
                            borderBottomRightRadius = 0.unsafeCast<BorderBottomRightRadius>()

                        }

                        props.answers.size - 1 -> {
                            borderTopLeftRadius = 0.unsafeCast<BorderTopRightRadius>()
                            borderBottomLeftRadius = 0.unsafeCast<BorderBottomRightRadius>()
                        }

                        else -> {
                            borderTopRightRadius = 0.unsafeCast<BorderTopRightRadius>()
                            borderBottomRightRadius = 0.unsafeCast<BorderBottomRightRadius>()
                            borderTopLeftRadius = 0.unsafeCast<BorderTopRightRadius>()
                            borderBottomLeftRadius = 0.unsafeCast<BorderBottomRightRadius>()
                        }
                    }
                }
                if (i < props.counter) {
                    div {
                        className = ClassName("row justify-content-center")
                        if (props.answers[i] ==CORRECT) {
                            fontAwesomeIcon(faThumbsUp)
                        } else {
                            fontAwesomeIcon(faXmark)
                        }
                    }
                }
            }
        }
    }
}

external interface ProgressBarProps : Props {
    var counter: Int
    var selection: Selection
    var answers: List<Answer>
}
