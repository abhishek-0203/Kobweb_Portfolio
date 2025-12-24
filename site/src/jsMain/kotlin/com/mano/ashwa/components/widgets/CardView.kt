package com.mano.ashwa.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.mano.ashwa.model.ExperienceData
import com.mano.ashwa.model.ProjectData
import com.mano.ashwa.theme.ThemeColors
import com.mano.ashwa.theme.ThemeState
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexWrap
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

// ============================================================================
// Helper Functions
// ============================================================================

/**
 * Converts a Kobweb Color to a CSS-compatible string.
 * Falls back to white if the color string is empty.
 */
private fun Color.toCssString(): String =
    toString().ifBlank { ThemeColors.TEXT_PRIMARY }

/**
 * Determines the accent color for a card, using a subtle fallback for transparent colors.
 */
private fun getAccentColor(color: Color): String =
    if (color == Colors.Transparent) ThemeColors.CARD_BACKGROUND_FALLBACK else color.toCssString()


// ============================================================================
// Card Components
// ============================================================================

/**
 * Displays a skill category card with icon, title, and bullet-point list of skills.
 * Enhanced with hover effects and modern styling.
 */
@Composable
fun SkillCardView(
    title: String,
    skills: List<String>,
    icon: String? = null,
    color: Color = Colors.Transparent
) {
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK
    val accent = getAccentColor(color)

    // Theme-aware colors
    val cardBg = if (isDark) {
        "linear-gradient(135deg, rgba(30, 41, 59, 0.8) 0%, rgba(15, 23, 42, 0.9) 100%)"
    } else {
        "linear-gradient(135deg, rgba(255, 255, 255, 0.95) 0%, rgba(241, 245, 249, 0.98) 100%)"
    }
    val cardBorder = if (isDark) "rgba(255, 255, 255, 0.08)" else "rgba(0, 0, 0, 0.08)"
    val cardShadow = if (isDark) "0 8px 32px rgba(0, 0, 0, 0.2)" else "0 8px 32px rgba(0, 0, 0, 0.08)"
    val hoverShadow = if (isDark) {
        "0 20px 40px rgba(0, 0, 0, 0.3), 0 0 30px ${accent}20"
    } else {
        "0 20px 40px rgba(0, 0, 0, 0.12), 0 0 30px ${accent}15"
    }
    val titleColor = if (isDark) "#ffffff" else "#1e293b"
    val skillColor = if (isDark) "#94a3b8" else "#64748b"

    Div(attrs = {
        style {
            // Glassmorphism background
            property("background", cardBg)
            property("backdrop-filter", "blur(10px)")
            property("-webkit-backdrop-filter", "blur(10px)")
            // Border and accent
            property("border", "1px solid $cardBorder")
            property("border-left", "4px solid $accent")
            property("border-radius", "16px")
            property("padding", "24px")
            // Shadow
            property("box-shadow", cardShadow)
            // Transition for hover
            property("transition", "all 0.3s cubic-bezier(0.4, 0, 0.2, 1)")
            property("cursor", "default")
            property("height", "100%")
        }
        attr("onmouseenter", "this.style.transform='translateY(-8px)';this.style.boxShadow='$hoverShadow'")
        attr("onmouseleave", "this.style.transform='translateY(0)';this.style.boxShadow='$cardShadow'")
    }) {
        Column(Modifier.gap(12.px)) {
            // Header: Icon + Title
            Row(Modifier.gap(12.px)) {
                icon?.let {
                    // Icon with gradient background
                    Div(attrs = {
                        style {
                            property("width", "44px")
                            property("height", "44px")
                            property("background", "linear-gradient(135deg, ${accent}40 0%, ${accent}20 100%)")
                            property("border-radius", "12px")
                            property("display", "flex")
                            property("align-items", "center")
                            property("justify-content", "center")
                            property("font-size", "22px")
                        }
                    }) {
                        Text(it)
                    }
                }
                SpanText(
                    title,
                    Modifier.fontWeight(FontWeight.Bold).fontSize(20.px).styleModifier {
                        property("color", titleColor)
                    }
                )
            }
            // Divider line
            Div(attrs = {
                style {
                    property("width", "100%")
                    property("height", "1px")
                    property("background", "linear-gradient(90deg, ${accent}50, transparent)")
                    property("margin", "4px 0")
                }
            })
            // Skills list with improved styling
            Column(Modifier.gap(6.px)) {
                skills.forEach { skill ->
                    Row(Modifier.gap(8.px)) {
                        // Bullet point with accent color
                        Div(attrs = {
                            style {
                                property("width", "6px")
                                property("height", "6px")
                                property("background", accent)
                                property("border-radius", "50%")
                                property("margin-top", "8px")
                                property("flex-shrink", "0")
                            }
                        })
                        SpanText(skill, Modifier.fontSize(15.px).styleModifier {
                            property("color", skillColor)
                        })
                    }
                }
            }
        }
    }
}

/**
 * Displays an experience card with company info, role, duration, and skills.
 */
@Composable
fun ExperienceCardView(data: ExperienceData) {
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK
    val accent = getAccentColor(data.color)

    // Theme-aware colors
    val cardBg = if (isDark) {
        "linear-gradient(135deg, rgba(30, 41, 59, 0.8) 0%, rgba(15, 23, 42, 0.9) 100%)"
    } else {
        "linear-gradient(135deg, rgba(255, 255, 255, 0.95) 0%, rgba(241, 245, 249, 0.98) 100%)"
    }
    val cardBorder = if (isDark) "rgba(255, 255, 255, 0.08)" else "rgba(0, 0, 0, 0.08)"
    val cardShadow = if (isDark) "0 8px 32px rgba(0, 0, 0, 0.3)" else "0 8px 32px rgba(0, 0, 0, 0.08)"
    val titleColor = if (isDark) "#ffffff" else "#1e293b"
    val secondaryColor = if (isDark) "#94a3b8" else "#64748b"

    Div(attrs = {
        style {
            property("background", cardBg)
            property("backdrop-filter", "blur(10px)")
            property("-webkit-backdrop-filter", "blur(10px)")
            property("border-left", "4px solid $accent")
            property("border", "1px solid $cardBorder")
            property("border-left", "4px solid $accent")
            property("box-shadow", cardShadow)
            property("transition", "all 0.3s cubic-bezier(0.4, 0, 0.2, 1)")
            property("cursor", "default")
            property("border-radius", "16px")
            property("padding", "20px")
            property("width", "100%")
        }
    }) {
        Column(Modifier.gap(8.px)) {
            // Header: Icon + Company Info
            Row(Modifier.gap(8.px)) {
                if (data.icon.isNotEmpty()) {
                    SpanText(data.icon, Modifier.fontSize(24.px).styleModifier {
                        property("color", titleColor)
                    })
                }
                Column {
                    // Company name and duration
                    Row {
                        SpanText(
                            data.companyName,
                            Modifier.fontWeight(FontWeight.Bold).fontSize(20.px).styleModifier {
                                property("color", titleColor)
                            }
                        )
                        Box(modifier = Modifier.width(10.px))
                        SpanText(
                            "(${data.duration})",
                            Modifier.fontWeight(FontWeight.Bold).fontSize(14.px)
                                .padding(top = 5.px).styleModifier {
                                    property("color", secondaryColor)
                                }
                        )
                    }
                    // Role
                    SpanText(
                        data.role,
                        Modifier.fontWeight(FontWeight.Bold).fontSize(14.px).styleModifier {
                            property("color", titleColor)
                        }
                    )
                }
            }
            // Skills list
            Column {
                data.skills.forEach { skill ->
                    SpanText("• $skill", Modifier.fontSize(16.px).styleModifier {
                        property("color", secondaryColor)
                    })
                }
            }
        }
    }
}

/**
 * Displays a project card with name, description, technologies, and role.
 */
@Composable
fun ProjectCardView(data: ProjectData) {
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK
    val accent = getAccentColor(data.color)

    // Theme-aware colors
    val cardBg = if (isDark) {
        "linear-gradient(135deg, rgba(30, 41, 59, 0.8) 0%, rgba(15, 23, 42, 0.9) 100%)"
    } else {
        "linear-gradient(135deg, rgba(255, 255, 255, 0.95) 0%, rgba(241, 245, 249, 0.98) 100%)"
    }
    val cardBorder = if (isDark) "rgba(255, 255, 255, 0.08)" else "rgba(0, 0, 0, 0.08)"
    val cardShadow = if (isDark) "0 10px 40px rgba(0, 0, 0, 0.25)" else "0 10px 40px rgba(0, 0, 0, 0.08)"
    val hoverShadow = if (isDark) {
        "0 25px 50px rgba(0, 0, 0, 0.35), 0 0 40px ${accent}15"
    } else {
        "0 25px 50px rgba(0, 0, 0, 0.12), 0 0 40px ${accent}10"
    }
    val titleColor = if (isDark) "#ffffff" else "#1e293b"
    val secondaryColor = if (isDark) "#94a3b8" else "#64748b"
    val badgeBg = if (isDark) "rgba(255, 255, 255, 0.08)" else "rgba(0, 0, 0, 0.05)"
    val roleBadgeBg = if (isDark) {
        "linear-gradient(135deg, ${accent}25 0%, ${accent}15 100%)"
    } else {
        "linear-gradient(135deg, ${accent}20 0%, ${accent}10 100%)"
    }
    val roleTextColor = if (isDark) "#e2e8f0" else "#1e293b"

    Div(attrs = {
        style {
            // Glassmorphism background
            property("background", cardBg)
            property("backdrop-filter", "blur(10px)")
            property("-webkit-backdrop-filter", "blur(10px)")
            // Border and accent
            property("border", "1px solid $cardBorder")
            property("border-top", "4px solid $accent")
            property("border-radius", "20px")
            property("padding", "28px")
            // Shadow
            property("box-shadow", cardShadow)
            // Transition for hover
            property("transition", "all 0.3s cubic-bezier(0.4, 0, 0.2, 1)")
            property("cursor", "default")
            property("height", "100%")
            property("display", "flex")
            property("flex-direction", "column")
        }
        attr("onmouseenter", "this.style.transform='translateY(-10px) scale(1.02)';this.style.boxShadow='$hoverShadow'")
        attr("onmouseleave", "this.style.transform='translateY(0) scale(1)';this.style.boxShadow='$cardShadow'")
    }) {
        Column(Modifier.gap(12.px)) {
            // Header: Icon + Project Info
            Row(Modifier.gap(12.px)) {
                if (data.icon.isNotEmpty()) {
                    // Icon with gradient background
                    Div(attrs = {
                        style {
                            property("width", "52px")
                            property("height", "52px")
                            property("background", "linear-gradient(135deg, ${accent}40 0%, ${accent}20 100%)")
                            property("border-radius", "14px")
                            property("display", "flex")
                            property("align-items", "center")
                            property("justify-content", "center")
                            property("font-size", "26px")
                            property("flex-shrink", "0")
                        }
                    }) {
                        Text(data.icon)
                    }
                }
                Column(Modifier.gap(4.px)) {
                    SpanText(
                        data.name,
                        Modifier.fontWeight(FontWeight.Bold).fontSize(22.px).styleModifier {
                            property("color", titleColor)
                        }
                    )
                    // Duration badge
                    Div(attrs = {
                        style {
                            property("display", "inline-flex")
                            property("padding", "4px 10px")
                            property("background", badgeBg)
                            property("border-radius", "12px")
                            property("font-size", "12px")
                            property("color", secondaryColor)
                            property("font-weight", "500")
                        }
                    }) {
                        Text(data.duration)
                    }
                }
            }

            // Role badge
            Div(attrs = {
                style {
                    property("display", "inline-flex")
                    property("align-items", "center")
                    property("gap", "6px")
                    property("padding", "6px 12px")
                    property("background", roleBadgeBg)
                    property("border", "1px solid ${accent}40")
                    property("border-radius", "8px")
                    property("font-size", "13px")
                    property("color", roleTextColor)
                    property("font-weight", "500")
                    property("width", "fit-content")
                }
            }) {
                Text("👨‍💻 ${data.role}")
            }

            // Description
            Div(attrs = {
                style {
                    property("flex-grow", "1")
                    property("margin-top", "8px")
                }
            }) {
                SpanText(data.description, Modifier.fontSize(15.px).styleModifier {
                    property("color", secondaryColor)
                    property("line-height", "1.6")
                })
            }

            // Technologies
            ChipLayout(data.technologies)
        }
    }
}

// ============================================================================
// Chip Components
// ============================================================================

/**
 * Displays a horizontal flex-wrap layout of technology chips with modern styling.
 */
@Composable
fun ChipLayout(
    items: List<String>,
    chipColor: String = ThemeColors.CHIP_DEFAULT
) {
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK

    val containerBg = if (isDark) {
        "linear-gradient(135deg, rgba(15, 23, 42, 0.6) 0%, rgba(30, 41, 59, 0.4) 100%)"
    } else {
        "linear-gradient(135deg, rgba(241, 245, 249, 0.8) 0%, rgba(226, 232, 240, 0.6) 100%)"
    }
    val containerBorder = if (isDark) "rgba(255, 255, 255, 0.05)" else "rgba(0, 0, 0, 0.05)"

    Div({
        style {
            display(DisplayStyle.Flex)
            flexWrap(FlexWrap.Wrap)
            property("gap", "8px")
            property("margin-top", "12px")
            property("padding", "12px")
            width(100.percent)
            property("background", containerBg)
            property("border-radius", "12px")
            property("border", "1px solid $containerBorder")
        }
    }) {
        items.forEach { tech ->
            Chip(tech, chipColor)
        }
    }
}

/**
 * Displays a single technology/skill chip with gradient styling and hover effect.
 */
@Composable
fun Chip(
    text: String,
    color: String = ThemeColors.CHIP_DEFAULT
) {
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK

    val chipBg = if (isDark) {
        "linear-gradient(135deg, rgba(99, 102, 241, 0.3) 0%, rgba(139, 92, 246, 0.3) 100%)"
    } else {
        "linear-gradient(135deg, rgba(99, 102, 241, 0.15) 0%, rgba(139, 92, 246, 0.2) 100%)"
    }
    val chipBorder = if (isDark) "rgba(139, 92, 246, 0.4)" else "rgba(99, 102, 241, 0.3)"
    val chipTextColor = if (isDark) "#e0e7ff" else "#4338ca"
    val chipShadow = if (isDark) "0 2px 8px rgba(99, 102, 241, 0.15)" else "0 2px 8px rgba(99, 102, 241, 0.1)"

    val hoverBg = if (isDark) {
        "linear-gradient(135deg, rgba(99, 102, 241, 0.5) 0%, rgba(139, 92, 246, 0.5) 100%)"
    } else {
        "linear-gradient(135deg, rgba(99, 102, 241, 0.25) 0%, rgba(139, 92, 246, 0.3) 100%)"
    }
    val hoverShadow = if (isDark) "0 4px 12px rgba(99, 102, 241, 0.3)" else "0 4px 12px rgba(99, 102, 241, 0.2)"

    Div({
        style {
            property("background", chipBg)
            property("border", "1px solid $chipBorder")
            property("border-radius", "20px")
            property("padding", "6px 14px")
            property("font-size", "13px")
            property("font-weight", "500")
            property("color", chipTextColor)
            property("letter-spacing", "0.3px")
            property("transition", "all 0.2s ease")
            property("cursor", "default")
            property("box-shadow", chipShadow)
        }
        attr("onmouseenter", "this.style.background='$hoverBg';this.style.transform='translateY(-2px)';this.style.boxShadow='$hoverShadow'")
        attr("onmouseleave", "this.style.background='$chipBg';this.style.transform='translateY(0)';this.style.boxShadow='$chipShadow'")
    }) {
        Text(text)
    }
}
