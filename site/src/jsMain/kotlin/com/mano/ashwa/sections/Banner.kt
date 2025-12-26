package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.mano.ashwa.components.rememberAnimatedText
import com.mano.ashwa.data.SocialLinks
import com.mano.ashwa.navigation.Routes
import com.mano.ashwa.styles.bannerStyle
import com.mano.ashwa.styles.upDownAnim
import com.mano.ashwa.styles.zoomIn
import com.mano.ashwa.theme.ThemeState
import com.mano.ashwa.utils.Assets
import com.mano.ashwa.utils.atBreakpointMd
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.*
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.attributes.ATarget
import org.jetbrains.compose.web.attributes.target
import org.jetbrains.compose.web.dom.*

@Composable
fun Banner() {
    // Get theme from ThemeState
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK

    val animatedText = rememberAnimatedText(
        toRotate = listOf("Full Stack Developer", "Kotlin Enthusiast", "AI Explorer", "Backend Developer"),
        period = 2000
    )

    // Theme-aware colors
    val bgGradient = if (isDark) {
        "linear-gradient(135deg, rgba(15, 23, 42, 0.95) 0%, rgba(30, 41, 59, 0.9) 50%, rgba(15, 23, 42, 0.95) 100%)"
    } else {
        "linear-gradient(135deg, rgba(248, 250, 252, 0.98) 0%, rgba(241, 245, 249, 0.95) 50%, rgba(248, 250, 252, 0.98) 100%)"
    }

    // Main banner container with enhanced background
    Box(
        modifier = bannerStyle.toModifier()
            .id("home")
            .styleModifier {
                property("background", bgGradient)
                property("position", "relative")
                property("overflow", "hidden")
            }
    ) {
        // Animated background particles/shapes
        BackgroundEffects(isDark)

        SimpleGrid(
            numColumns(base = 1, md = 2),
            modifier = Modifier.fillMaxWidth().styleModifier {
                property("position", "relative")
                property("z-index", "1")
            }
        ) {
            BannerText(animatedText.value, isDark)

            // Hero image with glow effect
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // Theme-aware SVG illustration
                BannerIllustration(isDark)
            }
        }
    }
}

@Composable
private fun BannerIllustration(isDark: Boolean) {
    val glowColor = if (isDark) "rgba(139, 92, 246, 0.3)" else "rgba(99, 102, 241, 0.2)"

    // Use Image for main illustration, fallback to SVG
    Image(
        src = Assets.HeaderImg,
        modifier = Modifier
            .width(100.percent)
            .padding(0.px atBreakpointMd 30.px)
            .height(auto)
            .styleModifier {
                property("filter", "drop-shadow(0 0 40px $glowColor)")
            }
            .animation(
                zoomIn.toAnimation(
                    duration = 1.s,
                    timingFunction = AnimationTimingFunction.Ease,
                    iterationCount = AnimationIterationCount.of(1)
                ),
                upDownAnim.toAnimation(
                    duration = 3.s,
                    direction = AnimationDirection.Alternate,
                    iterationCount = AnimationIterationCount.Infinite
                )
            )
    )
}

@Composable
private fun BackgroundEffects(isDark: Boolean) {
    val orb1Color = if (isDark) "rgba(99, 102, 241, 0.15)" else "rgba(99, 102, 241, 0.08)"
    val orb2Color = if (isDark) "rgba(167, 139, 250, 0.15)" else "rgba(167, 139, 250, 0.08)"
    val orb3Color = if (isDark) "rgba(244, 114, 182, 0.1)" else "rgba(244, 114, 182, 0.05)"

    // Floating gradient orbs
    Div(attrs = {
        style {
            property("position", "absolute")
            property("top", "10%")
            property("left", "10%")
            property("width", "300px")
            property("height", "300px")
            property("background", "radial-gradient(circle, $orb1Color 0%, transparent 70%)")
            property("border-radius", "50%")
            property("filter", "blur(40px)")
            property("animation", "float 8s ease-in-out infinite")
            property("pointer-events", "none")
        }
    })
    Div(attrs = {
        style {
            property("position", "absolute")
            property("bottom", "20%")
            property("right", "15%")
            property("width", "250px")
            property("height", "250px")
            property("background", "radial-gradient(circle, $orb2Color 0%, transparent 70%)")
            property("border-radius", "50%")
            property("filter", "blur(40px)")
            property("animation", "float 6s ease-in-out infinite reverse")
            property("pointer-events", "none")
        }
    })
    Div(attrs = {
        style {
            property("position", "absolute")
            property("top", "50%")
            property("right", "30%")
            property("width", "200px")
            property("height", "200px")
            property("background", "radial-gradient(circle, $orb3Color 0%, transparent 70%)")
            property("border-radius", "50%")
            property("filter", "blur(40px)")
            property("animation", "float 10s ease-in-out infinite")
            property("pointer-events", "none")
        }
    })

    // Decorative grid pattern for light mode
    if (!isDark) {
        Div(attrs = {
            style {
                property("position", "absolute")
                property("inset", "0")
                property("background-image", "radial-gradient(rgba(99, 102, 241, 0.1) 1px, transparent 1px)")
                property("background-size", "30px 30px")
                property("opacity", "0.5")
                property("pointer-events", "none")
            }
        })
    }
}

@Composable
fun BannerText(text: String, isDark: Boolean = true) {
    val textPrimary = if (isDark) "#e2e8f0" else "#1e293b"
    val textSecondary = if (isDark) "#94a3b8" else "#475569"
    val accentColor = if (isDark) "#a78bfa" else "#7c3aed"
    val badgeBg = if (isDark) {
        "linear-gradient(135deg, rgba(99, 102, 241, 0.2) 0%, rgba(139, 92, 246, 0.2) 100%)"
    } else {
        "linear-gradient(135deg, rgba(99, 102, 241, 0.1) 0%, rgba(139, 92, 246, 0.15) 100%)"
    }
    val badgeBorder = if (isDark) "rgba(139, 92, 246, 0.3)" else "rgba(99, 102, 241, 0.3)"
    val badgeTextColor = if (isDark) "#a5b4fc" else "#6366f1"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.px atBreakpointMd 50.px)
            .gap(20.px)
    ) {
        // Welcome badge with enhanced styling
        Div(attrs = {
            style {
                property("display", "inline-flex")
                property("align-items", "center")
                property("gap", "8px")
                property("padding", "8px 16px")
                property("background", badgeBg)
                property("border", "1px solid $badgeBorder")
                property("border-radius", "25px")
                property("backdrop-filter", "blur(10px)")
                property("animation", "fadeInUp 0.8s ease-out")
            }
        }) {
            Span(attrs = {
                style {
                    property("width", "8px")
                    property("height", "8px")
                    property("background", "#22c55e")
                    property("border-radius", "50%")
                    property("animation", "pulse 2s infinite")
                }
            })
            SpanText(
                "Available for opportunities",
                modifier = Modifier.styleModifier {
                    property("color", badgeTextColor)
                    property("font-size", "14px")
                    property("font-weight", "500")
                    property("letter-spacing", "0.5px")
                }
            )
        }

        // Main heading with animated text
        H1(
            attrs = Modifier
                .fontSize(72.px atBreakpointMd 48.px)
                .fontWeight(800)
                .letterSpacing((-1).px)
                .lineHeight(1.1)
                .margin(top = 16.px, bottom = 16.px)
                .display(DisplayStyle.Block)
                .styleModifier {
                    property("animation", "fadeInUp 0.8s ease-out 0.2s both")
                }
                .toAttrs()
        ) {
            SpanText(
                text = "Hi, I'm ",
                modifier = Modifier.styleModifier {
                    property("color", textPrimary)
                }
            )
            Br()
            SpanText(
                text = "Abhishek ",
                modifier = Modifier.styleModifier {
                    property("background", "linear-gradient(90deg, #6366f1, #a855f7)")
                    property("-webkit-background-clip", "text")
                    property("-webkit-text-fill-color", "transparent")
                    property("background-clip", "text")
                }
            )
            SpanText(
                text = "Verma",
                modifier = Modifier.styleModifier {
                    property("color", textPrimary)
                }
            )
        }

        // Role with typing animation
        Div(attrs = {
            style {
                property("display", "flex")
                property("align-items", "center")
                property("gap", "12px")
                property("animation", "fadeInUp 0.8s ease-out 0.4s both")
            }
        }) {
            // Decorative line
            Div(attrs = {
                style {
                    property("width", "40px")
                    property("height", "3px")
                    property("background", "linear-gradient(90deg, #6366f1, #a855f7)")
                    property("border-radius", "2px")
                }
            })
            SpanText(
                text = text,
                modifier = Modifier.styleModifier {
                    property("color", accentColor)
                    property("font-size", "24px")
                    property("font-weight", "600")
                }
            )
            // Cursor
            Span(attrs = {
                style {
                    property("display", "inline-block")
                    property("width", "3px")
                    property("height", "28px")
                    property("background", accentColor)
                    property("animation", "blink 1s infinite")
                }
            })
        }

        // Description
        P(attrs = {
            style {
                property("color", textSecondary)
                property("font-size", "18px")
                property("line-height", "1.7")
                property("max-width", "550px")
                property("margin", "16px 0 0 0")
                property("animation", "fadeInUp 0.8s ease-out 0.6s both")
            }
        }) {
            Text("A motivated and quick-learning developer passionate about the intersection of ")
            Span(attrs = { style { property("color", if (isDark) "#60a5fa" else "#3b82f6"); property("font-weight", "600") } }) {
                Text("AI")
            }
            Text(" and ")
            Span(attrs = { style { property("color", if (isDark) "#2dd4bf" else "#0d9488"); property("font-weight", "600") } }) {
                Text("software development")
            }
            Text(", continuously learning ")
            Span(attrs = { style { property("color", if (isDark) "#a78bfa" else "#7c3aed"); property("font-weight", "600") } }) {
                Text("Kotlin, GoLang")
            }
            Text(" and machine learning to create smart and impactful solutions.")
        }

        // CTA Buttons
        Div(attrs = {
            style {
                property("display", "flex")
                property("gap", "16px")
                property("margin-top", "24px")
                property("flex-wrap", "wrap")
                property("animation", "fadeInUp 0.8s ease-out 0.8s both")
            }
        }) {
            // Primary CTA - View Projects
            A(href = Routes.PROJECT, attrs = {
                style {
                    property("display", "inline-flex")
                    property("align-items", "center")
                    property("gap", "8px")
                    property("padding", "14px 28px")
                    property("background", "linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%)")
                    property("color", "#ffffff")
                    property("font-size", "16px")
                    property("font-weight", "600")
                    property("border-radius", "12px")
                    property("text-decoration", "none")
                    property("transition", "all 0.3s ease")
                    property("box-shadow", "0 4px 15px rgba(99, 102, 241, 0.4)")
                }
                attr("onmouseenter", "this.style.transform='translateY(-3px)';this.style.boxShadow='0 8px 25px rgba(99, 102, 241, 0.5)'")
                attr("onmouseleave", "this.style.transform='translateY(0)';this.style.boxShadow='0 4px 15px rgba(99, 102, 241, 0.4)'")
            }) {
                FaRocket(size = IconSize.SM)
                Text("View Projects")
            }

            // Secondary CTA - Contact Me
            A(href = SocialLinks.EMAIL, attrs = {
                style {
                    property("display", "inline-flex")
                    property("align-items", "center")
                    property("gap", "8px")
                    property("padding", "14px 28px")
                    property("background", if (isDark) "rgba(255, 255, 255, 0.05)" else "rgba(0, 0, 0, 0.05)")
                    property("color", if (isDark) "#e2e8f0" else "#1e293b")
                    property("font-size", "16px")
                    property("font-weight", "600")
                    property("border", "1px solid ${if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.1)"}")
                    property("border-radius", "12px")
                    property("text-decoration", "none")
                    property("transition", "all 0.3s ease")
                }
                attr("onmouseenter", "this.style.background='${if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.1)"}';this.style.transform='translateY(-3px)'")
                attr("onmouseleave", "this.style.background='${if (isDark) "rgba(255, 255, 255, 0.05)" else "rgba(0, 0, 0, 0.05)"}';this.style.transform='translateY(0)'")
            }) {
                FaEnvelope(size = IconSize.SM)
                Text("Contact Me")
            }
        }

        // Social Links
        Div(attrs = {
            style {
                property("display", "flex")
                property("gap", "12px")
                property("margin-top", "32px")
                property("animation", "fadeInUp 0.8s ease-out 1s both")
            }
        }) {
            SocialIconLink(SocialLinks.GITHUB, isDark) { FaGithub(size = IconSize.LG) }
            SocialIconLink(SocialLinks.LINKEDIN, isDark) { FaLinkedin(size = IconSize.LG) }
            SocialIconLink(SocialLinks.EMAIL, isDark) { FaEnvelope(size = IconSize.LG) }
        }
    }
}

@Composable
private fun SocialIconLink(href: String, isDark: Boolean, icon: @Composable () -> Unit) {
    val bgColor = if (isDark) "rgba(255, 255, 255, 0.05)" else "rgba(0, 0, 0, 0.05)"
    val hoverBgColor = if (isDark) "rgba(139, 92, 246, 0.2)" else "rgba(99, 102, 241, 0.15)"
    val borderColor = if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.1)"
    val hoverBorderColor = if (isDark) "rgba(139, 92, 246, 0.5)" else "rgba(99, 102, 241, 0.5)"
    val textColor = if (isDark) "#94a3b8" else "#64748b"
    val hoverTextColor = if (isDark) "#a78bfa" else "#7c3aed"

    A(href = href, attrs = {
        if (!href.startsWith("mailto:")) {
            target(ATarget.Blank)
            attr("rel", "noopener noreferrer")
        }
        style {
            property("display", "flex")
            property("align-items", "center")
            property("justify-content", "center")
            property("width", "48px")
            property("height", "48px")
            property("background", bgColor)
            property("border", "1px solid $borderColor")
            property("border-radius", "12px")
            property("color", textColor)
            property("text-decoration", "none")
            property("transition", "all 0.3s ease")
        }
        attr("onmouseenter", "this.style.background='$hoverBgColor';this.style.borderColor='$hoverBorderColor';this.style.color='$hoverTextColor';this.style.transform='translateY(-3px)'")
        attr("onmouseleave", "this.style.background='$bgColor';this.style.borderColor='$borderColor';this.style.color='$textColor';this.style.transform='translateY(0)'")
    }) {
        icon()
    }
}

