package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.components.widgets.ExperienceCardView
import com.mano.ashwa.data.ExperiencesData
import com.mano.ashwa.navigation.Routes
import com.mano.ashwa.theme.ThemeState
import com.mano.ashwa.utils.AppStrings
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.icons.fa.FaBriefcase
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initExperiencesPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData(AppStrings.EXPERIENCE_PAGE_TITLE))
}

@Page(Routes.EXPERIENCE)
@Layout(".components.layouts.PageLayout")
@Composable
fun Experiences() {
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK

    // Main container
    Div(attrs = {
        style {
            property("width", "100%")
            property("max-width", "1200px")
            property("margin", "0 auto")
            property("padding", "40px 24px")
        }
    }) {
        // Section Header
        ExperiencesHeader(isDark)

        // Experiences Grid
        Div(attrs = {
            style {
                property("display", "grid")
                property("grid-template-columns", "repeat(auto-fit, minmax(320px, 1fr))")
                property("gap", "24px")
                property("margin-top", "48px")
            }
        }) {
            ExperiencesData.allExperiences.forEachIndexed { index, experience ->
                Div(attrs = {
                    style {
                        property("animation", "fadeInUp 0.6s ease-out ${index * 0.15}s both")
                    }
                }) {
                    ExperienceCardView(experience)
                }
            }
        }
    }
}

@Composable
private fun ExperiencesHeader(isDark: Boolean) {
    val badgeBg = if (isDark) "rgba(34, 197, 94, 0.15)" else "rgba(34, 197, 94, 0.1)"
    val badgeBorder = if (isDark) "rgba(34, 197, 94, 0.3)" else "rgba(34, 197, 94, 0.25)"
    val badgeTextColor = if (isDark) "#4ade80" else "#16a34a"
    val titleGradient = if (isDark) {
        "linear-gradient(135deg, #ffffff 0%, #4ade80 50%, #a78bfa 100%)"
    } else {
        "linear-gradient(135deg, #1e293b 0%, #16a34a 50%, #7c3aed 100%)"
    }
    val subtitleColor = if (isDark) "#94a3b8" else "#64748b"

    // Center aligned header
    Div(attrs = {
        style {
            property("display", "flex")
            property("flex-direction", "column")
            property("align-items", "center")
            property("text-align", "center")
        }
    }) {
        // Badge
        Div(attrs = {
            style {
                property("display", "inline-flex")
                property("align-items", "center")
                property("gap", "8px")
                property("padding", "8px 16px")
                property("background", badgeBg)
                property("border", "1px solid $badgeBorder")
                property("border-radius", "25px")
                property("margin-bottom", "16px")
            }
        }) {
            FaBriefcase(size = IconSize.SM, modifier = Modifier.styleModifier { property("color", badgeTextColor) })
            SpanText(
                "Career Journey",
                modifier = Modifier.styleModifier {
                    property("color", badgeTextColor)
                    property("font-size", "14px")
                    property("font-weight", "500")
                }
            )
        }

        // Main Title with gradient
        H2(attrs = {
            style {
                property("font-size", "42px")
                property("font-weight", "700")
                property("margin", "0 0 16px")
                property("background", titleGradient)
                property("-webkit-background-clip", "text")
                property("-webkit-text-fill-color", "transparent")
                property("background-clip", "text")
            }
        }) {
            Text("Work Experience")
        }

        // Subtitle
        P(attrs = {
            style {
                property("color", subtitleColor)
                property("font-size", "18px")
                property("max-width", "600px")
                property("margin", "0")
                property("line-height", "1.6")
            }
        }) {
            Text("A timeline of my professional journey and the impactful projects I've contributed to.")
        }

        // Decorative line
        Div(attrs = {
            style {
                property("width", "80px")
                property("height", "4px")
                property("background", "linear-gradient(90deg, #22c55e, #a855f7)")
                property("border-radius", "2px")
                property("margin-top", "24px")
            }
        })
    }
}
