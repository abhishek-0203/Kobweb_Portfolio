package com.mano.ashwa.navigation

/**
 * Route constants for navigation.
 * Using object for better organization and discoverability.
 */
object Routes {
    const val HOME = "/"
    const val SKILL = "/skill"
    const val ABOUT = "/about"
    const val PROJECT = "/project"
    const val EXPERIENCE = "/experiences"
    const val COVER_LETTER = "/cover-letter"
    const val CONTACT_ME = "/contact-me"
}

// Legacy route constants for backward compatibility
@Deprecated("Use Routes.SKILL instead", ReplaceWith("Routes.SKILL"))
const val Skill_Route = Routes.SKILL
@Deprecated("Use Routes.ABOUT instead", ReplaceWith("Routes.ABOUT"))
const val About_Route = Routes.ABOUT
@Deprecated("Use Routes.PROJECT instead", ReplaceWith("Routes.PROJECT"))
const val Project_Route = Routes.PROJECT
@Deprecated("Use Routes.EXPERIENCE instead", ReplaceWith("Routes.EXPERIENCE"))
const val Experience_Route = Routes.EXPERIENCE
@Deprecated("Use Routes.COVER_LETTER instead", ReplaceWith("Routes.COVER_LETTER"))
const val CoverLetter_Route = Routes.COVER_LETTER
@Deprecated("Use Routes.CONTACT_ME instead", ReplaceWith("Routes.CONTACT_ME"))
const val ContactMe_Route = Routes.CONTACT_ME

/**
 * Sealed class representing all navigation screens in the app.
 * Each screen has an [id] for identification, [title] for display, and [route] for navigation.
 */
sealed class Screen(
    val id: String,
    val title: String,
    val route: String
) {
    data object Home : Screen(id = "home", title = "Home", route = Routes.HOME)
    data object About : Screen(id = "about", title = "About", route = Routes.ABOUT)
    data object Skill : Screen(id = "skill", title = "Skills", route = Routes.SKILL)
    data object Experience : Screen(id = "experience", title = "Experience", route = Routes.EXPERIENCE)
    data object Project : Screen(id = "project", title = "Projects", route = Routes.PROJECT)
    data object CoverLetter : Screen(id = "coverLetter", title = "Cover Letter", route = Routes.COVER_LETTER)
    data object ContactMe : Screen(id = "contactMe", title = "Contact Me", route = Routes.CONTACT_ME)

    companion object {
        /** All available screens for iteration. */
        val all: List<Screen> = listOf(Home, About, Skill, Experience, Project, CoverLetter, ContactMe)

        /** Find a screen by its route, or null if not found. */
        fun fromRoute(route: String): Screen? = all.find { it.route == route }
    }
}