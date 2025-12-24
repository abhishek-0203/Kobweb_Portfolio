package com.mano.ashwa.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.mano.ashwa.sections.BSHeader
import com.mano.ashwa.theme.GlobalStyles
import com.mano.ashwa.theme.ThemeState
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Div
import com.mano.ashwa.sections.Footer
import com.mano.ashwa.AppStyle

val PageContentStyle = CssStyle {
    base {
        Modifier.fillMaxWidth()
            .padding(leftRight = 2.cssRem, top = 5.cssRem)
            .flexGrow(1)
    }
}

class PageLayoutData(val title: String)

@Composable
@Layout
fun PageLayout(ctx: PageContext, content: @Composable ColumnScope.() -> Unit) {
    val data = ctx.data.getValue<PageLayoutData>()

    // Load theme from localStorage on first render
    LaunchedEffect(Unit) {
        ThemeState.loadFromLocalStorage()
    }

    // Get current color mode from ThemeState
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK

    // Inject global styles for animations and enhancements
    GlobalStyles()

    LaunchedEffect(data.title, colorMode) {
        document.title = data.title

        // Theme-aware background
        val bgColor = if (isDark) "#0f172a" else "#f8fafc"
        val bgGradient = if (isDark) {
            "linear-gradient(135deg, #0f172a 0%, #1e293b 50%, #0f172a 100%)"
        } else {
            "linear-gradient(135deg, #f8fafc 0%, #e2e8f0 50%, #f8fafc 100%)"
        }
        val textColor = if (isDark) "#FFFFFF" else "#0f172a"

        document.body?.style?.apply {
            backgroundColor = bgColor
            setProperty("background", bgGradient)
            setProperty("background-attachment", "fixed")
            color = textColor
            setProperty("transition", "all 0.3s ease")
        }

        // Inject theme-aware global styles for form controls
        val existingStyle = document.getElementById("theme-input-style")
        existingStyle?.remove()

        val s = document.createElement("style")
        s.setAttribute("id", "theme-input-style")
        s.textContent = if (isDark) {
            """
            input, textarea, select, .form-control, .bs-input, .bs-textarea {
              background-color: rgba(15, 23, 42, 0.8) !important;
              color: #ffffff !important;
              border: 1px solid rgba(255, 255, 255, 0.1) !important;
              border-radius: 8px !important;
              transition: all 0.2s ease !important;
            }
            input:focus, textarea:focus, select:focus {
              border-color: rgba(139, 92, 246, 0.5) !important;
              box-shadow: 0 0 0 3px rgba(139, 92, 246, 0.15) !important;
              outline: none !important;
            }
            ::placeholder { color: #64748b !important; }
            button, .kotlinbs-button { color: #ffffff !important; }
            
            /* Card styles for dark mode */
            .theme-card {
              background: linear-gradient(135deg, rgba(30, 41, 59, 0.8) 0%, rgba(15, 23, 42, 0.9) 100%) !important;
              border: 1px solid rgba(255, 255, 255, 0.08) !important;
              color: #ffffff !important;
            }
            """.trimIndent()
        } else {
            """
            input, textarea, select, .form-control, .bs-input, .bs-textarea {
              background-color: rgba(255, 255, 255, 0.9) !important;
              color: #0f172a !important;
              border: 1px solid rgba(0, 0, 0, 0.1) !important;
              border-radius: 8px !important;
              transition: all 0.2s ease !important;
            }
            input:focus, textarea:focus, select:focus {
              border-color: rgba(99, 102, 241, 0.5) !important;
              box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.15) !important;
              outline: none !important;
            }
            ::placeholder { color: #94a3b8 !important; }
            button, .kotlinbs-button { color: #0f172a !important; }
            
            /* Card styles for light mode */
            .theme-card {
              background: rgba(255, 255, 255, 0.95) !important;
              border: 1px solid rgba(0, 0, 0, 0.08) !important;
              color: #0f172a !important;
              box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08) !important;
            }
            """.trimIndent()
        }
        document.head?.appendChild(s)

        // Inject download helper JS
        if (document.getElementById("download-resume-js") == null) {
            val script = document.createElement("script")
            script.setAttribute("id", "download-resume-js")
            script.setAttribute("src", "/download-resume.js")
            script.setAttribute("defer", "true")
            document.head?.appendChild(script)
        }
    }

    // Theme-aware content background
    val contentBgColor = if (isDark) AppStyle.CONTENT_BACKGROUND_COLOR else "#f1f5f9"

    // Main container with flex layout
    Div(attrs = {
        style {
            property("display", "flex")
            property("flex-direction", "column")
            property("min-height", "100vh")
            property("width", "100%")
            property("transition", "all 0.3s ease")
        }
    }) {
        // Header with theme toggle
        BSHeader(
            ctx = ctx,
            colorMode = colorMode,
            onToggleColorMode = { ThemeState.toggle() }
        )

        // Main content area - grows to fill available space
        Column(
            Modifier.fillMaxWidth()
                .padding(leftRight = 2.cssRem, top = 5.cssRem)
                .backgroundColor(org.jetbrains.compose.web.css.Color(contentBgColor))
                .flexGrow(1)
        ) {
            content()
        }

        // Footer - always at bottom
        Footer(colorMode)
    }
}
