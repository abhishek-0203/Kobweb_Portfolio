package com.mano.ashwa.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.mano.ashwa.sections.BSHeader
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import kotlinx.browser.document
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.css.vh
import com.mano.ashwa.sections.Footer
import org.jetbrains.compose.web.css.DisplayStyle
import com.mano.ashwa.AppStyle

val PageContentStyle = CssStyle {
    base {
        Modifier.fillMaxSize()
            .padding(leftRight = 2.cssRem, top = 4.cssRem)
            .backgroundColor(org.jetbrains.compose.web.css.Color(AppStyle.CONTENT_BACKGROUND_COLOR))
            //.backgroundColor(org.jetbrains.compose.web.css.Color("#ffcccc")) // Debug: light red background
    }
}

class PageLayoutData(val title: String)

@Composable
@Layout
fun PageLayout(ctx: PageContext, content: @Composable ColumnScope.() -> Unit) {
    val data = ctx.data.getValue<PageLayoutData>()
    LaunchedEffect(data.title) {
        document.title = data.title
        // Ensure the overall page background and text color are dark-themed
        document.body?.style?.backgroundColor = AppStyle.CONTENT_BACKGROUND_COLOR
        document.body?.style?.color = "#FFFFFF"

        // Inject a small global style to darken native inputs/textarea and form controls so they match the dark theme.
        if (document.getElementById("dark-input-style") == null) {
            val s = document.createElement("style")
            s.setAttribute("id", "dark-input-style")
            s.textContent = """
                input, textarea, select, .form-control, .bs-input, .bs-textarea {
                  background-color: #121212 !important;
                  color: #ffffff !important;
                  border-color: #444 !important;
                }
                ::placeholder { color: #9a9a9a !important; }
                button, .kotlinbs-button { color: #ffffff !important; }
            """.trimIndent()
            document.head?.appendChild(s)
        }

        // Inject download helper JS (forceDownload) once so anchors can call it
        if (document.getElementById("download-resume-js") == null) {
            val script = document.createElement("script")
            script.setAttribute("id", "download-resume-js")
            script.setAttribute("src", "/download-resume.js")
            // load asynchronously
            script.setAttribute("defer", "true")
            document.head?.appendChild(script)
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .minHeight(100.vh)
            .display(DisplayStyle.Grid) // Ensure grid context
            .gridTemplateRows { size(1.fr); size(minContent) },
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier.fillMaxWidth(100.percent).fillMaxHeight(100.percent),
        ) {
            //NavHeader()

            BSHeader(ctx) // Header with navigation links
            Div(PageContentStyle.toAttrs()) {
                content() // Main page content
            }
            Footer() // Footer at the bottom
        }
    }
}
