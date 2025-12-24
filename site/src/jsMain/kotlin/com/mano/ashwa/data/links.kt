package com.mano.ashwa.data

/**
 * External links and social media URLs.
 */
object SocialLinks {
    const val GITHUB = "https://github.com/abhishek-0203"
    const val LINKEDIN = "https://www.linkedin.com/in/abhishek-verma-196789379/"
    const val EMAIL = "mailto:v.abhishek0203@gmail.com"
}

// Legacy constant for backward compatibility
@Deprecated("Use SocialLinks.GITHUB instead", ReplaceWith("SocialLinks.GITHUB"))
const val REPO_LINK = SocialLinks.GITHUB
