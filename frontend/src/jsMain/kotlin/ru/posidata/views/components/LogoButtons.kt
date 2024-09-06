/**
 * Beautiful logos with effects for SAVE and VULN services
 */

package ru.posidata.views.components

import react.ChildrenBuilder
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.span
import web.cssom.*

/**
 * Wrapper for css style for a glowing neon text, see .glow.scss
 */
fun ChildrenBuilder.neonLightingText(input: String) {
    button {
        className = ClassName("glowing-btn")
        span {
            className = ClassName("glowing-txt")
            +input
        }
    }
}
