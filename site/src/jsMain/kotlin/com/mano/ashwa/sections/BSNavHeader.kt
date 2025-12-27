package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mano.ashwa.components.widgets.NavTabDataList
import com.mano.ashwa.navigation.Screen
import com.mano.ashwa.utils.AppStrings
import com.stevdza.san.kotlinbs.components.BSNavBar
import com.stevdza.san.kotlinbs.models.BackgroundStyle
import com.stevdza.san.kotlinbs.models.navbar.NavBarBrand
import com.stevdza.san.kotlinbs.models.navbar.NavBarExpand
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.icons.fa.FaDownload
import com.varabyte.kobweb.silk.components.icons.fa.FaMoon
import com.varabyte.kobweb.silk.components.icons.fa.FaSun
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.cssRem
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Button
import kotlinx.browser.window
import kotlinx.browser.document

@Composable
fun BSHeader(ctx: PageContext, colorMode: ColorMode = ColorMode.DARK, onToggleColorMode: () -> Unit = {}) {
    val isDark = colorMode == ColorMode.DARK

    // Theme-aware colors
    val headerBg = if (isDark) {
        "linear-gradient(135deg, rgba(15, 23, 42, 0.95) 0%, rgba(30, 41, 59, 0.9) 100%)"
    } else {
        "linear-gradient(135deg, rgba(248, 250, 252, 0.95) 0%, rgba(241, 245, 249, 0.9) 100%)"
    }
    val borderColor = if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.1)"

    // Apply enhanced brand styles after component mounts
    LaunchedEffect(colorMode) {
        window.setTimeout({
            val brand = document.querySelector(".navbar-brand")
            brand?.let { element ->
                val htmlElement = element.asDynamic()
                val textColor = if (isDark) {
                    "linear-gradient(90deg, #00d4ff, #00ff88, #ffee00, #ff00aa, #aa00ff, #00d4ff)"
                } else {
                    "linear-gradient(90deg, #6366f1, #8b5cf6, #a855f7, #ec4899, #6366f1)"
                }
                val borderColorBrand = if (isDark) "rgba(0, 212, 255, 0.6)" else "rgba(99, 102, 241, 0.6)"

                htmlElement.style.cssText = """
                    position: absolute !important;
                    left: 20px !important;
                    top: 50% !important;
                    transform: translateY(-50%) !important;
                    margin: 0 !important;
                    z-index: 100 !important;
                    font-weight: 900 !important;
                    font-size: 1.1rem !important;
                    letter-spacing: 2px !important;
                    text-transform: uppercase !important;
                    background: $textColor !important;
                    background-size: 300% auto !important;
                    -webkit-background-clip: text !important;
                    -webkit-text-fill-color: transparent !important;
                    background-clip: text !important;
                    padding: 10px 18px 10px 42px !important;
                    border: 2px solid $borderColorBrand !important;
                    border-radius: 50px !important;
                    animation: gradient-shift 3s linear infinite !important;
                    transition: all 0.4s ease !important;
                    text-decoration: none !important;
                """.trimIndent()

                val currentText = htmlElement.innerText as? String ?: ""
                if (!currentText.startsWith("⚡")) {
                    htmlElement.innerHTML = "⚡ $currentText"
                }
            }

            // Update nav links for theme
            val navLinks = document.querySelectorAll(".nav-link")
            for (i in 0 until navLinks.length) {
                val link = navLinks.item(i)?.asDynamic()
                link?.style?.color = if (isDark) "rgba(255, 255, 255, 0.85)" else "rgba(15, 23, 42, 0.85)"
            }
        }, 100)
    }

    Div(attrs = {
        style {
            property("position", "fixed")
            property("top", "0")
            property("left", "0")
            property("right", "0")
            property("z-index", "1100")
            property("height", "4.5rem")
            property("background", headerBg)
            property("backdrop-filter", "blur(12px)")
            property("-webkit-backdrop-filter", "blur(12px)")
            property("border-bottom", "1px solid $borderColor")
            property("box-shadow", if (isDark) "0 4px 30px rgba(0, 0, 0, 0.3)" else "0 4px 30px rgba(0, 0, 0, 0.1)")
            property("transition", "all 0.3s ease")
        }
    }) {
        // Determine active tab based on current path
        val currentPath = window.location.pathname
        val initialSelectedTab = Screen.fromRoute(currentPath)?.id ?: Screen.Home.id

        var selectedTab by remember { mutableStateOf(initialSelectedTab) }

        BSNavBar(
            modifier = Modifier.fillMaxWidth().height(4.5.cssRem),
            stickyTop = false,
            itemsAlignment = Alignment.CenterHorizontally,
            brand = NavBarBrand(
                title = AppStrings.SITE_TITLE.uppercase(),
                href = "#"
            ),
            expand = NavBarExpand.LG,
            backgroundStyle = if (isDark) BackgroundStyle.Dark else BackgroundStyle.Light,
            items = NavTabDataList(
                ctx,
                selectedTab,
                onSelect = { id -> selectedTab = id }
            )
        )

        // Theme Toggle Button
        ThemeToggleButton(isDark, onToggleColorMode)

        // Enhanced Download Resume button with gradient and hover effects
        A(href = AppStrings.RESUME_PATH, attrs = {
            attr("download", AppStrings.RESUME_FILENAME)
            attr("onclick", "if (window.forceDownload) { window.forceDownload('${AppStrings.RESUME_PATH}','${AppStrings.RESUME_FILENAME}'); } else { window.open('${AppStrings.RESUME_PATH}','_blank') }; return false;")
            attr("aria-label", "Download ${AppStrings.USER_NAME} Resume")
            attr("class", "resume-download-btn")
            attr("style", """
                position: absolute;
                right: 20px;
                top: 50%;
                transform: translateY(-50%);
                z-index: 2000;
                display: inline-flex;
                align-items: center;
                gap: 8px;
                padding: 10px 20px;
                background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 50%, #a855f7 100%);
                color: #ffffff;
                font-weight: 600;
                font-size: 14px;
                letter-spacing: 0.5px;
                border: none;
                border-radius: 25px;
                cursor: pointer;
                text-decoration: none;
                box-shadow: 0 4px 15px rgba(99, 102, 241, 0.4), 0 0 0 0 rgba(139, 92, 246, 0.5);
                transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
                animation: pulse-glow 2s infinite;
            """.trimIndent())
            // Add hover styles via onmouseenter/leave
            attr("onmouseenter", "this.style.transform='translateY(-50%) scale(1.05)';this.style.boxShadow='0 6px 25px rgba(99, 102, 241, 0.6), 0 0 20px rgba(139, 92, 246, 0.4)'")
            attr("onmouseleave", "this.style.transform='translateY(-50%) scale(1)';this.style.boxShadow='0 4px 15px rgba(99, 102, 241, 0.4), 0 0 0 0 rgba(139, 92, 246, 0.5)'")
        }) {
            FaDownload(size = IconSize.SM, modifier = Modifier.styleModifier { property("margin-right", "4px") })
            SpanText(
                "Download Resume",
                modifier = Modifier.styleModifier {
                    property("cursor", "pointer")
                }
            )
        }
    }

    // Inject keyframe animation for the button glow effect
    InjectNavbarStyles()
}

@Composable
private fun InjectNavbarStyles() {
    Div({
        style {
            property("display", "none")
        }
        attr("id", "navbar-styles-injector")
    }) {
        // Inject CSS keyframes for animations
        org.jetbrains.compose.web.dom.Style {
            """
            /* ============================================
               Button Animations
               ============================================ */
            @keyframes pulse-glow {
                0%, 100% {
                    box-shadow: 0 4px 15px rgba(99, 102, 241, 0.4), 0 0 0 0 rgba(139, 92, 246, 0.5);
                }
                50% {
                    box-shadow: 0 4px 20px rgba(99, 102, 241, 0.5), 0 0 15px rgba(139, 92, 246, 0.3);
                }
            }
            
            .resume-download-btn:active {
                transform: translateY(-50%) scale(0.98) !important;
            }
            
            /* ============================================
               Brand/Logo Text Effects - Animated Gradient
               ============================================ */
            @keyframes gradient-shift {
                0% { background-position: 0% 50%; }
                50% { background-position: 100% 50%; }
                100% { background-position: 0% 50%; }
            }
            
            @keyframes text-glow {
                0%, 100% {
                    text-shadow: 0 0 10px rgba(96, 165, 250, 0.5),
                                 0 0 20px rgba(167, 139, 250, 0.3),
                                 0 0 30px rgba(167, 139, 250, 0.2);
                }
                50% {
                    text-shadow: 0 0 20px rgba(96, 165, 250, 0.8),
                                 0 0 40px rgba(167, 139, 250, 0.5),
                                 0 0 60px rgba(167, 139, 250, 0.3);
                }
            }
            
            @keyframes shimmer {
                0% { background-position: -200% center; }
                100% { background-position: 200% center; }
            }
            
            @keyframes float-brand {
                0%, 100% { transform: translateX(-50%) translateY(0); }
                50% { transform: translateX(-50%) translateY(-2px); }
            }
            
            @keyframes border-glow {
                0%, 100% { 
                    border-color: rgba(99, 102, 241, 0.5);
                    box-shadow: 0 0 15px rgba(99, 102, 241, 0.3), inset 0 0 10px rgba(99, 102, 241, 0.1);
                }
                50% { 
                    border-color: rgba(167, 139, 250, 0.7);
                    box-shadow: 0 0 25px rgba(167, 139, 250, 0.4), inset 0 0 15px rgba(167, 139, 250, 0.15);
                }
            }
            
            .navbar-brand {
                /* Positioning */
                position: absolute !important;
                left: 20px !important;
                top: 50% !important;
                transform: translateY(-50%) !important;
                margin: 0 !important;
                z-index: 100 !important;
                
                /* Typography */
                font-weight: 900 !important;
                font-size: 1.2rem !important;
                letter-spacing: 3px !important;
                text-transform: uppercase !important;
                
                /* Gradient Text */
                background: linear-gradient(90deg, #00d4ff, #00ff88, #ffee00, #ff00aa, #aa00ff, #00d4ff) !important;
                background-size: 300% auto !important;
                -webkit-background-clip: text !important;
                -webkit-text-fill-color: transparent !important;
                background-clip: text !important;
                
                /* Container Styling */
                padding: 12px 20px 12px 48px !important;
                border: 2px solid rgba(0, 212, 255, 0.5) !important;
                border-radius: 50px !important;
                
                /* Effects */
                animation: gradient-shift 3s linear infinite, brand-glow 2s ease-in-out infinite !important;
                transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1) !important;
            }
            
            @keyframes brand-glow {
                0%, 100% {
                    border-color: rgba(0, 212, 255, 0.5);
                    box-shadow: 0 0 10px rgba(0, 212, 255, 0.3), 0 0 20px rgba(170, 0, 255, 0.2);
                }
                50% {
                    border-color: rgba(255, 0, 170, 0.5);
                    box-shadow: 0 0 15px rgba(0, 255, 136, 0.4), 0 0 30px rgba(255, 0, 170, 0.3);
                }
            }
            
            .navbar-brand::before {
                content: '⚡' !important;
                position: absolute !important;
                left: 16px !important;
                top: 50% !important;
                transform: translateY(-50%) !important;
                font-size: 1.2rem !important;
                -webkit-text-fill-color: initial !important;
                animation: sparkle 1.5s ease-in-out infinite !important;
            }
            
            @keyframes sparkle {
                0%, 100% { transform: translateY(-50%) scale(1) rotate(0deg); }
                25% { transform: translateY(-50%) scale(1.15) rotate(10deg); }
                50% { transform: translateY(-50%) scale(1) rotate(0deg); }
                75% { transform: translateY(-50%) scale(1.15) rotate(-10deg); }
            }
            
            .navbar-brand:hover {
                letter-spacing: 5px !important;
                transform: translateY(-50%) scale(1.05) !important;
                border-color: rgba(255, 238, 0, 0.8) !important;
                box-shadow: 0 0 20px rgba(0, 212, 255, 0.5), 0 0 40px rgba(255, 0, 170, 0.4) !important;
            }
            
            .navbar-brand:hover::before {
                animation: sparkle 0.4s ease-in-out infinite !important;
            }
            
            /* ============================================
               Navigation Link Effects
               ============================================ */
            .nav-link {
                position: relative !important;
                font-weight: 500 !important;
                font-size: 0.95rem !important;
                letter-spacing: 0.5px !important;
                color: rgba(255, 255, 255, 0.85) !important;
                padding: 8px 16px !important;
                transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1) !important;
                text-transform: uppercase !important;
            }
            
            .nav-link::before {
                content: '' !important;
                position: absolute !important;
                top: 0 !important;
                left: 0 !important;
                right: 0 !important;
                bottom: 0 !important;
                background: linear-gradient(135deg, rgba(99, 102, 241, 0.1) 0%, rgba(139, 92, 246, 0.1) 100%) !important;
                border-radius: 8px !important;
                opacity: 0 !important;
                transition: opacity 0.3s ease !important;
                z-index: -1 !important;
            }
            
            .nav-link:hover::before {
                opacity: 1 !important;
            }
            
            .nav-link::after {
                content: '' !important;
                position: absolute !important;
                bottom: -2px !important;
                left: 50% !important;
                width: 0 !important;
                height: 2px !important;
                background: linear-gradient(90deg, #6366f1, #a855f7, #f472b6) !important;
                background-size: 200% auto !important;
                transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1) !important;
                transform: translateX(-50%) !important;
                border-radius: 2px !important;
                animation: gradient-shift 2s ease infinite !important;
            }
            
            .nav-link:hover::after,
            .nav-link.active::after {
                width: 80% !important;
                box-shadow: 0 0 10px rgba(139, 92, 246, 0.5) !important;
            }
            
            .nav-link:hover {
                color: #ffffff !important;
                text-shadow: 0 0 10px rgba(167, 139, 250, 0.5) !important;
                transform: translateY(-1px) !important;
            }
            
            .nav-link.active {
                color: #a78bfa !important;
                text-shadow: 0 0 15px rgba(167, 139, 250, 0.6) !important;
            }
            
            /* ============================================
               Navbar Container Enhancement
               ============================================ */
            .navbar {
                border-bottom: 1px solid rgba(255, 255, 255, 0.05) !important;
                justify-content: center !important;
            }
            
            .navbar-collapse {
                justify-content: center !important;
            }
            
            .navbar-nav {
                align-items: center !important;
                justify-content: center !important;
            }
            
            /* Navbar brand positioning - merged with main styles above */
            @media (max-width: 991px) {
                .navbar-brand {
                    position: relative !important;
                    left: auto !important;
                    transform: none !important;
                }
                .navbar-brand:hover {
                    transform: scale(1.05) !important;
                }
            }
            
            .navbar-toggler {
                border-color: rgba(167, 139, 250, 0.5) !important;
                transition: all 0.3s ease !important;
            }
            
            .navbar-toggler:hover {
                background: rgba(167, 139, 250, 0.1) !important;
                border-color: rgba(167, 139, 250, 0.8) !important;
            }
            
            .navbar-toggler-icon {
                filter: brightness(1.2) !important;
            }
            
            /* ============================================
               Mobile Responsive Styles
               ============================================ */
            @media (max-width: 991px) {
                .resume-download-btn {
                    display: none !important;
                }
                
                .navbar-brand {
                    font-size: 0.9rem !important;
                    padding: 8px 14px 8px 38px !important;
                    letter-spacing: 1.5px !important;
                }
            }
            
            @media (max-width: 768px) {
                .navbar-brand {
                    font-size: 0.8rem !important;
                    padding: 6px 12px 6px 32px !important;
                    letter-spacing: 1px !important;
                    left: 10px !important;
                }
                
                .navbar-brand::before {
                    left: 10px !important;
                    font-size: 1rem !important;
                }
            }
            
            @media (max-width: 576px) {
                .navbar-brand {
                    font-size: 0.65rem !important;
                    padding: 5px 8px 5px 24px !important;
                    letter-spacing: 0.5px !important;
                    left: 5px !important;
                    border-width: 1px !important;
                }
                
                .navbar-brand::before {
                    left: 6px !important;
                    font-size: 0.8rem !important;
                }
                
                .navbar-toggler {
                    padding: 4px 8px !important;
                    margin-right: 5px !important;
                }
            }
            
            /* Theme Toggle Button Responsive */
            @media (max-width: 991px) {
                .theme-toggle-btn {
                    right: 70px !important;
                    width: 38px !important;
                    height: 38px !important;
                }
            }
            
            @media (max-width: 576px) {
                .theme-toggle-btn {
                    right: 55px !important;
                    width: 34px !important;
                    height: 34px !important;
                    border-radius: 8px !important;
                }
            }
            """.trimIndent()
        }
    }
}

@Composable
private fun ThemeToggleButton(isDark: Boolean, onToggle: () -> Unit) {
    val bgColor = if (isDark) "rgba(255, 255, 255, 0.1)" else "rgba(0, 0, 0, 0.1)"
    val hoverBgColor = if (isDark) "rgba(255, 255, 255, 0.2)" else "rgba(0, 0, 0, 0.15)"
    val iconColor = if (isDark) "#fbbf24" else "#6366f1"

    Button(attrs = {
        onClick { onToggle() }
        attr("class", "theme-toggle-btn")
        style {
            property("position", "absolute")
            property("right", "220px")
            property("top", "50%")
            property("transform", "translateY(-50%)")
            property("z-index", "2002")
            property("display", "flex")
            property("align-items", "center")
            property("justify-content", "center")
            property("width", "44px")
            property("height", "44px")
            property("background", bgColor)
            property("border", "1px solid ${if (isDark) "rgba(255, 255, 255, 0.15)" else "rgba(0, 0, 0, 0.1)"}")
            property("border-radius", "12px")
            property("cursor", "pointer")
            property("transition", "all 0.3s ease")
            property("color", iconColor)
            property("box-shadow", if (isDark) "0 2px 10px rgba(0, 0, 0, 0.2)" else "0 2px 10px rgba(0, 0, 0, 0.1)")
        }
        attr("onmouseenter", "this.style.background='$hoverBgColor';this.style.transform='translateY(-50%) scale(1.1)'")
        attr("onmouseleave", "this.style.background='$bgColor';this.style.transform='translateY(-50%) scale(1)'")
        attr("aria-label", if (isDark) "Switch to light mode" else "Switch to dark mode")
        attr("title", if (isDark) "Switch to light mode" else "Switch to dark mode")
    }) {
        if (isDark) {
            FaSun(size = IconSize.LG)
        } else {
            FaMoon(size = IconSize.LG)
        }
    }
}

