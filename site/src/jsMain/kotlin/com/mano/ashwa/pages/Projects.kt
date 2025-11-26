package com.mano.ashwa.pages

import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.navigation.Project_Route
import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.text.SpanText
import com.mano.ashwa.components.widgets.ProjectCardView
import com.mano.ashwa.model.ProjectData
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.gridTemplateColumns
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Div

@InitRoute
fun initMyProjectsPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Abhishek Projects"))
}

@Page(Project_Route)
@Layout(".components.layouts.PageLayout")
@Composable
fun Projects() {
    Box(Modifier.fillMaxWidth().backgroundColor(Colors.Transparent).padding(32.px)) {
        Column(
            Modifier.gap(32.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText("My Projects", Modifier.fontSize(32.px).fontWeight(FontWeight.Bold).color(Colors.DarkSlateBlue))

            Div(
                attrs = Modifier
                    .fillMaxWidth()
                    .display(DisplayStyle.Grid)
                    .gridTemplateColumns {
                        repeat(2) { minmax(300.px, 1.fr) }
                    }
                    .gap(32.px)
                    .toAttrs()
            ) {
                projectCards.forEach { card ->
                    ProjectCardView(card)
                }
            }
        }
    }
}

private val projectCards = listOf(
    ProjectData(
        name = "DevConnect – (Backend Microservices) Developer–Client Collaboration Platform",
        description = "A Go-based backend platform enabling real-time chat, video calls (WebRTC), and dummy " +
                "payments for seamless collaboration between developers and clients. Features include secure" +
                " authentication with role-based access, WebSocket-based chat with message persistence, JSON file-based " +
                "structured data management, and modular RESTful APIs built using the Gin framework.",
        duration = "March 2025 – October 2025.",
        role = "Backend Developer",
        technologies = listOf("Golang","Gin", "WebRTC", "WebSocket", "JSON", "Dummy Payments"),
        icon = "\uD83D\uDCBB",
        color = Colors.Lavender
    ),
    ProjectData(
        name = "Kobweb-Blog  Full-Stack Technical Blogging Platform",
        description = "Kotlin Multiplatform blogging platform using Kobweb, Compose for Web, Ktor, and MongoDB. Features category, search, auth, REST APIs, and dynamic content.",
        duration = "Present",
        role = "Full Stack Developer",
        technologies = listOf("Kotlin Multiplatform", "Kobweb", "Compose for Web", "Ktor", "MongoDB"),
        icon = "\ud83c\udf10",
        color = Colors.LightGreen
    ),
    ProjectData(
        name = "Portfolio Website  Personal Branding Site",
        description = "Responsive portfolio site with contact form, built and deployed using Kobweb and Compose for Web.",
        duration = "Present",
        role = "Web Developer",
        technologies = listOf("Kotlin Multiplatform", "Kobweb", "Compose for Web", "Ktor"),
        icon = "\ud83d\udcbc",
        color = Colors.LightBlue
    ),
    ProjectData(
        name = "User Module API",
        description = "Implemented very basic User Module API (Login, Registration, Profile).",
        duration = "2023",
        role = "Developer",
        technologies = listOf("GoLang","Gin","JSON"),
        icon = "\u2728",
        color = Colors.LightPink
    )
)
