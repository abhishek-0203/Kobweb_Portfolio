@file:Suppress("UNUSED_VARIABLE", "UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")

package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mano.ashwa.data.SocialLinks
import com.mano.ashwa.navigation.Routes
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.icons.fa.*
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import kotlinx.browser.window

external fun encodeURIComponent(str: String): String

@Composable
fun Footer(colorMode: ColorMode = ColorMode.DARK) {
    val isDark = colorMode == ColorMode.DARK

    // Theme-aware colors
    val bgGradient = if (isDark) {
        "linear-gradient(180deg, #0f172a 0%, #1e1b4b 100%)"
    } else {
        "linear-gradient(180deg, #e2e8f0 0%, #c7d2fe 100%)"
    }

    // Main footer container with gradient background
    Div(attrs = {
        style {
            property("width", "100%")
            property("background", bgGradient)
            property("position", "relative")
            property("overflow", "hidden")
        }
    }) {
        // Background decorative elements
        ContactBackgroundEffects(isDark)

        // Content wrapper
        Div(attrs = {
            style {
                property("position", "relative")
                property("z-index", "1")
                property("padding", "60px 24px 40px")
                property("max-width", "1100px")
                property("margin", "0 auto")
            }
        }) {
            // Section Header
            ContactHeader(isDark)

            // Main content - simplified single column for better mobile support
            Div(attrs = {
                style {
                    property("display", "flex")
                    property("flex-wrap", "wrap")
                    property("gap", "40px")
                    property("margin-top", "40px")
                    property("justify-content", "center")
                }
            }) {
                // Contact Form - takes more space
                Div(attrs = {
                    style {
                        property("flex", "1 1 400px")
                        property("max-width", "550px")
                        property("min-width", "300px")
                    }
                }) {
                    ContactFormCard(isDark)
                }

                // Contact Info Cards
                Div(attrs = {
                    style {
                        property("flex", "1 1 300px")
                        property("max-width", "400px")
                        property("min-width", "280px")
                    }
                }) {
                    ContactInfoSection(isDark)
                }
            }

            // Divider
            Div(attrs = {
                style {
                    property("height", "1px")
                    property("background", "linear-gradient(90deg, transparent 0%, rgba(139, 92, 246, 0.3) 50%, transparent 100%)")
                    property("margin", "50px 0 30px")
                }
            })

            // Footer bottom section
            FooterBottom(isDark)
        }
    }
}

@Composable
private fun ContactBackgroundEffects(isDark: Boolean) {
    val orb1Color = if (isDark) "rgba(99, 102, 241, 0.12)" else "rgba(99, 102, 241, 0.08)"
    val orb2Color = if (isDark) "rgba(139, 92, 246, 0.08)" else "rgba(139, 92, 246, 0.06)"

    // Gradient orb top-left
    Div(attrs = {
        style {
            property("position", "absolute")
            property("top", "-100px")
            property("left", "-100px")
            property("width", "300px")
            property("height", "300px")
            property("background", "radial-gradient(circle, $orb1Color 0%, transparent 70%)")
            property("border-radius", "50%")
            property("filter", "blur(40px)")
            property("pointer-events", "none")
        }
    })
    // Gradient orb bottom-right
    Div(attrs = {
        style {
            property("position", "absolute")
            property("bottom", "-100px")
            property("right", "-80px")
            property("width", "350px")
            property("height", "350px")
            property("background", "radial-gradient(circle, $orb2Color 0%, transparent 70%)")
            property("border-radius", "50%")
            property("filter", "blur(40px)")
            property("pointer-events", "none")
        }
    })
}

@Composable
private fun ContactHeader(isDark: Boolean) {
    val badgeBg = if (isDark) "rgba(139, 92, 246, 0.15)" else "rgba(99, 102, 241, 0.12)"
    val badgeBorder = if (isDark) "rgba(139, 92, 246, 0.3)" else "rgba(99, 102, 241, 0.25)"
    val badgeTextColor = if (isDark) "#a78bfa" else "#6366f1"
    val titleGradient = if (isDark) {
        "linear-gradient(135deg, #ffffff 0%, #a5b4fc 100%)"
    } else {
        "linear-gradient(135deg, #1e293b 0%, #6366f1 100%)"
    }
    val subtitleColor = if (isDark) "#94a3b8" else "#64748b"

    // Center aligned header
    Div(attrs = {
        style {
            property("display", "flex")
            property("flex-direction", "column")
            property("align-items", "center")
            property("text-align", "center")
            property("width", "100%")
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
            FaEnvelope(size = IconSize.SM, modifier = Modifier.styleModifier { property("color", badgeTextColor) })
            SpanText(
                "Get In Touch",
                modifier = Modifier.styleModifier {
                    property("color", badgeTextColor)
                    property("font-size", "14px")
                    property("font-weight", "500")
                }
            )
        }

        // Main Title
        H2(attrs = {
            style {
                property("font-size", "36px")
                property("font-weight", "700")
                property("text-align", "center")
                property("margin", "0 0 12px")
                property("background", titleGradient)
                property("-webkit-background-clip", "text")
                property("-webkit-text-fill-color", "transparent")
                property("background-clip", "text")
            }
        }) {
            Text("Let's Work Together")
        }

        // Subtitle
        P(attrs = {
            style {
                property("color", subtitleColor)
                property("font-size", "16px")
                property("text-align", "center")
                property("max-width", "450px")
                property("margin", "0")
                property("line-height", "1.6")
            }
        }) {
            Text("Have a project in mind? I'd love to hear from you!")
        }
    }
}

@Composable
private fun ContactFormCard(isDark: Boolean) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf<String?>(null) }
    var statusIsError by remember { mutableStateOf(false) }
    val recipientEmail = "v.abhishek0203@gmail.com"

    // Theme-aware colors
    val cardBg = if (isDark) {
        "linear-gradient(135deg, rgba(30, 41, 59, 0.8) 0%, rgba(15, 23, 42, 0.9) 100%)"
    } else {
        "linear-gradient(135deg, rgba(255, 255, 255, 0.9) 0%, rgba(241, 245, 249, 0.95) 100%)"
    }
    val cardBorder = if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.1)"
    val cardShadow = if (isDark) "0 20px 40px rgba(0, 0, 0, 0.25)" else "0 20px 40px rgba(0, 0, 0, 0.08)"
    val titleColor = if (isDark) "#ffffff" else "#1e293b"
    val subtitleColor = if (isDark) "#64748b" else "#64748b"

    // Glassmorphism card
    Div(attrs = {
        style {
            property("background", cardBg)
            property("backdrop-filter", "blur(20px)")
            property("-webkit-backdrop-filter", "blur(20px)")
            property("border", "1px solid $cardBorder")
            property("border-radius", "20px")
            property("padding", "32px")
            property("box-shadow", cardShadow)
        }
    }) {
        // Form header
        H3(attrs = {
            style {
                property("color", titleColor)
                property("font-size", "22px")
                property("font-weight", "600")
                property("margin", "0 0 6px")
            }
        }) {
            Text("Send a Message")
        }
        P(attrs = {
            style {
                property("color", subtitleColor)
                property("font-size", "14px")
                property("margin", "0 0 24px")
            }
        }) {
            Text("Fill out the form and I'll get back to you soon.")
        }

        // Name fields row
        Div(attrs = {
            style {
                property("display", "grid")
                property("grid-template-columns", "1fr 1fr")
                property("gap", "12px")
                property("margin-bottom", "16px")
            }
        }) {
            FormInput(
                value = firstName,
                label = "First Name",
                placeholder = "John",
                icon = { FaUser(size = IconSize.SM) },
                onValueChange = { firstName = it },
                isDark = isDark
            )
            FormInput(
                value = lastName,
                label = "Last Name",
                placeholder = "Doe",
                icon = { FaUser(size = IconSize.SM) },
                onValueChange = { lastName = it },
                isDark = isDark
            )
        }

        // Subject field
        Div(attrs = {
            style { property("margin-bottom", "16px") }
        }) {
            FormInput(
                value = subject,
                label = "Subject",
                placeholder = "Project Inquiry",
                icon = { FaTag(size = IconSize.SM) },
                onValueChange = { subject = it },
                isDark = isDark
            )
        }

        // Message field
        val labelColor = if (isDark) "#94a3b8" else "#64748b"
        val inputBg = if (isDark) "rgba(15, 23, 42, 0.6)" else "rgba(255, 255, 255, 0.8)"
        val inputBorder = if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.15)"
        val inputTextColor = if (isDark) "#ffffff" else "#1e293b"

        Div(attrs = {
            style { property("margin-bottom", "20px") }
        }) {
            Label(attrs = {
                style {
                    property("display", "block")
                    property("color", labelColor)
                    property("font-size", "14px")
                    property("font-weight", "500")
                    property("margin-bottom", "8px")
                }
            }) {
                Text("Message")
            }
            TextArea(value = message, attrs = {
                attr("placeholder", "Tell me about your project...")
                onInput { message = it.value }
                style {
                    property("width", "100%")
                    property("min-height", "140px")
                    property("padding", "14px 16px")
                    property("background", inputBg)
                    property("border", "1px solid $inputBorder")
                    property("border-radius", "12px")
                    property("color", inputTextColor)
                    property("font-size", "15px")
                    property("font-family", "inherit")
                    property("resize", "vertical")
                    property("transition", "all 0.3s ease")
                    property("outline", "none")
                }
                attr("onfocus", "this.style.borderColor='rgba(139, 92, 246, 0.5)';this.style.boxShadow='0 0 0 3px rgba(139, 92, 246, 0.1)'")
                attr("onblur", "this.style.borderColor='$inputBorder';this.style.boxShadow='none'")
            })
        }

        // Submit button
        Button(attrs = {
            style {
                property("width", "100%")
                property("padding", "16px 32px")
                property("background", "linear-gradient(135deg, #6366f1 0%, #8b5cf6 50%, #a855f7 100%)")
                property("color", "#ffffff")
                property("font-size", "16px")
                property("font-weight", "600")
                property("border", "none")
                property("border-radius", "12px")
                property("cursor", "pointer")
                property("display", "flex")
                property("align-items", "center")
                property("justify-content", "center")
                property("gap", "10px")
                property("transition", "all 0.3s cubic-bezier(0.4, 0, 0.2, 1)")
                property("box-shadow", "0 4px 20px rgba(99, 102, 241, 0.4)")
            }
            attr("onmouseenter", "this.style.transform='translateY(-2px)';this.style.boxShadow='0 8px 30px rgba(99, 102, 241, 0.5)'")
            attr("onmouseleave", "this.style.transform='translateY(0)';this.style.boxShadow='0 4px 20px rgba(99, 102, 241, 0.4)'")
            onClick {
                if (firstName.isBlank() || lastName.isBlank() || subject.isBlank() || message.isBlank()) {
                    statusIsError = true
                    statusMessage = "Please fill in all fields before sending."
                    return@onClick
                }
                val fullName = "$firstName $lastName"
                val body = "From: $fullName\n\n$message"
                val mailto = "mailto:$recipientEmail?subject=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}"
                window.open(mailto, "_self")
                statusIsError = false
                statusMessage = "Opening your email client..."
                firstName = ""; lastName = ""; subject = ""; message = ""
            }
        }) {
            FaPaperPlane(size = IconSize.SM)
            Text("Send Message")
        }

        // Status message
        statusMessage?.let { msg ->
            Div(attrs = {
                style {
                    property("margin-top", "16px")
                    property("padding", "12px 16px")
                    property("border-radius", "8px")
                    property("background", if (statusIsError) "rgba(239, 68, 68, 0.15)" else "rgba(34, 197, 94, 0.15)")
                    property("border", "1px solid ${if (statusIsError) "rgba(239, 68, 68, 0.3)" else "rgba(34, 197, 94, 0.3)"}")
                }
            }) {
                SpanText(
                    msg,
                    modifier = Modifier.styleModifier {
                        property("color", if (statusIsError) "#f87171" else "#4ade80")
                        property("font-size", "14px")
                        property("font-weight", "500")
                    }
                )
            }
        }
    }
}

@Composable
private fun FormInput(
    value: String,
    label: String,
    placeholder: String,
    icon: @Composable () -> Unit,
    onValueChange: (String) -> Unit,
    isDark: Boolean
) {
    val labelColor = if (isDark) "#94a3b8" else "#64748b"
    val iconColor = if (isDark) "#64748b" else "#94a3b8"
    val inputBg = if (isDark) "rgba(15, 23, 42, 0.6)" else "rgba(255, 255, 255, 0.8)"
    val inputBorder = if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.15)"
    val inputTextColor = if (isDark) "#ffffff" else "#1e293b"

    Div {
        Label(attrs = {
            style {
                property("display", "block")
                property("color", labelColor)
                property("font-size", "14px")
                property("font-weight", "500")
                property("margin-bottom", "8px")
            }
        }) {
            Text(label)
        }
        Div(attrs = {
            style {
                property("position", "relative")
            }
        }) {
            Div(attrs = {
                style {
                    property("position", "absolute")
                    property("left", "14px")
                    property("top", "50%")
                    property("transform", "translateY(-50%)")
                    property("color", iconColor)
                }
            }) {
                icon()
            }
            Input(InputType.Text, attrs = {
                value(value)
                attr("placeholder", placeholder)
                onInput { onValueChange(it.value) }
                style {
                    property("width", "100%")
                    property("padding", "14px 16px 14px 42px")
                    property("background", inputBg)
                    property("border", "1px solid $inputBorder")
                    property("border-radius", "12px")
                    property("color", inputTextColor)
                    property("font-size", "15px")
                    property("transition", "all 0.3s ease")
                    property("outline", "none")
                }
                attr("onfocus", "this.style.borderColor='rgba(139, 92, 246, 0.5)';this.style.boxShadow='0 0 0 3px rgba(139, 92, 246, 0.1)'")
                attr("onblur", "this.style.borderColor='$inputBorder';this.style.boxShadow='none'")
            })
        }
    }
}

@Composable
private fun ContactInfoSection(isDark: Boolean) {
    // Stack of contact info cards
    Div(attrs = {
        style {
            property("display", "flex")
            property("flex-direction", "column")
            property("gap", "16px")
        }
    }) {
        // Contact info cards
        ContactInfoCard(
            icon = { FaEnvelope(size = IconSize.LG) },
            title = "Email",
            value = "v.abhishek0203@gmail.com",
            href = SocialLinks.EMAIL,
            gradientFrom = "#6366f1",
            gradientTo = "#8b5cf6",
            isDark = isDark
        )

        ContactInfoCard(
            icon = { FaLinkedin(size = IconSize.LG) },
            title = "LinkedIn",
            value = "Connect with me",
            href = SocialLinks.LINKEDIN,
            gradientFrom = "#0077b5",
            gradientTo = "#00a0dc",
            isDark = isDark
        )

        ContactInfoCard(
            icon = { FaGithub(size = IconSize.LG) },
            title = "GitHub",
            value = "View my repositories",
            href = SocialLinks.GITHUB,
            gradientFrom = if (isDark) "#333333" else "#24292e",
            gradientTo = "#6e5494",
            isDark = isDark
        )

        ContactInfoCard(
            icon = { FaLocationDot(size = IconSize.LG) },
            title = "Location",
            value = "Bangalore, India",
            href = null,
            gradientFrom = "#22c55e",
            gradientTo = "#16a34a",
            isDark = isDark
        )
    }
}

@Composable
private fun ContactInfoCard(
    icon: @Composable () -> Unit,
    title: String,
    value: String,
    href: String?,
    gradientFrom: String,
    gradientTo: String,
    isDark: Boolean
) {
    // Theme-aware colors
    val cardBg = if (isDark) {
        "linear-gradient(135deg, rgba(30, 41, 59, 0.6) 0%, rgba(15, 23, 42, 0.8) 100%)"
    } else {
        "linear-gradient(135deg, rgba(255, 255, 255, 0.9) 0%, rgba(241, 245, 249, 0.95) 100%)"
    }
    val cardBorder = if (isDark) "rgba(255, 255, 255, 0.08)" else "rgba(0, 0, 0, 0.08)"
    val hoverBorder = if (isDark) "rgba(139, 92, 246, 0.3)" else "rgba(99, 102, 241, 0.4)"
    val titleColor = if (isDark) "#64748b" else "#64748b"
    val valueColor = if (isDark) "#ffffff" else "#1e293b"
    val arrowColor = if (isDark) "#64748b" else "#94a3b8"

    val cardContent: @Composable () -> Unit = {
        Div(attrs = {
            style {
                property("display", "flex")
                property("align-items", "center")
                property("gap", "16px")
                property("padding", "20px")
                property("background", cardBg)
                property("backdrop-filter", "blur(10px)")
                property("border", "1px solid $cardBorder")
                property("border-radius", "14px")
                property("transition", "all 0.3s ease")
                if (!isDark) {
                    property("box-shadow", "0 4px 15px rgba(0, 0, 0, 0.05)")
                }
            }
            if (href != null) {
                attr("onmouseenter", "this.style.transform='translateX(5px)';this.style.borderColor='$hoverBorder'")
                attr("onmouseleave", "this.style.transform='translateX(0)';this.style.borderColor='$cardBorder'")
            }
        }) {
            // Icon container
            Div(attrs = {
                style {
                    property("width", "48px")
                    property("height", "48px")
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
                        property("color", titleColor)
                        property("font-size", "12px")
                        property("font-weight", "500")
                        property("text-transform", "uppercase")
                        property("letter-spacing", "0.5px")
                        property("margin-bottom", "2px")
                    }
                }) {
                    Text(title)
                }
                Div(attrs = {
                    style {
                        property("color", valueColor)
                        property("font-size", "14px")
                        property("font-weight", "500")
                    }
                }) {
                    Text(value)
                }
            }
            // Arrow for clickable cards
            if (href != null) {
                Div(attrs = {
                    style {
                        property("margin-left", "auto")
                        property("color", arrowColor)
                    }
                }) {
                    FaArrowRight(size = IconSize.SM)
                }
            }
        }
    }

    if (href != null) {
        A(href = href, attrs = {
            if (!href.startsWith("mailto:")) {
                target(ATarget.Blank)
                attr("rel", "noopener noreferrer")
            }
            style { property("text-decoration", "none") }
        }) {
            cardContent()
        }
    } else {
        cardContent()
    }
}

@Composable
private fun FooterBottom(isDark: Boolean) {
    val copyrightColor = if (isDark) "#64748b" else "#64748b"

    Div(attrs = {
        style {
            property("display", "flex")
            property("flex-wrap", "wrap")
            property("justify-content", "center")
            property("align-items", "center")
            property("gap", "20px")
            property("text-align", "center")
        }
    }) {
        // Copyright
        Div(attrs = {
            style {
                property("color", copyrightColor)
                property("font-size", "14px")
                property("width", "100%")
                property("text-align", "center")
            }
        }) {
            Text("© 2025 Abhishek Verma. All rights reserved.")
        }

        // Quick Links
        Div(attrs = {
            style {
                property("display", "flex")
                property("flex-wrap", "wrap")
                property("gap", "20px")
                property("justify-content", "center")
            }
        }) {
            FooterLink("Home", Routes.HOME, isDark)
            FooterLink("Skills", Routes.SKILL, isDark)
            FooterLink("Projects", Routes.PROJECT, isDark)
            FooterLink("Experience", Routes.EXPERIENCE, isDark)
        }

        // Social Icons
        Div(attrs = {
            style {
                property("display", "flex")
                property("gap", "12px")
                property("justify-content", "center")
            }
        }) {
            SocialIconButton(SocialLinks.GITHUB, isDark) { FaGithub(size = IconSize.SM) }
            SocialIconButton(SocialLinks.LINKEDIN, isDark) { FaLinkedin(size = IconSize.SM) }
            SocialIconButton(SocialLinks.EMAIL, isDark) { FaEnvelope(size = IconSize.SM) }
        }
    }
}

@Composable
private fun FooterLink(text: String, href: String, isDark: Boolean) {
    val linkColor = if (isDark) "#94a3b8" else "#64748b"
    val hoverColor = if (isDark) "#a78bfa" else "#6366f1"

    A(href = href, attrs = {
        style {
            property("color", linkColor)
            property("text-decoration", "none")
            property("font-size", "14px")
            property("transition", "color 0.2s ease")
        }
        attr("onmouseenter", "this.style.color='$hoverColor'")
        attr("onmouseleave", "this.style.color='$linkColor'")
    }) {
        Text(text)
    }
}

@Composable
private fun SocialIconButton(href: String, isDark: Boolean, icon: @Composable () -> Unit) {
    val bgColor = if (isDark) "rgba(255, 255, 255, 0.05)" else "rgba(0, 0, 0, 0.05)"
    val borderColor = if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.1)"
    val iconColor = if (isDark) "#94a3b8" else "#64748b"
    val hoverBg = if (isDark) "rgba(139, 92, 246, 0.2)" else "rgba(99, 102, 241, 0.15)"
    val hoverBorder = if (isDark) "rgba(139, 92, 246, 0.5)" else "rgba(99, 102, 241, 0.5)"
    val hoverIconColor = if (isDark) "#a78bfa" else "#6366f1"

    A(href = href, attrs = {
        if (!href.startsWith("mailto:")) {
            target(ATarget.Blank)
            attr("rel", "noopener noreferrer")
        }
        style {
            property("display", "flex")
            property("align-items", "center")
            property("justify-content", "center")
            property("width", "40px")
            property("height", "40px")
            property("background", bgColor)
            property("border", "1px solid $borderColor")
            property("border-radius", "10px")
            property("color", iconColor)
            property("text-decoration", "none")
            property("transition", "all 0.3s ease")
        }
        attr("onmouseenter", "this.style.background='$hoverBg';this.style.borderColor='$hoverBorder';this.style.color='$hoverIconColor'")
        attr("onmouseleave", "this.style.background='$bgColor';this.style.borderColor='$borderColor';this.style.color='$iconColor'")
    }) {
        icon()
    }
}

