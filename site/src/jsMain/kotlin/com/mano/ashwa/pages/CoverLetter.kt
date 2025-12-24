package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.data.SocialLinks
import com.mano.ashwa.navigation.Routes
import com.mano.ashwa.theme.ThemeState
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.icons.fa.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.attributes.*

@InitRoute
fun initCoverLetterPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Cover Letter - Abhishek Verma"))
}

@Page(Routes.COVER_LETTER)
@Layout(".components.layouts.PageLayout")
@Composable
fun CoverLetter() {
    val colorMode by ThemeState.colorMode
    val isDark = colorMode == ColorMode.DARK

    // Theme-aware colors
    val cardBg = if (isDark) {
        "linear-gradient(135deg, rgba(30, 41, 59, 0.8) 0%, rgba(15, 23, 42, 0.9) 100%)"
    } else {
        "linear-gradient(135deg, rgba(255, 255, 255, 0.95) 0%, rgba(241, 245, 249, 0.98) 100%)"
    }
    val cardBorder = if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.1)"
    val cardShadow = if (isDark) "0 25px 50px rgba(0, 0, 0, 0.3)" else "0 25px 50px rgba(0, 0, 0, 0.08)"
    val greetingColor = if (isDark) "#a78bfa" else "#7c3aed"
    val textColor = if (isDark) "#cbd5e1" else "#475569"
    val highlightBg = if (isDark) {
        "linear-gradient(135deg, rgba(99, 102, 241, 0.15) 0%, rgba(139, 92, 246, 0.1) 100%)"
    } else {
        "linear-gradient(135deg, rgba(99, 102, 241, 0.1) 0%, rgba(139, 92, 246, 0.08) 100%)"
    }
    val closingBg = if (isDark) {
        "linear-gradient(135deg, rgba(244, 114, 182, 0.1) 0%, rgba(167, 139, 250, 0.1) 100%)"
    } else {
        "linear-gradient(135deg, rgba(244, 114, 182, 0.08) 0%, rgba(167, 139, 250, 0.08) 100%)"
    }
    val closingTextColor = if (isDark) "#e2e8f0" else "#1e293b"
    val sigLabelColor = if (isDark) "#94a3b8" else "#64748b"
    val sigBorderColor = if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.1)"

    // Main container
    Div(attrs = {
        style {
            property("width", "100%")
            property("max-width", "900px")
            property("margin", "0 auto")
            property("padding", "40px 24px")
        }
    }) {
        // Header Section
        CoverLetterHeader(isDark)

        // Main Letter Card
        Div(attrs = {
            style {
                property("margin-top", "40px")
                property("background", cardBg)
                property("backdrop-filter", "blur(20px)")
                property("-webkit-backdrop-filter", "blur(20px)")
                property("border", "1px solid $cardBorder")
                property("border-radius", "24px")
                property("padding", "48px")
                property("box-shadow", cardShadow)
                property("animation", "fadeInUp 0.8s ease-out")
            }
        }) {
            // Greeting
            H3(attrs = {
                style {
                    property("color", greetingColor)
                    property("font-size", "24px")
                    property("font-weight", "600")
                    property("margin", "0 0 24px")
                }
            }) {
                Text("Dear Hiring Manager,")
            }

            // Letter paragraphs
            LetterParagraph(
                prefix = "I'm ",
                highlightedText = "Abhishek Verma",
                remainingText = ", a motivated and quick-learning developer passionate about the intersection of AI and software development. I'm continuously learning Kotlin, GoLang, and machine learning to create smart and impactful solutions. Currently pursuing Bachelor of Engineering in Information Science at AMC Engineering College, Bangalore.",
                isDark = isDark
            )

            LetterParagraph(
                prefix = "I specialize in ",
                highlightedText = "Kotlin for frontend development",
                remainingText = " and ",
                highlightedText2 = "Go for backend Microservices",
                remainingText2 = " — building end-to-end applications that blend creativity, functionality, and real-time interactivity.",
                isDark = isDark
            )

            // Highlight box for projects
            Div(attrs = {
                style {
                    property("margin", "24px 0")
                    property("padding", "20px 24px")
                    property("background", highlightBg)
                    property("border-left", "4px solid #8b5cf6")
                    property("border-radius", "0 12px 12px 0")
                }
            }) {
                P(attrs = {
                    style {
                        property("color", textColor)
                        property("font-size", "16px")
                        property("line-height", "1.8")
                        property("margin", "0")
                    }
                }) {
                    Text("My learning journey has been shaped by hands-on project work — from developing ")
                    Span(attrs = { style { property("color", if (isDark) "#f472b6" else "#db2777"); property("font-weight", "600") } }) {
                        Text("DevConnect")
                    }
                    Text(", a real-time developer–client collaboration platform with chat, video calls, and crypto payments, to building ")
                    Span(attrs = { style { property("color", if (isDark) "#60a5fa" else "#3b82f6"); property("font-weight", "600") } }) {
                        Text("Kobweb Blog")
                    }
                    Text(", a full-stack blogging Web Application with modern UI and backend integration.")
                }
            }

            LetterParagraphSimple("I enjoy turning ideas into functional, elegant products — whether that's through clean API design, intuitive interfaces, or AI-powered automation workflows. My projects reflect my focus on modular architecture, efficient data handling, and seamless user experiences.", isDark)

            LetterParagraphSimple("Beyond code, I value continuous learning, adaptability, and collaboration. I'm always eager to explore new technologies, contribute to innovative teams, and work on solutions that create meaningful impact.", isDark)

            LetterParagraphSimple("I look forward to opportunities where I can contribute my skills in Go, Kotlin, AI-driven systems, and full-stack development to build smarter, user-centric software solutions.", isDark)

            // Highlighted closing message
            Div(attrs = {
                style {
                    property("margin", "32px 0 24px")
                    property("padding", "20px")
                    property("background", closingBg)
                    property("border-radius", "12px")
                    property("text-align", "center")
                }
            }) {
                P(attrs = {
                    style {
                        property("color", closingTextColor)
                        property("font-size", "18px")
                        property("font-weight", "500")
                        property("margin", "0")
                        property("line-height", "1.6")
                    }
                }) {
                    Text("Thank you for visiting my portfolio. ")
                    Br()
                    Span(attrs = {
                        style {
                            property("background", "linear-gradient(90deg, #f472b6, #a78bfa)")
                            property("-webkit-background-clip", "text")
                            property("-webkit-text-fill-color", "transparent")
                            property("background-clip", "text")
                            property("font-weight", "700")
                        }
                    }) {
                        Text("Let's connect and build something impactful together!")
                    }
                }
            }

            // Signature
            Div(attrs = {
                style {
                    property("margin-top", "32px")
                    property("padding-top", "24px")
                    property("border-top", "1px solid $sigBorderColor")
                }
            }) {
                P(attrs = {
                    style {
                        property("color", sigLabelColor)
                        property("font-size", "16px")
                        property("margin", "0 0 8px")
                    }
                }) {
                    Text("Warm regards,")
                }
                P(attrs = {
                    style {
                        property("color", if (isDark) "#ffffff" else "#1e293b")
                        property("font-size", "24px")
                        property("font-weight", "700")
                        property("margin", "0")
                        property("background", "linear-gradient(135deg, #60a5fa 0%, #a78bfa 100%)")
                        property("-webkit-background-clip", "text")
                        property("-webkit-text-fill-color", "transparent")
                        property("background-clip", "text")
                    }
                }) {
                    Text("Abhishek Verma")
                }
            }
        }

        // Contact Cards Section
        ContactCards(isDark)
    }
}

@Composable
private fun CoverLetterHeader(isDark: Boolean) {
    val badgeBg = if (isDark) "rgba(34, 197, 94, 0.15)" else "rgba(34, 197, 94, 0.1)"
    val badgeBorder = if (isDark) "rgba(34, 197, 94, 0.3)" else "rgba(34, 197, 94, 0.25)"
    val badgeTextColor = if (isDark) "#4ade80" else "#16a34a"
    val titleGradient = if (isDark) {
        "linear-gradient(135deg, #ffffff 0%, #4ade80 50%, #22d3ee 100%)"
    } else {
        "linear-gradient(135deg, #1e293b 0%, #16a34a 50%, #0891b2 100%)"
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
            FaFileLines(size = IconSize.SM, modifier = Modifier.styleModifier { property("color", badgeTextColor) })
            SpanText(
                "About Me",
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
            Text("Cover Letter")
        }

        // Subtitle
        P(attrs = {
            style {
                property("color", subtitleColor)
                property("font-size", "18px")
                property("max-width", "500px")
                property("margin", "0")
                property("line-height", "1.6")
            }
        }) {
            Text("A personal introduction to my journey, passion, and aspirations")
        }

        // Decorative line
        Div(attrs = {
            style {
                property("width", "80px")
                property("height", "4px")
                property("background", "linear-gradient(90deg, #4ade80, #22d3ee)")
                property("border-radius", "2px")
                property("margin-top", "24px")
            }
        })
    }
}

@Composable
private fun LetterParagraph(
    prefix: String,
    highlightedText: String,
    remainingText: String,
    highlightedText2: String? = null,
    remainingText2: String? = null,
    isDark: Boolean = true
) {
    val textColor = if (isDark) "#cbd5e1" else "#475569"
    val highlight1Color = if (isDark) "#a78bfa" else "#7c3aed"
    val highlight2Color = if (isDark) "#60a5fa" else "#3b82f6"

    P(attrs = {
        style {
            property("color", textColor)
            property("font-size", "16px")
            property("line-height", "1.8")
            property("margin", "0 0 20px")
        }
    }) {
        Text(prefix)
        Span(attrs = {
            style {
                property("color", highlight1Color)
                property("font-weight", "600")
            }
        }) {
            Text(highlightedText)
        }
        Text(remainingText)
        highlightedText2?.let { ht2 ->
            Span(attrs = {
                style {
                    property("color", highlight2Color)
                    property("font-weight", "600")
                }
            }) {
                Text(ht2)
            }
        }
        remainingText2?.let { Text(it) }
    }
}

@Composable
private fun LetterParagraphSimple(text: String, isDark: Boolean = true) {
    val textColor = if (isDark) "#cbd5e1" else "#475569"

    P(attrs = {
        style {
            property("color", textColor)
            property("font-size", "16px")
            property("line-height", "1.8")
            property("margin", "0 0 20px")
        }
    }) {
        Text(text)
    }
}

@Composable
private fun ContactCards(isDark: Boolean) {
    Div(attrs = {
        style {
            property("display", "flex")
            property("flex-wrap", "wrap")
            property("gap", "16px")
            property("margin-top", "40px")
            property("justify-content", "center")
        }
    }) {
        ContactCard(
            icon = { FaEnvelope(size = IconSize.LG) },
            label = "Email",
            value = "v.abhishek0203@gmail.com",
            href = SocialLinks.EMAIL,
            gradientFrom = "#6366f1",
            gradientTo = "#8b5cf6",
            isDark = isDark
        )

        ContactCard(
            icon = { FaLinkedin(size = IconSize.LG) },
            label = "LinkedIn",
            value = "Connect with me",
            href = SocialLinks.LINKEDIN,
            gradientFrom = "#0077b5",
            gradientTo = "#00a0dc",
            isDark = isDark
        )

        ContactCard(
            icon = { FaGithub(size = IconSize.LG) },
            label = "GitHub",
            value = "View my code",
            href = SocialLinks.GITHUB,
            gradientFrom = if (isDark) "#333333" else "#24292e",
            gradientTo = "#6e5494",
            isDark = isDark
        )
    }
}

@Composable
private fun ContactCard(
    icon: @Composable () -> Unit,
    label: String,
    value: String,
    href: String,
    gradientFrom: String,
    gradientTo: String,
    isDark: Boolean
) {
    val cardBg = if (isDark) {
        "linear-gradient(135deg, rgba(30, 41, 59, 0.7) 0%, rgba(15, 23, 42, 0.8) 100%)"
    } else {
        "linear-gradient(135deg, rgba(255, 255, 255, 0.9) 0%, rgba(241, 245, 249, 0.95) 100%)"
    }
    val cardBorder = if (isDark) "rgba(255, 255, 255, 0.08)" else "rgba(0, 0, 0, 0.08)"
    val hoverBorder = if (isDark) "rgba(139, 92, 246, 0.4)" else "rgba(99, 102, 241, 0.4)"
    val labelColor = if (isDark) "#64748b" else "#64748b"
    val valueColor = if (isDark) "#e2e8f0" else "#1e293b"
    val hoverShadow = if (isDark) "0 10px 30px rgba(0, 0, 0, 0.3)" else "0 10px 30px rgba(0, 0, 0, 0.1)"

    A(href = href, attrs = {
        if (!href.startsWith("mailto:")) {
            target(ATarget.Blank)
            attr("rel", "noopener noreferrer")
        }
        style {
            property("display", "flex")
            property("align-items", "center")
            property("gap", "14px")
            property("padding", "16px 20px")
            property("background", cardBg)
            property("border", "1px solid $cardBorder")
            property("border-radius", "14px")
            property("text-decoration", "none")
            property("transition", "all 0.3s ease")
            property("min-width", "220px")
            if (!isDark) {
                property("box-shadow", "0 4px 15px rgba(0, 0, 0, 0.05)")
            }
        }
        attr("onmouseenter", "this.style.transform='translateY(-4px)';this.style.borderColor='$hoverBorder';this.style.boxShadow='$hoverShadow'")
        attr("onmouseleave", "this.style.transform='translateY(0)';this.style.borderColor='$cardBorder';this.style.boxShadow='${if (!isDark) "0 4px 15px rgba(0, 0, 0, 0.05)" else "none"}'")
    }) {
        // Icon container
        Div(attrs = {
            style {
                property("width", "44px")
                property("height", "44px")
                property("background", "linear-gradient(135deg, $gradientFrom 0%, $gradientTo 100%)")
                property("border-radius", "12px")
                property("display", "flex")
                property("align-items", "center")
                property("justify-content", "center")
                property("color", "#ffffff")
                property("flex-shrink", "0")
            }
        }) {
            icon()
        }
        // Text content
        Div {
            Div(attrs = {
                style {
                    property("color", labelColor)
                    property("font-size", "12px")
                    property("font-weight", "500")
                    property("text-transform", "uppercase")
                    property("letter-spacing", "0.5px")
                }
            }) {
                Text(label)
            }
            Div(attrs = {
                style {
                    property("color", valueColor)
                    property("font-size", "14px")
                    property("font-weight", "500")
                    property("margin-top", "2px")
                }
            }) {
                Text(value)
            }
        }
    }
}
