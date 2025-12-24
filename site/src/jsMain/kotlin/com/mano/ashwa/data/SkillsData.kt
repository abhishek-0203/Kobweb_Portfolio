package com.mano.ashwa.data

import com.mano.ashwa.model.SkillData
import com.varabyte.kobweb.compose.ui.graphics.Colors

/**
 * Centralized skill data for the Skills page.
 * Separating data from UI for better maintainability.
 */
object SkillsData {

    val allSkills: List<SkillData> = listOf(
        SkillData(
            title = "OOPs",
            skills = listOf(
                "Familiar with object-oriented programming (OOP) principles and design patterns",
                "Experienced in building structured, object-oriented software using class-based architecture",
                "Skilled in applying OOP methodologies for code organization and maintainability"
            ),
            icon = "🌐",
            color = Colors.LightSalmon
        ),
        SkillData(
            title = "Kotlin",
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
            title = "Full Stack Web Developer",
            skills = listOf(
                "Familiar with responsive web apps entirely in Kotlin using Kobweb",
                "Familiar with Compose HTML, Silk styling, and route-based navigation",
                "Creating interactive UI layouts with reusable composables & custom themes",
                "Integrating APIs, and REST endpoints into Kobweb sites",
                "Deploying Kobweb projects via Render & GitHub Pages"
            ),
            icon = "🌐",
            color = Colors.LightSkyBlue
        ),
        SkillData(
            title = "Backend & Microservices",
            skills = listOf(
                "Familiar with Go for RESTful API development",
                "Basic Knowledge of Gin for backend web services and API creation",
                "Familiar with API testing tools like Postman and n8n for workflow automation",
                "Version control and collaboration using Git & GitHub"
            ),
            icon = "☁️",
            color = Colors.Lavender
        ),
        SkillData(
            title = "Soft Skills & Collaboration",
            skills = listOf(
                "Quick learner with strong problem-solving and analytical abilities",
                "Good communication and teamwork skills for effective collaboration",
                "Curious and self-motivated with a passion for continuous learning"
            ),
            icon = "🔍",
            color = Colors.MistyRose
        ),
        SkillData(
            title = "Git",
            skills = listOf(
                "Efficient in creating feature branches, merging changes, and maintaining clean workflows",
                "Strong control over commit history, including meaningful commits and reverting",
                "Skilled in collaborating through pull requests and code reviews",
                "Ability to identify and resolve merge conflicts smoothly",
                "Proficient in syncing with GitHub/GitLab and handling SSH authentication"
            ),
            icon = "💾",
            color = Colors.PaleTurquoise
        ),
        SkillData(
            title = "Security & Optimization",
            skills = listOf(
                "Basic API security and safe handling of sensitive data",
                "Understanding of authentication concepts like JWT and tokens",
                "Knowledge of basic time and space complexity (Big-O)",
                "Optimized network usage with reduced unnecessary API calls"
            ),
            icon = "🔐",
            color = Colors.PaleGoldenRod
        ),
        SkillData(
            title = "Tools & Technologies",
            skills = listOf(
                "IntelliJ IDEA, VS Code, Cursor IDE, and Git for development",
                "Familiar with project management tools like Jira and Asana",
                "Knowledge of API testing with Postman and workflow automation with n8n"
            ),
            icon = "🌐",
            color = Colors.Aqua
        ),
        SkillData(
            title = "AI & Machine Learning",
            skills = listOf(
                "Strong theoretical understanding of Machine Learning and Deep Learning",
                "Knowledge of Neural Networks and CNNs",
                "Hands-on familiarity with Scikit-learn, Pandas, NumPy, and OpenCV",
                "Introduction to TensorFlow and PyTorch frameworks"
            ),
            icon = "⚙️",
            color = Colors.Pink
        ),
        SkillData(
            title = "Generative AI & Prompt Engineering",
            skills = listOf(
                "Skilled in Prompt Engineering for AI-driven content creation",
                "Knowledge of LangChain, OpenAI API, and Supabase AI integrations",
                "Understanding of Agentic AI systems and tool integration",
                "Experience with ChatGPT, Gemini, and DALL·E"
            ),
            icon = "🤖",
            color = Colors.LightSalmon
        ),
        SkillData(
            title = "UI/UX & Design",
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
            title = "Database",
            skills = listOf(
                "Basic SQL knowledge and relational database concepts",
                "Basic NoSQL knowledge and non-relational database concepts",
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
                "Presentation & stakeholder communication"
            ),
            icon = "📝",
            color = Colors.Linen
        ),
        SkillData(
            title = "Debugging & Performance",
            skills = listOf(
                "Reading and understanding error messages/stack traces",
                "Using logs to track values and flow",
                "Using breakpoints and stepping through code in the debugger",
                "Reproducing bugs consistently before fixing",
                "Checking recent code changes (Git diff) to find what broke"
            ),
            icon = "🐛",
            color = Colors.Linen
        )
    )
}

