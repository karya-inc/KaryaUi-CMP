package com.daiatech.app.ui.util

object Constants {
    val allowedHtmlTags = setOf(
        "b", "i", "u", "strong", "em", "font", "br", "p", "span",
        "div", "ol", "ul", "li", "h1", "h2", "h3", "h4", "h5"
    )
    val htmlTagMatchRegex = Regex("<(/?)(\\w+)([^>]*)>")
}
