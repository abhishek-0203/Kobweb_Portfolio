package com.mano.ashwa.theme

import com.varabyte.kobweb.silk.theme.colors.ColorMode

/**
 * Centralized color constants for consistent theming across the app.
 * Supports both Light and Dark themes.
 */
object ThemeColors {
    // ==================== DARK THEME ====================
    object Dark {
        // Background colors
        const val BACKGROUND_PRIMARY = "#0f172a"
        const val BACKGROUND_SECONDARY = "#1e293b"
        const val BACKGROUND_TERTIARY = "#334155"
        const val CARD_BACKGROUND = "rgba(30, 41, 59, 0.8)"
        const val CARD_BACKGROUND_HOVER = "rgba(51, 65, 85, 0.9)"

        // Text colors
        const val TEXT_PRIMARY = "#ffffff"
        const val TEXT_SECONDARY = "#94a3b8"
        const val TEXT_MUTED = "#64748b"
        const val TEXT_ACCENT = "#a78bfa"

        // Border colors
        const val BORDER_PRIMARY = "rgba(255, 255, 255, 0.1)"
        const val BORDER_ACCENT = "rgba(139, 92, 246, 0.5)"

        // Header/Navbar
        const val HEADER_BACKGROUND = "linear-gradient(135deg, rgba(15, 23, 42, 0.95) 0%, rgba(30, 41, 59, 0.9) 100%)"

        // Banner
        const val BANNER_BACKGROUND = "linear-gradient(135deg, #0f172a 0%, #1e293b 50%, #0f172a 100%)"
        const val BANNER_ORB_1 = "rgba(99, 102, 241, 0.15)"
        const val BANNER_ORB_2 = "rgba(139, 92, 246, 0.1)"
    }

    // ==================== LIGHT THEME ====================
    object Light {
        // Background colors
        const val BACKGROUND_PRIMARY = "#f8fafc"
        const val BACKGROUND_SECONDARY = "#e2e8f0"
        const val BACKGROUND_TERTIARY = "#cbd5e1"
        const val CARD_BACKGROUND = "rgba(255, 255, 255, 0.9)"
        const val CARD_BACKGROUND_HOVER = "rgba(255, 255, 255, 1)"

        // Text colors
        const val TEXT_PRIMARY = "#0f172a"
        const val TEXT_SECONDARY = "#475569"
        const val TEXT_MUTED = "#64748b"
        const val TEXT_ACCENT = "#7c3aed"

        // Border colors
        const val BORDER_PRIMARY = "rgba(0, 0, 0, 0.1)"
        const val BORDER_ACCENT = "rgba(124, 58, 237, 0.5)"

        // Header/Navbar
        const val HEADER_BACKGROUND = "linear-gradient(135deg, rgba(248, 250, 252, 0.95) 0%, rgba(241, 245, 249, 0.9) 100%)"

        // Banner
        const val BANNER_BACKGROUND = "linear-gradient(135deg, #f8fafc 0%, #e2e8f0 50%, #f8fafc 100%)"
        const val BANNER_ORB_1 = "rgba(99, 102, 241, 0.1)"
        const val BANNER_ORB_2 = "rgba(139, 92, 246, 0.08)"
    }

    // ==================== SHARED ACCENT COLORS ====================
    const val PRIMARY = "#6366f1"
    const val PRIMARY_HOVER = "#4f46e5"
    const val ACCENT_PURPLE = "#a855f7"
    const val ACCENT_PINK = "#ec4899"
    const val ACCENT_CYAN = "#06b6d4"
    const val ACCENT_GREEN = "#22c55e"

    // Gradients
    object Gradients {
        const val PRIMARY = "linear-gradient(135deg, #6366f1 0%, #8b5cf6 50%, #a855f7 100%)"
        const val ACCENT = "linear-gradient(135deg, #f472b6 0%, #a78bfa 100%)"
        const val TEXT_RAINBOW = "linear-gradient(90deg, #60a5fa, #a78bfa, #f472b6)"
    }

    // Legacy support
    const val CONTENT_BACKGROUND = "#212125"
    const val CARD_BACKGROUND = "rgba(255,255,255,0.03)"
    const val CARD_BACKGROUND_FALLBACK = "rgba(255,255,255,0.06)"
    const val TEXT_PRIMARY = "#ffffff"
    const val TEXT_SECONDARY = "#cfcfcf"
    const val CHIP_DEFAULT = "#2196f3"
}

/**
 * Helper function to get theme-specific colors
 */
fun ColorMode.getColors() = when (this) {
    ColorMode.DARK -> ThemeColors.Dark
    ColorMode.LIGHT -> ThemeColors.Light
}

/**
 * Spacing constants for consistent layout.
 */
object Spacing {
    const val XS = 4
    const val SM = 8
    const val MD = 16
    const val LG = 24
    const val XL = 32
    const val XXL = 48
}

/**
 * Font size constants for consistent typography.
 */
object FontSizes {
    const val SMALL = 14
    const val BODY = 16
    const val LARGE = 18
    const val TITLE = 20
    const val HEADING_SM = 24
    const val HEADING_MD = 32
    const val HEADING_LG = 42
    const val PAGE_TITLE = 48
}

