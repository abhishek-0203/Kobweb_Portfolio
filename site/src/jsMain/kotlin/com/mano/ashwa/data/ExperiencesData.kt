package com.mano.ashwa.data

import com.mano.ashwa.model.ExperienceData
import com.varabyte.kobweb.compose.ui.graphics.Colors

/**
 * Centralized experience data for the Experience page.
 * Separating data from UI for better maintainability.
 */
object ExperiencesData {

    val allExperiences: List<ExperienceData> = listOf(
        ExperienceData(
            companyName = "Tech Mahindra",
            title = "Working as Tech Lead for the client Keysight",
            duration = "14-May-2024 to Present",
            role = "Tech Lead",
            skills = listOf(
                "Log back stack tracking, Debugging",
                "Android Native, JNI code implementation",
                "Performance and Memory handling",
                "NR & LTE network communications and monitoring",
                "Agile Methodologies and Team Leadership",
                "Code architecture, Mentoring and Code Reviews",
                "Task Planning and Execution and Client Communication",
                "Technologies: Kotlin, Java, C/C++, Android, JNI, Git, JIRA, Confluence",
                "Environment: Linux, Windows",
                "Domain: Telecom (5G, LTE, NR)",
                "Google Drive Upload & Avoid Duplicate Folder Creation",
                "File Handling, Multithreading, Retrofit, REST API",
                "Google Play Service NearBy Discovery connectivity",
                "Bluetooth connectivity and file transferring",
                "Requirements gathering, Flow Diagrams, Task breakup and estimation"
            ),
            icon = "📱",
            color = Colors.LightBlue
        ),
        ExperienceData(
            companyName = "NINESTARS INFORMATION TECHNOLOGIES PVT LTD",
            title = "Worked on News, E-Commerce, Media Apps with clients TheHindu, Deccan Herald, TV9",
            duration = "26-Aug-2015 to 13-May-2024",
            role = "Lead Android Developer",
            skills = listOf(
                "Responsible for code architecture and correct coding practices",
                "Feature delivery on Mobile with task scoping and breakdown",
                "Performance and Memory handling",
                "Development team leadership and collaboration",
                "Server driven template based custom UIs",
                "Code architecture, Mentoring and Code Reviews",
                "Database and Scheduled syncing",
                "GraphQL APIs implementation",
                "Team management and client interaction",
                "End-to-end product quality ownership"
            ),
            icon = "📱",
            color = Colors.LightGreen
        ),
        ExperienceData(
            companyName = "GLOBAL LOGIC INDIA LTD",
            title = "Worked on JIO core App (Reliance)",
            duration = "14-Mar-2014 to 22-Aug-2015",
            role = "Senior Software Developer",
            skills = listOf(
                "Android App Programmer",
                "Screen UI design and flow implementation",
                "Custom UIs & designs",
                "Database and APIs Integration",
                "Background Jobs, Alarm Manager, Background data syncing",
                "Server driven template based custom UIs",
                "MVM Design Pattern using RxJava"
            ),
            icon = "📱",
            color = Colors.LightYellow
        ),
        ExperienceData(
            companyName = "A1 Technologies PVT LTD",
            title = "Android Development",
            duration = "Dec-2011 to Feb-2014",
            role = "Software Engineer",
            skills = listOf(
                "Creating layouts and designs",
                "Database and API integration",
                "Multi listview with Loader data provider",
                "Content Providers implementation",
                "Services and BroadcastReceivers for background processing",
                "Social Login Implementation"
            ),
            icon = "📱",
            color = Colors.LightCyan
        ),
        ExperienceData(
            companyName = "RED ORANGE TECHNOLOGIES",
            title = "Android Development",
            duration = "May-2011 to Dec-2011",
            role = "Junior Software Engineer",
            skills = listOf(
                "Android Mobile app lifecycle understanding",
                "REST API requests for file operations",
                "Layout & UI Creation",
                "Database and API integration",
                "Third party libs integration"
            ),
            icon = "📱",
            color = Colors.LightPink
        ),
        ExperienceData(
            companyName = "LIMITEX TECHNOLOGIES",
            title = "Training and Initial Development",
            duration = "Sep-2010 to May-2011",
            role = "Trainee/Junior Software Developer",
            skills = listOf(
                "Learnt HTML, CSS, and Eclipse IDE",
                "Created basic HTML templates",
                "Trainee Android Development",
                "Learning Android layouts and designs"
            ),
            icon = "📱",
            color = Colors.WhiteSmoke
        )
    )
}

