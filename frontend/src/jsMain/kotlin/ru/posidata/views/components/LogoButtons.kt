/**
 * Beautiful logos with effects for SAVE and VULN services
 */

package ru.posidata.views.components

import react.ChildrenBuilder
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.span
import web.cssom.*


fun ChildrenBuilder.neonLightingText(input: String) {
    button {
        className = ClassName("glowing-btn")
        span {
            className = ClassName("glowing-txt")
            +input
        }
    }
}

