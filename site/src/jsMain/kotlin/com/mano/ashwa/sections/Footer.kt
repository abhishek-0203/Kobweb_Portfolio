@file:Suppress("UNUSED_VARIABLE", "UNUSED_VALUE", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")

package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.stevdza.san.kotlinbs.components.BSButton
import com.stevdza.san.kotlinbs.forms.BSInput
import com.stevdza.san.kotlinbs.forms.BSTextArea
import com.stevdza.san.kotlinbs.models.BSBorderRadius
import com.stevdza.san.kotlinbs.models.button.ButtonCustomization
import com.varabyte.kobweb.compose.css.FontStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import com.mano.ashwa.AppStyle
import kotlinx.browser.window

// Make the JS global function available to Kotlin/JS so we can URL-encode strings
external fun encodeURIComponent(str: String): String

@Composable
fun Footer() {
	// Outer background for footer (keeps same color but add top padding)
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.styleModifier { property("background-color", AppStyle.CONTENT_BACKGROUND_COLOR); property("color", "#ffffff") }
			.padding(40.px)
	) {
		// Title
		SpanText(
			"Contact Me",
			modifier = Modifier
				.fontStyle(FontStyle.Normal)
				.fontWeight(FontWeight.Bold)
				.align(alignment = Alignment.CenterHorizontally)
				.padding(top = 16.px) // reduced from 80.px so title sits just above the card
				.styleModifier { property("font-size", "20px"); property("letter-spacing", "0.4px") }
		)

		// Card container for contact form to make it look elevated and centered
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.styleModifier {
					property("display", "flex")
					property("justify-content", "center")
					property("align-items", "center")
					// removed min-height: 100vh so the card sits directly below the title instead of being pushed down
					property("padding", "40px 0")
				}
		) {
			Box(
				modifier = Modifier
					.styleModifier {
						property("width", "100%")
						property("max-width", "980px")
						property("background-color", "#071126")
						property("border-radius", "14px")
						property("box-shadow", "0 12px 40px rgba(2,6,23,0.6)")
						property("padding", "28px")
					}
			)
			{
				// Centered contact inputs inside the card
				Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
					ContactUsInput()
				}
			}
		}

		// Divider
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.height(1.px)
				.styleModifier { property("background-color", "#1f2937"); property("margin-top", "28px"); property("margin-bottom", "28px") }
		)

		// Quick Infos and Socials
		QuickInfos()

	}
}


@Composable
fun ContactUsInput() {

    val fullWidth = 520.px
    val gap = 10.px
    val halfWidth = 250.px


    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf<String?>(null) }
    var statusIsError by remember { mutableStateOf(false) }

    // Mailto-only behavior: open the visitor's email client pre-filled and addressed to you.
    val recipientEmail = "v.abhishek0203@gmail.com"

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First Name and Last Name side by side
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BSInput(
                modifier = Modifier.width(halfWidth).styleModifier {
                    property("background-color", "#0b1220"); property(
                    "color",
                    "#ffffff"
                ); property("border-color", "#263244"); property(
                    "padding",
                    "10px"
                ); property("border-radius", "8px")
                },
                value = firstName,
                label = "First Name",
                placeholder = "First Name",
                onValueChange = {
                    firstName = it
                },
            )
            Box(modifier = Modifier.padding(gap))
            BSInput(
                modifier = Modifier.width(halfWidth).styleModifier {
                    property("background-color", "#0b1220"); property(
                    "color",
                    "#ffffff"
                ); property("border-color", "#263244"); property(
                    "padding",
                    "10px"
                ); property("border-radius", "8px")
                },
                value = lastName,
                label = "Last Name",
                placeholder = "Last Name",
                onValueChange = {
                    lastName = it
                },
            )
        }

        Box(modifier = Modifier.padding(gap))
        // Subject
        BSInput(
            modifier = Modifier.width(fullWidth).styleModifier {
                property("background-color", "#0b1220"); property(
                "color",
                "#ffffff"
            ); property("border-color", "#263244"); property(
                "padding",
                "10px"
            ); property("border-radius", "8px")
            },
            value = subject,
            label = "Subject",
            placeholder = "Subject",
            onValueChange = {
                subject = it
            },
        )

        Box(modifier = Modifier.padding(gap))
        // Message
        BSTextArea(
            modifier = Modifier.width(fullWidth).styleModifier {
                property("background-color", "#0b1220"); property(
                "color",
                "#ffffff"
            ); property("border-color", "#263244"); property(
                "padding",
                "10px"
            ); property("border-radius", "8px")
            },
            value = message,
            label = "Message",
            placeholder = "Message",
            onValueChange = {
                message = it
            }
        )

        Box(modifier = Modifier.padding(gap))
        // Send Button

        BSButton(
            modifier = Modifier.width(fullWidth - 30.px)
                .justifyContent(JustifyContent.Center),
            text = "Send Message  \u2709\uFE0F",
            customization = ButtonCustomization(
                color = Colors.White,
                hoverColor = Colors.White,
                activeColor = Colors.WhiteSmoke,
                borderColor = Colors.White,
                hoverBorderColor = Colors.White,
                activeBorderColor = rgb(168, 192, 255),
                gradient = linearGradient(
                    from = rgb(168, 192, 255),
                    to = rgb(63, 43, 150),
                    dir = LinearGradient.Direction.ToTopRight
                ),
                borderRadius = BSBorderRadius(all = 50.px),
                horizontalPadding = 1.25.cssRem
            ),
            onClick = {
                // basic validation
                if (firstName.isBlank() || lastName.isBlank() || subject.isBlank() || message.isBlank()) {
                    statusIsError = true
                    statusMessage = "Please fill in all fields before sending."
                    return@BSButton
                }

                val fullName = "$firstName $lastName"
                val body = "From: $fullName\n\n$message"
                // encode subject/body to safely include in mailto
                val encodedSubject = encodeURIComponent(subject)
                val encodedBody = encodeURIComponent(body)
                val mailto = "mailto:$recipientEmail?subject=$encodedSubject&body=$encodedBody"

                // open default mail client (visitor must press send in their client)
                window.open(mailto, "_self")

                // local UI feedback and clear fields
                statusIsError = false
                statusMessage = "Your message has been sent!"
                firstName = ""
                lastName = ""
                subject = ""
                message = ""
            }
        )

        // Inline status message (success or error)
        statusMessage?.let { msg ->
            SpanText(
                msg,
                modifier = Modifier.padding(top = 10.px).styleModifier {
                    property("color", if (statusIsError) "#f97373" else "#86efac")
                    property("font-weight", "500")
                }
            )
        }
    }
}

@Composable
fun QuickInfos() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top // changed from CenterVertically to Top
        ) {
            // Start ### My Projects-------------------------------
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(20.px).styleModifier { property("min-width", "220px") }
            ) {
                H5 { Text("My Projects") }
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(10.px)
                ) {
                    A(
                        href = "https://www.keysight.com/in/en/product/NTH50047B/nemo-handy-handheld-measurement-solution.html",
                        attrs = {
                            target(ATarget.Blank)
                            attr("rel", "noopener noreferrer")
                        }
                    ) {
                        SpanText(
                            "DevMatch – Developer–Client Collaboration Platform",
                            modifier = Modifier.padding(5.px)
                                .styleModifier { property("color", "#93C5FD") })
                    }
                    A(
                        href = "https://play.google.com/store/apps/details?id=com.mobstac.thehindu&h&pli=1",
                        attrs = {
                            target(ATarget.Blank)
                            attr("rel", "noopener noreferrer")
                        }
                    ) {
                        SpanText(
                            "Kobweb-Blog  Full-Stack Technical Blogging Platform",
                            modifier = Modifier.padding(5.px)
                                .styleModifier { property("color", "#93C5FD") }
                        )
                    }
                    A(
                        href = "https://play.google.com/store/apps/details?id=com.mobstac.thehindubusinessline",
                        attrs = {
                            target(ATarget.Blank)
                            attr("rel", "noopener noreferrer")
                        }
                    ) {
                        SpanText(
                            "Portfolio Website  Personal Branding Site",
                            modifier = Modifier.padding(5.px)
                                .styleModifier { property("color", "#93C5FD") }
                        )
                    }
//
                }
            }

            // Start ### Quick Links ### --------------------------------------
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(20.px).styleModifier { property("min-width", "180px") }
            ) {
                H5 { Text("Quick Links") }
                A(href = "#home") {
                    SpanText(
                        "Home",
                        modifier = Modifier.padding(5.px).textAlign(TextAlign.Start)
                            .styleModifier {
                                property("cursor", "pointer"); property(
                                "color",
                                "#93C5FD"
                            )
                            })
                }
                A(href = "#about") {
                    SpanText(
                        "About Me",
                        modifier = Modifier.padding(5.px)
                            .styleModifier {
                                property("cursor", "pointer"); property(
                                "color",
                                "#93C5FD"
                            )
                            })
                }
                A(href = "skill") {
                    SpanText(
                        "Skills",
                        modifier = Modifier.padding(5.px)
                            .styleModifier {
                                property("cursor", "pointer"); property(
                                "color",
                                "#93C5FD"
                            )
                            })
                }
                A(href = "project") {
                    SpanText(
                        "Projects",
                        modifier = Modifier.padding(5.px)
                            .styleModifier {
                                property("cursor", "pointer"); property(
                                "color",
                                "#93C5FD"
                            )
                            })
                }
                // Download CV - adjust href to the real path if different
                A(href = "/Resume/Abhishek_Verma.pdf", attrs = {
                    attr("download", "Abhishek_Verma.pdf")
                }) {
                    SpanText(
                        "Download Resume",
                        modifier = Modifier.padding(5.px)
                            .styleModifier {
                                property("cursor", "pointer"); property(
                                "color",
                                "#93C5FD"
                            )
                            })
                }


            }

            // Start ### Follow Me-------------------------------
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(20.px).styleModifier { property("min-width", "180px") }
            ) {
                H5 { Text("Follow Me") }
                Div(
                    attrs = Modifier
                        .fillMaxWidth()
                        .height(2.px)
                        .styleModifier { property("background-color", "#3b82f6") }
                        .toAttrs()
                )

                A(
                    href = "https://github.com/abhishek-0203",
                    attrs = {
                        target(ATarget.Blank)
                        attr("rel", "noopener noreferrer")
                        attr("style", "display:inline-block;margin-right:8px;vertical-align:middle")
                    }
                ) {
                    // Use an inline SVG data URI so we control colors reliably
                    val githubSvg = """
                        <svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24'>
                          <circle cx='12' cy='12' r='12' fill='#181717'/>
                          <path d='M12 .297c-6.63 0-12 5.373-12 12 0 5.303 3.438 9.8 8.205 11.387.6.111.82-.261.82-.58 0-.287-.01-1.04-.016-2.04-3.338.724-4.042-1.61-4.042-1.61-.546-1.387-1.333-1.756-1.333-1.756-1.089-.745.083-.73.083-.73 1.205.084 1.839 1.24 1.839 1.24 1.07 1.835 2.809 1.305 3.492.998.11-.776.419-1.305.762-1.605-2.665-.3-5.466-1.332-5.466-5.93 0-1.31.468-2.381 1.236-3.221-.124-.303-.536-1.523.117-3.176 0 0 1.008-.322 3.301 1.23A11.495 11.495 0 0112 5.8c1.02.004 2.046.138 3.003.405 2.291-1.553 3.297-1.23 3.297-1.23.655 1.653.244 2.873.12 3.176.77.84 1.235 1.91 1.235 3.221 0 4.61-2.805 5.625-5.478 5.92.43.372.814 1.103.814 2.222 0 1.603-.014 2.896-.014 3.287 0 .321.216.697.824.579C20.565 21.796 24 17.298 24 12c0-6.627-5.373-11.703-12-11.703z' fill='#ffffff'/>
                        </svg>
                    """.trimIndent()

                    Img(
                        src = "data:image/svg+xml;utf8,${encodeURIComponent(githubSvg)}",
                        alt = "GitHub",
                        attrs = {
                            attr("style", "width:36px;height:36px;display:inline-block;vertical-align:middle;border-radius:8px;background-color:transparent;padding:4px")
                        }
                    )
                }
                A(
                    href = "https://www.linkedin.com/in/abhishek-verma-196789379/",
                    attrs = {
                        target(ATarget.Blank)
                        attr("rel", "noopener noreferrer")
                        attr("style", "display:inline-block;vertical-align:middle")
                    }
                ) {
                    val linkedinSvg = """
                        <svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24'>
                          <circle cx='12' cy='12' r='12' fill='#0077B5'/>
                          <path d='M20.447 20.452h-3.554v-5.569c0-1.328-.025-3.039-1.852-3.039-1.852 0-2.135 1.445-2.135 2.939v5.669H9.349V9h3.414v1.561h.049c.476-.9 1.637-1.852 3.368-1.852 3.6 0 4.266 2.37 4.266 5.455v6.288zM7.119 20.452H3.554V9h3.565v11.452zM5.337 7.433c-1.144 0-2.071-.928-2.071-2.073 0-1.145.927-2.073 2.071-2.073 1.144 0 2.071.928 2.071 2.073 0 1.145-.927 2.073-2.071 2.073z' fill='#ffffff'/>
                        </svg>
                    """.trimIndent()

                    Img(
                        src = "data:image/svg+xml;utf8,${encodeURIComponent(linkedinSvg)}",
                        alt = "LinkedIn",
                        attrs = {
                            attr("style", "width:36px;height:36px;display:inline-block;vertical-align:middle;border-radius:8px;background-color:transparent;padding:4px")
                        }
                    )
                }
        }
    }
}
