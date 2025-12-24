package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.components.widgets.SkillCardView
import com.mano.ashwa.data.SkillsData
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
import com.varabyte.kobweb.silk.components.icons.fa.FaCode
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@InitRoute
fun initSkillPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData(AppStrings.SKILLS_PAGE_TITLE))
}

@Page(Routes.SKILL)
@Layout(".components.layouts.PageLayout")
@Composable
fun SkillPage() {
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
        SkillsHeader()

        // Skills Grid
        Div(attrs = {
            style {
                property("display", "grid")
                property("grid-template-columns", "repeat(auto-fit, minmax(280px, 1fr))")
                property("gap", "24px")
                property("margin-top", "48px")
            }
        }) {
            SkillsData.allSkills.forEachIndexed { index, skill ->
                // Wrap each card with animation delay
                Div(attrs = {
                    style {
                        property("animation", "fadeInUp 0.6s ease-out ${index * 0.1}s both")
                    }
                }) {
                    SkillCardView(
                        title = skill.title,
                        skills = skill.skills,
                        icon = skill.icon,
                        color = skill.color
                    )
                }
            }
        }
    }
}

@Composable
private fun SkillsHeader() {
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK

    // Theme-aware colors
    val badgeBg = if (isDark) "rgba(99, 102, 241, 0.15)" else "rgba(99, 102, 241, 0.1)"
    val badgeBorder = if (isDark) "rgba(99, 102, 241, 0.3)" else "rgba(99, 102, 241, 0.25)"
    val badgeTextColor = if (isDark) "#818cf8" else "#6366f1"
    val titleGradient = if (isDark) {
        "linear-gradient(135deg, #ffffff 0%, #60a5fa 50%, #a78bfa 100%)"
    } else {
        "linear-gradient(135deg, #1e293b 0%, #6366f1 50%, #7c3aed 100%)"
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
            FaCode(size = IconSize.SM, modifier = Modifier.styleModifier { property("color", badgeTextColor) })
            SpanText(
                "Technical Expertise",
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
            Text("Skills & Technologies")
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
            Text("A comprehensive toolkit of programming languages, frameworks, and tools I use to build exceptional software solutions.")
        }

        // Decorative line
        Div(attrs = {
            style {
                property("width", "80px")
                property("height", "4px")
                property("background", "linear-gradient(90deg, #6366f1, #a855f7)")
                property("border-radius", "2px")
                property("margin-top", "24px")
            }
        })
    }
}

