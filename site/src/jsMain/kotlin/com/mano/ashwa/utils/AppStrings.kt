package com.mano.ashwa.utils

/**
 * Centralized string constants for the application.
 * Using const val for compile-time constants.
 */
object AppStrings {
    // Personal Info
    const val USER_NAME = "Abhishek Verma"
    const val EMAIL = "v.abhishek0203@gmail.com"
    const val GITHUB_URL = "https://github.com/abhishek-0203"
    const val LINKEDIN_URL = "https://www.linkedin.com/in/abhishek-verma-196789379/"

    // Page Titles
    const val SITE_TITLE = "Abhishek's Portfolio"
    const val RESUME_PAGE_TITLE = "Resume - Abhishek Verma"
    const val ABOUT_PAGE_TITLE = "About - Abhishek Verma"
    const val SKILLS_PAGE_TITLE = "Skills - Abhishek Verma"
    const val PROJECTS_PAGE_TITLE = "Projects - Abhishek Verma"
    const val EXPERIENCE_PAGE_TITLE = "Experience - Abhishek Verma"

    // Section Headers
    const val SKILLS_HEADER = "My Skills"
    const val PROJECTS_HEADER = "My Projects"
    const val EXPERIENCE_HEADER = "My Experiences"
    const val CONTACT_HEADER = "Contact Me"

    // Resume
    const val RESUME_FILENAME = "Abhishek_Verma.pdf"
    const val RESUME_PATH = "/Resume/Abhishek_Verma.pdf"

    // Legacy aliases for backward compatibility
    @Deprecated("Use USER_NAME instead", ReplaceWith("USER_NAME"))
    val userName = USER_NAME
    @Deprecated("Use RESUME_PAGE_TITLE instead", ReplaceWith("RESUME_PAGE_TITLE"))
    val documentPageTitle = RESUME_PAGE_TITLE
    @Deprecated("Use ABOUT_PAGE_TITLE instead", ReplaceWith("ABOUT_PAGE_TITLE"))
    val aboutPageTitle = ABOUT_PAGE_TITLE
}