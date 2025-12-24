package com.mano.ashwa.theme

import androidx.compose.runtime.*
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.localStorage

/**
 * Global theme state that can be accessed from any component
 */
object ThemeState {
    private var _colorMode = mutableStateOf(ColorMode.DARK)

    val colorMode: State<ColorMode> get() = _colorMode

    fun toggle() {
        _colorMode.value = if (_colorMode.value == ColorMode.DARK) ColorMode.LIGHT else ColorMode.DARK
        saveToLocalStorage()
    }

    fun setColorMode(mode: ColorMode) {
        _colorMode.value = mode
        saveToLocalStorage()
    }

    fun isDark(): Boolean = _colorMode.value == ColorMode.DARK

    private fun saveToLocalStorage() {
        try {
            localStorage.setItem("theme", if (_colorMode.value == ColorMode.DARK) "dark" else "light")
        } catch (e: Exception) {
            // Ignore localStorage errors
        }
    }

    fun loadFromLocalStorage() {
        try {
            val savedTheme = localStorage.getItem("theme")
            _colorMode.value = if (savedTheme == "light") ColorMode.LIGHT else ColorMode.DARK
        } catch (e: Exception) {
            _colorMode.value = ColorMode.DARK
        }
    }
}

/**
 * Composable function to get current theme colors
 */
@Composable
fun rememberThemeColors(): ThemeColorSet {
    val colorMode by ThemeState.colorMode
    return remember(colorMode) {
        if (colorMode == ColorMode.DARK) darkThemeColors else lightThemeColors
    }
}

/**
 * Data class containing all theme colors
 */
data class ThemeColorSet(
    val isDark: Boolean,
    // Backgrounds
    val backgroundPrimary: String,
    val backgroundSecondary: String,
    val backgroundTertiary: String,
    val cardBackground: String,
    val cardBackgroundHover: String,
    // Text
    val textPrimary: String,
    val textSecondary: String,
    val textMuted: String,
    val textAccent: String,
    // Borders
    val borderPrimary: String,
    val borderAccent: String,
    // Header
    val headerBackground: String,
    // Banner
    val bannerBackground: String,
    val bannerOrb1: String,
    val bannerOrb2: String,
)

val darkThemeColors = ThemeColorSet(
    isDark = true,
    backgroundPrimary = "#0f172a",
    backgroundSecondary = "#1e293b",
    backgroundTertiary = "#334155",
    cardBackground = "rgba(30, 41, 59, 0.8)",
    cardBackgroundHover = "rgba(51, 65, 85, 0.9)",
    textPrimary = "#ffffff",
    textSecondary = "#94a3b8",
    textMuted = "#64748b",
    textAccent = "#a78bfa",
    borderPrimary = "rgba(255, 255, 255, 0.1)",
    borderAccent = "rgba(139, 92, 246, 0.5)",
    headerBackground = "linear-gradient(135deg, rgba(15, 23, 42, 0.95) 0%, rgba(30, 41, 59, 0.9) 100%)",
    bannerBackground = "linear-gradient(135deg, #0f172a 0%, #1e293b 50%, #0f172a 100%)",
    bannerOrb1 = "rgba(99, 102, 241, 0.15)",
    bannerOrb2 = "rgba(139, 92, 246, 0.1)",
)

val lightThemeColors = ThemeColorSet(
    isDark = false,
    backgroundPrimary = "#f8fafc",
    backgroundSecondary = "#e2e8f0",
    backgroundTertiary = "#cbd5e1",
    cardBackground = "rgba(255, 255, 255, 0.9)",
    cardBackgroundHover = "rgba(255, 255, 255, 1)",
    textPrimary = "#0f172a",
    textSecondary = "#475569",
    textMuted = "#64748b",
    textAccent = "#7c3aed",
    borderPrimary = "rgba(0, 0, 0, 0.1)",
    borderAccent = "rgba(99, 102, 241, 0.5)",
    headerBackground = "linear-gradient(135deg, rgba(248, 250, 252, 0.95) 0%, rgba(241, 245, 249, 0.9) 100%)",
    bannerBackground = "linear-gradient(135deg, #f8fafc 0%, #e2e8f0 50%, #f8fafc 100%)",
    bannerOrb1 = "rgba(99, 102, 241, 0.08)",
    bannerOrb2 = "rgba(139, 92, 246, 0.06)",
)

