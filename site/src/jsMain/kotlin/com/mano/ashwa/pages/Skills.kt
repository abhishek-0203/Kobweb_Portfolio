package com.mano.ashwa.pages

import org.jetbrains.compose.web.css.fr
import androidx.compose.runtime.Composable
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.components.widgets.SkillCardView
import com.mano.ashwa.model.SkillData
import com.mano.ashwa.navigation.Skill_Route
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.compose.foundation.layout.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.compose.ui.modifiers.*
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.DisplayStyle
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.jetbrains.compose.web.dom.Div

@InitRoute
fun initSkillPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Abhishek Skills"))
}

@Page(Skill_Route)
@Layout(".components.layouts.PageLayout")
@Composable
fun SkillPage() {
    Box(Modifier.fillMaxWidth().backgroundColor(Colors.Transparent).padding(32.px)) {
        Column(
            Modifier.gap(32.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText("My Skills", Modifier.fontSize(32.px).fontWeight(FontWeight.Bold).color(Colors.DarkSlateBlue))

            Div(
                attrs = Modifier
                    .fillMaxWidth()
                    .display(DisplayStyle.Grid)
                    .gridTemplateColumns {
                        repeat(4) { minmax(250.px, 1.fr) }
                    }
                    .gap(32.px)
                    .toAttrs()
            ) {
                skillCards.forEach { card ->
                    SkillCardView(
                        title = card.title,
                        skills = card.skills,
                        icon = card.icon,
                        color = card.color
                    )
                }
            }
        }
    }
}


private val skillCards = listOf(
    SkillData(
        title = "Kotlin Multiplatform & Android",
        skills = listOf(
            "Familiar in Kotlin for building Android and web-based applications",
            "Familiar with Jetpack Compose and Compose Multiplatform for responsive UI design",
            "Familiar with Material Design 3, Navigation Components, and UI theming",
            "Basic understanding of Kotlin Kobweb framework for full-stack web development",
            "Knowledge of Coroutines and Flow for managing asynchronous operations",
            "Skilled in REST API integration, JSON handling, and API debugging"
        ),
        icon = "📱",
        color = Colors.LightBlue
    ),
    SkillData(
        title = "Architecture & Patterns",
        skills = listOf(
            "Clean Architecture & SOLID principles",
            "MVVM / MVI / Unidirectional data flow",
            "Coroutines, Flow, and structured concurrency",
            "Scalable multi-module architecture",
            "Design Patterns: Repository, Factory, Singleton, Observer, Builder, Strategy, Adapter, Delegate, Mediator"
        ),
        icon = "🏗️",
        color = Colors.LightGoldenRodYellow
    ),
    SkillData(
        title = "Libraries & Integrations",
        skills = listOf(
            "Dependency Injection (Hilt, Koin, Dagger)",
            "Networking with Ktor, Retrofit, GraphQL, WebSocket",
            "Firebase suite: Auth, Firestore, Messaging, Crashlytics",
            "Google Ads SDK, Analytics & Payment SDK integrations",
            "Compose UI tooling, Accompanist, and Coil/Glide"
        ),
        icon = "📦",
        color = Colors.LightGreen
    ),
    SkillData(
        title = "Kobweb Framework",
        skills = listOf(
            "Familiar with responsive web apps entirely in Kotlin using Kobweb",
            "Familiar with Compose HTML, Silk styling, and route-based navigation",
            "Creating interactive UI layouts with reusable composables & custom themes",
            "Integrating APIs, Firebase, and REST endpoints into Kobweb sites",
            "Deploying Kobweb projects via Gradle & GitHub Pages",
            "Optimizing SEO, routing, and SSR for Kotlin web apps"
        ),
        icon = "🌐",
        color = Colors.LightSkyBlue
    ),
    SkillData(
        title = "Backend & Integration",
        skills = listOf(
            "Familiar with Go (Gin Framework) for RESTful API development",
            "Understanding of JWT Authentication and secure data handling",
            "Basic Knowledge of Django for backend web services and API creation",
            "Familiar with API testing tools like Postman and n8n for workflow automation",
            "Version control and collaboration using Git & GitHub"
        ),
        icon = "☁️",
        color = Colors.Lavender
    ),
    SkillData(
        title = "Debugging & Performance",
        skills = listOf(
            "Advanced debugging & log tracing",
            "Memory, CPU & network profiling",
            "Startup & frame rendering optimization",
            "Custom ADB scripts & benchmarking tools",
            "Crash & ANR analysis with Firebase & Play Console"
        ),
        icon = "🔍",
        color = Colors.MistyRose
    ),
    SkillData(
        title = "Testing & Release",
        skills = listOf(
            "Manual & functional testing for Android modules",
            "Basic test cases (JUnit, Mockito, Espresso)",
            "Flow & LiveData reactive stream testing",
            "Automated builds using GitHub Actions or Jenkins",
            "Static analysis (Ktlint, Detekt, SonarQube)"
        ),
        icon = "✅",
        color = Colors.AliceBlue
    ),
    SkillData(
        title = "Security & Optimization",
        skills = listOf(
            "App signing, keystore & Play Integrity setup",
            "Secure API handling with encryption & tokens",
            "ProGuard/R8 optimization & code obfuscation",
            "Data privacy (GDPR) & permission audits"
        ),
        icon = "🔐",
        color = Colors.PaleGoldenRod
    ),
    SkillData(
        title = "Tools & Technologies",
        skills = listOf(
            "IntelliJ IDEA, VS Code, Cursor IDE, and Git for development and version control",
            "Experience with CI/CD pipelines using GitHub Actions",
            "Familiar with project management tools like Jira and Asana",
            "Knowledge of API testing with Postman and workflow automation with n8n"
        ),
        icon = "🌐",
        color = Colors.PaleGoldenRod
    ),
    SkillData(
        title = "Artificial Intelligence & Machine Learning",
        skills = listOf(
            "Strong theoretical understanding of Machine Learning and Deep Learning concepts",
            "Knowledge of Neural Networks, CNNs",
            "Hands-on familiarity with Scikit-learn, Pandas, NumPy, and OpenCV",
            "Introduction to TensorFlow and PyTorch frameworks for model development"
        ),
        icon = "⚙️",
        color = Colors.Wheat
    ),
    SkillData(
        title = "Generative AI & Prompt Engineering",
        skills = listOf(
            "Skilled in Prompt Engineering for AI-driven content creation and problem solving",
            "Knowledge of LangChain, OpenAI API, and Supabase AI integrations",
            "Understanding of Agentic AI systems and tool integration for workflow automation",
            "Experience with generative AI platforms such as ChatGPT, Gemini, and DALL·E"
        ),
        icon = "🤖",
        color = Colors.LightSalmon
    ),
    SkillData(
        title = "UI/UX & Design Collaboration",
        skills = listOf(
            "Composable UI design with accessibility support",
            "Basic knowledge of Figma",
            "Responsive layouts & adaptive theming",
            "Motion, animation & material transitions"
        ),
        icon = "🎨",
        color = Colors.MistyRose
    ),
    SkillData(
        title = "Database & Storage",
        skills = listOf(
                    "Basic SQL knowledge and relational database concepts",
                    "Experience with JSON-based storage and lightweight backend data structures",
                    "Understanding of Room Database and DataStore integration in Android"
        ),
        icon = "👥",
        color = Colors.HoneyDew
    ),
    SkillData(
        title = "AI, Prompting & Agents",
        skills = listOf(
            "Prompt Engineering — structured prompt design & chaining",
            "Understanding Agentic AI concepts & reasoning flow",
            "Integrating AI workflows for app intelligence & automation"
        ),
        icon = "🤖",
        color = Colors.PaleTurquoise
    ),
    SkillData(
        title = "Documentation & Communication",
        skills = listOf(
            "Technical documentation (API specs, design guides)",
            "Markdown, README & codebase documentation",
            "Presentation & stakeholder communication",
        ),
        icon = "📝",
        color = Colors.Linen
    ),

    SkillData(
        title = "Soft Skills & Collaboration",
        skills = listOf(
            "Quick learner with strong problem-solving and analytical abilities",
            "Good communication and teamwork skills for effective collaboration",
            "Curious and self-motivated with a passion for continuous learning and innovation"
        ),
        icon = "👥",
        color = Colors.Linen
    )
)
