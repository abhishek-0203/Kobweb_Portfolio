package com.mano.ashwa.data

import com.mano.ashwa.model.ProjectData
import com.varabyte.kobweb.compose.ui.graphics.Colors

/**
 * Centralized project data for the Projects page.
 * Separating data from UI for better maintainability.
 */
object ProjectsData {

    val allProjects: List<ProjectData> = listOf(
        ProjectData(
            name = "DevConnect – Developer–Client Collaboration Platform",
            description = "A Go-based backend platform enabling real-time chat, video calls (WebRTC), and dummy " +
                    "payments for seamless collaboration between developers and clients. Features include secure " +
                    "authentication with role-based access, WebSocket-based chat with message persistence, JSON " +
                    "file-based structured data management, and modular RESTful APIs built using the Gin framework.",
            duration = "March 2025 – October 2025",
            role = "Backend Developer",
            technologies = listOf("Golang", "Gin", "WebRTC", "WebSocket", "JSON", "Dummy Payments"),
            icon = "💻",
            color = Colors.Lavender
        ),
        ProjectData(
            name = "Kobweb-Blog – Full-Stack Technical Blogging Platform",
            description = "Kotlin Multiplatform blogging platform using Kobweb, Compose for Web, Ktor, and MongoDB. " +
                    "Features category, search, auth, REST APIs, and dynamic content.",
            duration = "Present",
            role = "Full Stack Developer",
            technologies = listOf("Kotlin Multiplatform", "Kobweb", "Compose for Web", "Ktor", "MongoDB"),
            icon = "🌐",
            color = Colors.LightGreen
        ),
        ProjectData(
            name = "Portfolio Website – Personal Branding Site",
            description = "Responsive portfolio site with contact form, built and deployed using Kobweb and " +
                    "Compose for Web.",
            duration = "Present",
            role = "Web Developer",
            technologies = listOf("Kotlin Multiplatform", "Kobweb", "Compose for Web", "Ktor"),
            icon = "💼",
            color = Colors.LightBlue
        ),
        ProjectData(
            name = "User Module API",
            description = "Implemented very basic User Module API (Login, Registration, Profile).",
            duration = "2023",
            role = "Developer",
            technologies = listOf("GoLang", "Gin", "JSON"),
            icon = "✨",
            color = Colors.LightPink
        )
    )
}

