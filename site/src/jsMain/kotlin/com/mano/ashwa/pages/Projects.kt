package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.components.widgets.ProjectCardView
import com.mano.ashwa.data.ProjectsData
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
import com.varabyte.kobweb.silk.components.icons.fa.FaRocket
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initMyProjectsPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData(AppStrings.PROJECTS_PAGE_TITLE))
}

@Page(Routes.PROJECT)
@Layout(".components.layouts.PageLayout")
@Composable
fun Projects() {
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
        ProjectsHeader()

        // Projects Grid
        Div(attrs = {
            style {
                property("display", "grid")
                property("grid-template-columns", "repeat(auto-fit, minmax(350px, 1fr))")
                property("gap", "28px")
                property("margin-top", "48px")
            }
        }) {
            ProjectsData.allProjects.forEachIndexed { index, project ->
                // Wrap each card with animation delay
                Div(attrs = {
                    style {
                        property("animation", "fadeInUp 0.6s ease-out ${index * 0.15}s both")
                    }
                }) {
                    ProjectCardView(project)
                }
            }
        }

        // Stats Section
        ProjectsStats()
    }
}

@Composable
private fun ProjectsHeader() {
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK

    // Theme-aware colors
    val badgeBg = if (isDark) "rgba(244, 114, 182, 0.15)" else "rgba(244, 114, 182, 0.1)"
    val badgeBorder = if (isDark) "rgba(244, 114, 182, 0.3)" else "rgba(244, 114, 182, 0.25)"
    val badgeTextColor = if (isDark) "#f472b6" else "#db2777"
    val titleGradient = if (isDark) {
        "linear-gradient(135deg, #ffffff 0%, #f472b6 50%, #a78bfa 100%)"
    } else {
        "linear-gradient(135deg, #1e293b 0%, #db2777 50%, #7c3aed 100%)"
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
            FaRocket(size = IconSize.SM, modifier = Modifier.styleModifier { property("color", badgeTextColor) })
            SpanText(
                "Featured Work",
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
            Text("My Projects")
        }

        // Subtitle
        P(attrs = {
            style {
                property("color", subtitleColor)
                property("font-size", "18px")
                property("max-width", "650px")
                property("margin", "0")
                property("line-height", "1.6")
            }
        }) {
            Text("A showcase of my work ranging from mobile apps to full-stack web applications, demonstrating expertise across multiple technologies.")
        }

        // Decorative line
        Div(attrs = {
            style {
                property("width", "80px")
                property("height", "4px")
                property("background", "linear-gradient(90deg, #f472b6, #a855f7)")
                property("border-radius", "2px")
                property("margin-top", "24px")
            }
        })
    }
}

@Composable
private fun ProjectsStats() {
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK

    val statsBg = if (isDark) {
        "linear-gradient(135deg, rgba(30, 41, 59, 0.6) 0%, rgba(15, 23, 42, 0.8) 100%)"
    } else {
        "linear-gradient(135deg, rgba(255, 255, 255, 0.9) 0%, rgba(241, 245, 249, 0.95) 100%)"
    }
    val statsBorder = if (isDark) "rgba(255, 255, 255, 0.08)" else "rgba(0, 0, 0, 0.08)"

    Div(attrs = {
        style {
            property("display", "flex")
            property("justify-content", "center")
            property("gap", "48px")
            property("margin-top", "60px")
            property("padding", "32px")
            property("background", statsBg)
            property("border-radius", "20px")
            property("border", "1px solid $statsBorder")
            property("flex-wrap", "wrap")
            if (!isDark) {
                property("box-shadow", "0 8px 30px rgba(0, 0, 0, 0.06)")
            }
        }
    }) {
        StatItem("${ProjectsData.allProjects.size}+", "Projects", isDark)
        StatItem("5+", "Technologies", isDark)
        StatItem("100%", "Passion", isDark)
    }
}

@Composable
private fun StatItem(value: String, label: String, isDark: Boolean) {
    val labelColor = if (isDark) "#64748b" else "#64748b"
    val valueGradient = if (isDark) {
        "linear-gradient(135deg, #f472b6 0%, #a78bfa 100%)"
    } else {
        "linear-gradient(135deg, #db2777 0%, #7c3aed 100%)"
    }

    Div(attrs = {
        style {
            property("display", "flex")
            property("flex-direction", "column")
            property("align-items", "center")
            property("gap", "4px")
            property("min-width", "100px")
        }
    }) {
        SpanText(
            value,
            modifier = Modifier.styleModifier {
                property("font-size", "36px")
                property("font-weight", "700")
                property("background", valueGradient)
                property("-webkit-background-clip", "text")
                property("-webkit-text-fill-color", "transparent")
                property("background-clip", "text")
            }
        )
        SpanText(
            label,
            modifier = Modifier.styleModifier {
                property("font-size", "14px")
                property("color", labelColor)
                property("font-weight", "500")
                property("text-transform", "uppercase")
                property("letter-spacing", "1px")
            }
        )
    }
}
