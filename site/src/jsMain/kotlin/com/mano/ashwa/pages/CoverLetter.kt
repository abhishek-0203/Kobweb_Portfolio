package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.navigation.CoverLetter_Route
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.attributes.*

@InitRoute
fun initCoverLetterPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Cover Letter - Abhishek Verma"))
}

@Page(CoverLetter_Route)
@Layout(".components.layouts.PageLayout")
@Composable
fun CoverLetter() {
    Box(Modifier.fillMaxWidth().padding(32.px)) {
        Column(Modifier.gap(16.px), horizontalAlignment = Alignment.Start) {
            SpanText("Dear Hiring Manager,")

            SpanText("I’m Abhishek Verma, motivated and quick-learning developer passionate about the intersection of AI and software development, continuously learning Kotlin, GoLang and machine learning to create smart and impactful solutions currently pursuing Bachelor of Engineering in Information Science at AMC Engineering College, Bangalore.")

            SpanText("I specialize in Kotlin for frontend development and Go for backend Microservices — building end-to-end applications that blend creativity, functionality, and real-time interactivity.")

            SpanText("Over time, I’ve developed a deep interest in AI systems and how intelligent automation can simplify human–computer interaction. My learning journey has been shaped by hands-on project work — from developing DevConnect, a real-time developer–client collaboration platform with chat, video calls, and crypto payments, to building Kobweb Blog a full-stack blogging Web Application with modern UI and backend integration.")

            SpanText("I enjoy turning ideas into functional, elegant products — whether that’s through clean API design, intuitive interfaces, or AI-powered automation workflows. My projects reflect my focus on modular architecture, efficient data handling, and seamless user experiences.")

            SpanText("Beyond code, I value continuous learning, adaptability, and collaboration. I’m always eager to explore new technologies, contribute to innovative teams, and work on solutions that create meaningful impact.")

            SpanText("I look forward to opportunities where I can contribute my skills in Go, Kotlin, AI-driven systems, and full-stack development to build smarter, user-centric software solutions.")

            SpanText("Thank you for visiting my portfolio. Let’s connect and build something impactful together!")

            SpanText("Warm regards,\nAbhishek Verma")

            // Contact links
            A(href = "mailto:v.abhishek0203@gmail.com", attrs = {
                attr("rel", "noopener noreferrer")
            }) {
                SpanText("📧 v.abhishek0203@gmail.com", Modifier.padding(top = 8.px))
            }

            A(href = "https://www.linkedin.com/in/abhishek-verma-196789379/", attrs = {
                target(ATarget.Blank)
                attr("rel", "noopener noreferrer")
            }) {
                SpanText("🔗 LinkedIn", Modifier.padding(top = 4.px))
            }

            A(href = "https://github.com/abhishek-0203", attrs = {
                target(ATarget.Blank)
                attr("rel", "noopener noreferrer")
            }) {
                SpanText("🔗 GitHub", Modifier.padding(top = 4.px))
            }
        }
    }
}