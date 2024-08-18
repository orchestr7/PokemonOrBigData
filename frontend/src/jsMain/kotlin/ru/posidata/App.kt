/**
 * Main entrypoint
 */

package ru.posidata


import js.objects.jso
import react.*
import react.dom.client.createRoot

import web.dom.document
import web.html.HTMLElement

import kotlinx.browser.window
import react.router.dom.RouterProvider
import react.router.dom.createBrowserRouter
import ru.posidata.views.components.errorBoundary
import ru.posidata.views.main.mainView
import ru.posidata.views.utils.externals.i18n.initI18n


/**
 * Main component for the whole App
 */
@JsExport
@OptIn(ExperimentalJsExport::class)
val App: FC<Props> = FC {
        RouterProvider {
            router = createBrowserRouter(
                routes = arrayOf(
                    jso {
                        path = "/"
                        element = mainView.create()
                        errorElement = errorBoundary.create()
                    }
                )
            )
        }
}

fun main() {
    /* Workaround for issue: https://youtrack.jetbrains.com/issue/KT-31888 */
    @Suppress("UnsafeCastFromDynamic")
    if (window.asDynamic().__karma__) {
        return
    }
    // this is needed for webpack to include resources
    kotlinext.js.require<dynamic>("../scss/app.scss")
    // this is needed for webpack to include bootstrap
    kotlinext.js.require<dynamic>("bootstrap")
    /*    ReactModal.setAppElement(document.getElementById("wrapper") as HTMLElement)  // required for accessibility in react-modal */

    initI18n()
    val mainDiv = document.getElementById("wrapper") as HTMLElement
    createRoot(mainDiv).render(App.create())
}
