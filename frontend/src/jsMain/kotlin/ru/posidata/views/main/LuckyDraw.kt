package ru.posidata.views.main

import js.objects.jso
import kotlinx.browser.window
import react.*
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h1
import ru.posidata.common.UserForSerializationDTO
import ru.posidata.views.utils.externals.particles.Particles
import ru.posidata.views.utils.externals.slotmachine.Slot
import ru.posidata.views.utils.internals.*
import web.cssom.*

val luckyDrawCard = FC<LuckyDrawProps> { _ ->
    val (luckyUserName, setLuckyUserName) = useState<String?>(null)

    val getUser = useDeferredRequest {
        val response = get(
            url = "${window.location.origin}/api/random",
            params = jso<dynamic> {},
            headers = jsonHeaders,
            loadingHandler = ::noopLoadingHandler,
            responseHandler = ::noopResponseHandler,
        )
        setLuckyUserName(response.decodeFromJsonString<UserForSerializationDTO>().username)
    }
    Particles::class.react {
        id = "tsparticles"
        url = "${window.location.origin}/particles.json"
    }

    ReactHTML.div {
        className = ClassName("full-width-container")
        id = "back"
        ReactHTML.div {
            className = ClassName("row justify-content-center align-items-center text-center")
            style = jso {
                minHeight = "100vh".unsafeCast<MinHeight>()
            }

            h1 {
                className = ClassName(" justify-content-center align-items-center text-center")
                style = jso {
                    fontFamily = "'Inter', sans-serif".unsafeCast<FontFamily>()
                    fontSize = 200.unsafeCast<FontSize>()
                    color = "white".unsafeCast<Color>()
                }
                if (luckyUserName != null) {
                    Slot {
                        startValue = "posidata"
                        value = luckyUserName
                        speed = 3.0
                        duration = 7.0
                    }
                }
            }

            div {
                className = ClassName("col-5 zIndex1000")
                button {
                    +"Погнали, разыграем кусок ДСП!"
                    className = ClassName("btn btn-outline-info zIndex1000")
                    onClick = {
                        getUser()
                    }
                }
            }
        }
    }
}

external interface LuckyDrawProps : Props
