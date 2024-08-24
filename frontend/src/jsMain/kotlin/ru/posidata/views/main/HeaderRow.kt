package ru.posidata.views.main

import js.objects.jso
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h2
import react.dom.html.ReactHTML.img
import web.cssom.BoxShadow
import web.cssom.ClassName
import web.cssom.FontFamily
import web.cssom.Width

val headerRow = FC<Props> {
    div {
        className = ClassName("row mt-3 mx-3")
        div {
            className = ClassName("col-2 d-flex align-items-center justify-content-center px-0 ")
            img {
                src = "img/pdata-icon.svg"
                style = jso {
                    width = "100%".unsafeCast<Width>()
                    boxShadow = "10px 10px 20px rgba(0, 0, 0, 0.5)".unsafeCast<BoxShadow>()
                }
            }
        }
        div {
            className = ClassName("col-8 d-flex align-items-center justify-content-center text-white")
            h2 {
                style = jso {
                    fontFamily = "'Inter', sans-serif".unsafeCast<FontFamily>()
                }
                +"Positive Data"
            }
        }

        div {
            className = ClassName("col-2 d-flex align-items-center justify-content-center px-0")
            img {
                src = "img/pokeball.png"
                style = jso {
                    width = "100%".unsafeCast<Width>()
                }
            }
        }
    }
}