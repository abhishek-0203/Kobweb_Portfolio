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

                // Only apply absolute positioning on desktop
                val isMobile = window.innerWidth <= 991

                if (isMobile) {
                    htmlElement.style.cssText = """
                        position: relative !important;
                        left: auto !important;
                        top: auto !important;
                        transform: none !important;
                        margin: 0 !important;
                        margin-left: 8px !important;
                        z-index: 100 !important;
                        font-weight: 900 !important;
                        font-size: ${if (window.innerWidth <= 576) "0.55rem" else "0.7rem"} !important;
                        letter-spacing: ${if (window.innerWidth <= 576) "0" else "0.5px"} !important;
                        text-transform: uppercase !important;
                        background: $textColor !important;
                        background-size: 300% auto !important;
                        -webkit-background-clip: text !important;
                        -webkit-text-fill-color: transparent !important;
                        background-clip: text !important;
                        padding: ${if (window.innerWidth <= 576) "4px 5px 4px 18px" else "5px 8px 5px 22px"} !important;
                        border: ${if (window.innerWidth <= 576) "1px" else "2px"} solid $borderColorBrand !important;
                        border-radius: 50px !important;
                        animation: gradient-shift 3s linear infinite !important;
                        transition: all 0.4s ease !important;
                        text-decoration: none !important;
                        max-width: ${if (window.innerWidth <= 576) "calc(100vw - 100px)" else "calc(100vw - 120px)"} !important;
                        overflow: hidden !important;
                        text-overflow: ellipsis !important;
                        white-space: nowrap !important;
                        flex-shrink: 1 !important;
                    """.trimIndent()
                } else {
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
                }

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

            // Handle responsive hiding of resume button
            val handleResize: () -> Unit = {
                val resumeBtn = document.querySelector(".resume-download-btn")
                resumeBtn?.let { btn ->
                    val btnElement = btn.asDynamic()
                    if (window.innerWidth <= 991) {
                        btnElement.style.display = "none"
                        btnElement.style.visibility = "hidden"
                    } else {
                        btnElement.style.display = "inline-flex"
                        btnElement.style.visibility = "visible"
                    }
                }

                // Handle theme toggle button repositioning on mobile (LEFT of hamburger)
                val themeBtn = document.querySelector(".theme-toggle-btn")
                themeBtn?.let { btn ->
                    val btnElement = btn.asDynamic()
                    if (window.innerWidth <= 991) {
                        btnElement.style.position = "fixed"
                        btnElement.style.transform = "none"
                        btnElement.style.zIndex = "1200"
                        btnElement.style.margin = "0"
                        when {
                            window.innerWidth <= 400 -> {
                                btnElement.style.right = "54px"
                                btnElement.style.top = "14px"
                                btnElement.style.width = "28px"
                                btnElement.style.height = "28px"
                            }
                            window.innerWidth <= 576 -> {
                                btnElement.style.right = "62px"
                                btnElement.style.top = "13px"
                                btnElement.style.width = "32px"
                                btnElement.style.height = "32px"
                            }
                            window.innerWidth <= 768 -> {
                                btnElement.style.right = "72px"
                                btnElement.style.top = "11px"
                                btnElement.style.width = "36px"
                                btnElement.style.height = "36px"
                            }
                            else -> {
                                btnElement.style.right = "80px"
                                btnElement.style.top = "10px"
                                btnElement.style.width = "38px"
                                btnElement.style.height = "38px"
                            }
                        }
                    } else {
                        btnElement.style.position = "absolute"
                        btnElement.style.right = "220px"
                        btnElement.style.top = "50%"
                        btnElement.style.transform = "translateY(-50%)"
                        btnElement.style.width = "44px"
                        btnElement.style.height = "44px"
                        btnElement.style.zIndex = "2002"
                    }
                }

                // Handle navbar toggler repositioning on mobile (FAR RIGHT)
                val navToggler = document.querySelector(".navbar-toggler")
                navToggler?.let { toggler ->
                    val togglerElement = toggler.asDynamic()
                    if (window.innerWidth <= 991) {
                        togglerElement.style.position = "fixed"
                        togglerElement.style.zIndex = "1150"
                        togglerElement.style.margin = "0"
                        when {
                            window.innerWidth <= 400 -> {
                                togglerElement.style.right = "6px"
                                togglerElement.style.top = "14px"
                                togglerElement.style.width = "38px"
                                togglerElement.style.height = "28px"
                            }
                            window.innerWidth <= 576 -> {
                                togglerElement.style.right = "8px"
                                togglerElement.style.top = "13px"
                                togglerElement.style.width = "42px"
                                togglerElement.style.height = "32px"
                            }
                            window.innerWidth <= 768 -> {
                                togglerElement.style.right = "10px"
                                togglerElement.style.top = "11px"
                                togglerElement.style.width = "46px"
                                togglerElement.style.height = "36px"
                            }
                            else -> {
                                togglerElement.style.right = "12px"
                                togglerElement.style.top = "10px"
                                togglerElement.style.width = "50px"
                                togglerElement.style.height = "38px"
                            }
                        }
                    }
                }

                // Handle navbar brand repositioning on mobile
                val navbarBrand = document.querySelector(".navbar-brand")
                navbarBrand?.let { brand ->
                    val brandElement = brand.asDynamic()
                    if (window.innerWidth <= 991) {
                        brandElement.style.position = "absolute"
                        brandElement.style.top = "50%"
                        brandElement.style.transform = "translateY(-50%)"
                        brandElement.style.margin = "0"
                        brandElement.style.flexShrink = "0"
                        brandElement.style.overflow = "hidden"
                        brandElement.style.textOverflow = "ellipsis"
                        brandElement.style.whiteSpace = "nowrap"
                        brandElement.style.zIndex = "1050"
                        when {
                            window.innerWidth <= 400 -> {
                                brandElement.style.fontSize = "0.4rem"
                                brandElement.style.padding = "3px 5px 3px 12px"
                                brandElement.style.letterSpacing = "0"
                                brandElement.style.left = "6px"
                                brandElement.style.maxWidth = "calc(100vw - 110px)"
                            }
                            window.innerWidth <= 576 -> {
                                brandElement.style.fontSize = "0.45rem"
                                brandElement.style.padding = "4px 6px 4px 15px"
                                brandElement.style.letterSpacing = "0"
                                brandElement.style.left = "8px"
                                brandElement.style.maxWidth = "calc(100vw - 120px)"
                            }
                            window.innerWidth <= 768 -> {
                                brandElement.style.fontSize = "0.5rem"
                                brandElement.style.padding = "5px 8px 5px 18px"
                                brandElement.style.letterSpacing = "0"
                                brandElement.style.left = "10px"
                                brandElement.style.maxWidth = "calc(100vw - 130px)"
                            }
                            else -> {
                                brandElement.style.fontSize = "0.55rem"
                                brandElement.style.padding = "6px 10px 6px 22px"
                                brandElement.style.letterSpacing = "0.5px"
                                brandElement.style.left = "12px"
                                brandElement.style.maxWidth = "calc(100vw - 140px)"
                            }
                        }
                    } else {
                        brandElement.style.position = "absolute"
                        brandElement.style.left = "20px"
                        brandElement.style.top = "50%"
                        brandElement.style.transform = "translateY(-50%)"
                        brandElement.style.fontSize = "1.1rem"
                        brandElement.style.padding = "10px 18px 10px 42px"
                        brandElement.style.letterSpacing = "2px"
                        brandElement.style.margin = "0"
                        brandElement.style.maxWidth = "none"
                    }
                }
            }

            // Initial call
            handleResize()

            // Add resize listener
            window.addEventListener("resize", { handleResize() })

            // Inject a style tag into the document head for maximum CSS priority
            val existingStyle = document.getElementById("navbar-collapse-override-styles")
            if (existingStyle == null) {
                val styleTag = document.createElement("style")
                styleTag.id = "navbar-collapse-override-styles"
                styleTag.textContent = """
                    @media (max-width: 991px) {
                        .navbar-collapse,
                        .navbar-collapse.collapse,
                        .navbar-collapse.show,
                        .navbar-collapse.collapsing,
                        .collapse.navbar-collapse,
                        .navbar .navbar-collapse,
                        div.navbar-collapse,
                        #navbarSupportedContent,
                        [class*="navbar-collapse"] {
                            position: fixed !important;
                            top: 72px !important;
                            right: 0 !important;
                            bottom: auto !important;
                            left: auto !important;
                            width: 240px !important;
                            max-width: 75vw !important;
                            height: auto !important;
                            max-height: calc(100vh - 90px) !important;
                            margin: 0 !important;
                            margin-left: auto !important;
                            padding: 16px 0 !important;
                            background: linear-gradient(165deg, rgba(15, 23, 42, 0.98) 0%, rgba(30, 41, 59, 0.98) 100%) !important;
                            backdrop-filter: blur(20px) !important;
                            -webkit-backdrop-filter: blur(20px) !important;
                            border-left: 1px solid rgba(139, 92, 246, 0.4) !important;
                            border-radius: 0 0 0 16px !important;
                            box-shadow: -10px 10px 40px rgba(0, 0, 0, 0.4) !important;
                            z-index: 1099 !important;
                            overflow-y: auto !important;
                            transform: none !important;
                        }
                        
                        .navbar-collapse:not(.show) {
                            display: none !important;
                        }
                        
                        .navbar-collapse.show {
                            display: block !important;
                            animation: dropdownSlideIn 0.25s ease-out !important;
                        }
                        
                        @keyframes dropdownSlideIn {
                            from { opacity: 0; transform: translateX(20px); }
                            to { opacity: 1; transform: translateX(0); }
                        }
                        
                        .navbar-collapse .navbar-nav {
                            padding: 8px 12px !important;
                            gap: 4px !important;
                            flex-direction: column !important;
                            align-items: stretch !important;
                        }
                        
                        .navbar-collapse .nav-item {
                            width: 100% !important;
                        }
                        
                        .navbar-collapse .nav-link {
                            display: block !important;
                            padding: 12px 16px !important;
                            margin: 2px 0 !important;
                            font-size: 0.9rem !important;
                            font-weight: 500 !important;
                            color: rgba(255, 255, 255, 0.9) !important;
                            background: rgba(255, 255, 255, 0.05) !important;
                            border: 1px solid rgba(139, 92, 246, 0.2) !important;
                            border-radius: 10px !important;
                            text-align: left !important;
                            transition: all 0.2s ease !important;
                        }
                        
                        .navbar-collapse .nav-link:hover {
                            background: rgba(139, 92, 246, 0.2) !important;
                            border-color: rgba(139, 92, 246, 0.5) !important;
                            transform: translateX(-4px) !important;
                        }
                        
                        .navbar-collapse .nav-link.active {
                            background: rgba(139, 92, 246, 0.25) !important;
                            border-color: rgba(139, 92, 246, 0.6) !important;
                            color: #c4b5fd !important;
                        }
                    }
                """.trimIndent()
                document.head?.appendChild(styleTag)
            }

            // Force navbar-collapse to open from RIGHT side
            val forceRightPosition: () -> Unit = {
                val navbarCollapse = document.querySelector(".navbar-collapse")
                navbarCollapse?.let { collapse ->
                    val collapseElement = collapse.asDynamic()
                    if (window.innerWidth <= 991) {
                        val isShown = collapse.asDynamic().classList?.contains("show") == true
                        collapseElement.style.cssText = """
                            position: fixed !important;
                            top: 72px !important;
                            right: 0 !important;
                            bottom: auto !important;
                            left: auto !important;
                            width: 240px !important;
                            max-width: 75vw !important;
                            height: auto !important;
                            max-height: calc(100vh - 90px) !important;
                            margin: 0 !important;
                            margin-left: auto !important;
                            padding: 16px 0 !important;
                            background: linear-gradient(165deg, rgba(15, 23, 42, 0.98) 0%, rgba(30, 41, 59, 0.98) 100%) !important;
                            backdrop-filter: blur(20px) !important;
                            border-left: 1px solid rgba(139, 92, 246, 0.4) !important;
                            border-radius: 0 0 0 16px !important;
                            box-shadow: -10px 10px 40px rgba(0, 0, 0, 0.4) !important;
                            z-index: 1099 !important;
                            overflow-y: auto !important;
                            transform: none !important;
                            display: ${if (isShown) "block" else "none"} !important;
                        """.trimIndent()
                    }
                }
            }

            // Initial call and observer for navbar state changes
            forceRightPosition()

            // Listen for Bootstrap collapse events
            val navbarCollapse = document.querySelector(".navbar-collapse")
            navbarCollapse?.addEventListener("show.bs.collapse", { forceRightPosition() })
            navbarCollapse?.addEventListener("shown.bs.collapse", { forceRightPosition() })

            // Also listen for click on navbar toggler
            val navToggler = document.querySelector(".navbar-toggler")
            navToggler?.addEventListener("click", {
                window.setTimeout({ forceRightPosition() }, 50)
                window.setTimeout({ forceRightPosition() }, 150)
                window.setTimeout({ forceRightPosition() }, 300)
            })

            // MutationObserver to catch class changes
            val observer = js("""
                new MutationObserver(function(mutations) { 
                    mutations.forEach(function(mutation) { 
                        if (mutation.attributeName === 'class') { 
                            var collapse = document.querySelector('.navbar-collapse'); 
                            if (collapse && window.innerWidth <= 991) { 
                                var isShown = collapse.classList.contains('show');
                                collapse.style.cssText = 'position: fixed !important; top: 72px !important; right: 0 !important; bottom: auto !important; left: auto !important; width: 240px !important; max-width: 75vw !important; height: auto !important; max-height: calc(100vh - 90px) !important; margin: 0 !important; margin-left: auto !important; padding: 16px 0 !important; background: linear-gradient(165deg, rgba(15, 23, 42, 0.98) 0%, rgba(30, 41, 59, 0.98) 100%) !important; backdrop-filter: blur(20px) !important; border-left: 1px solid rgba(139, 92, 246, 0.4) !important; border-radius: 0 0 0 16px !important; box-shadow: -10px 10px 40px rgba(0, 0, 0, 0.4) !important; z-index: 1099 !important; overflow-y: auto !important; transform: none !important; display: ' + (isShown ? 'block' : 'none') + ' !important;';
                            } 
                        } 
                    }); 
                })
            """)
            navbarCollapse?.let {
                observer.observe(it, js("{ attributes: true, attributeFilter: ['class'] }"))
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
        // Only render on larger screens - use CSS to hide on mobile
        A(href = AppStrings.RESUME_PATH, attrs = {
            attr("download", AppStrings.RESUME_FILENAME)
            attr("onclick", "if (window.forceDownload) { window.forceDownload('${AppStrings.RESUME_PATH}','${AppStrings.RESUME_FILENAME}'); } else { window.open('${AppStrings.RESUME_PATH}','_blank') }; return false;")
            attr("aria-label", "Download ${AppStrings.USER_NAME} Resume")
            attr("class", "resume-download-btn d-none d-lg-inline-flex")
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
            
            /* Bootstrap-like display utilities for mobile */
            @media (max-width: 991.98px) {
                .d-lg-inline-flex {
                    display: none !important;
                }
            }
            @media (min-width: 992px) {
                .d-lg-inline-flex {
                    display: inline-flex !important;
                }
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
            
            /* Mobile navbar layout */
            @media (max-width: 991px) {
                .navbar {
                    display: flex !important;
                    flex-wrap: nowrap !important;
                    align-items: center !important;
                    justify-content: flex-start !important;
                }
                
                .navbar > .container,
                .navbar > .container-fluid {
                    display: flex !important;
                    flex-wrap: nowrap !important;
                    align-items: center !important;
                    justify-content: flex-start !important;
                    width: 100% !important;
                    padding: 0 !important;
                }
                
                .navbar-brand {
                    position: relative !important;
                    left: auto !important;
                    transform: none !important;
                    flex-shrink: 1 !important;
                    min-width: 0 !important;
                }
                
                .navbar-brand:hover {
                    transform: scale(1.05) !important;
                }
                
                /* Override Bootstrap flex container alignment */
                .navbar,
                .navbar > .container,
                .navbar > .container-fluid,
                .navbar > .container-sm,
                .navbar > .container-md,
                .navbar > .container-lg,
                .navbar > .container-xl {
                    justify-content: flex-start !important;
                    align-items: center !important;
                }
                
                /* Mobile Dropdown Menu - Compact RIGHT side panel */
                .navbar-collapse,
                .navbar-collapse.collapse,
                .navbar-collapse.show,
                .navbar-collapse.collapsing,
                .collapse.navbar-collapse,
                #navbarContent,
                [id*="navbarContent"],
                .navbar .navbar-collapse,
                .navbar > .container > .navbar-collapse,
                .navbar > .container-fluid > .navbar-collapse {
                    position: fixed !important;
                    top: 72px !important;
                    right: 0 !important;
                    bottom: auto !important;
                    left: auto !important;
                    width: 240px !important;
                    max-width: 75vw !important;
                    height: auto !important;
                    max-height: calc(100vh - 90px) !important;
                    overflow-y: auto !important;
                    overflow-x: hidden !important;
                    background: linear-gradient(165deg, 
                        rgba(15, 23, 42, 0.98) 0%, 
                        rgba(30, 41, 59, 0.98) 100%) !important;
                    backdrop-filter: blur(20px) saturate(150%) !important;
                    -webkit-backdrop-filter: blur(20px) saturate(150%) !important;
                    border-left: 1px solid rgba(139, 92, 246, 0.4) !important;
                    border-right: none !important;
                    border-bottom: none !important;
                    border-radius: 0 0 0 16px !important;
                    box-shadow: 
                        -10px 10px 40px rgba(0, 0, 0, 0.4),
                        -2px 0 15px rgba(139, 92, 246, 0.1) !important;
                    padding: 16px 0 !important;
                    z-index: 1099 !important;
                    transform-origin: top right !important;
                    margin: 0 !important;
                    margin-left: auto !important;
                }
                
                /* Custom scrollbar for dropdown */
                .navbar-collapse::-webkit-scrollbar {
                    width: 6px !important;
                }
                
                .navbar-collapse::-webkit-scrollbar-track {
                    background: rgba(255, 255, 255, 0.05) !important;
                    border-radius: 3px !important;
                }
                
                .navbar-collapse::-webkit-scrollbar-thumb {
                    background: linear-gradient(180deg, rgba(139, 92, 246, 0.6), rgba(99, 102, 241, 0.6)) !important;
                    border-radius: 3px !important;
                }
                
                .navbar-collapse::-webkit-scrollbar-thumb:hover {
                    background: linear-gradient(180deg, rgba(139, 92, 246, 0.8), rgba(99, 102, 241, 0.8)) !important;
                }
                
                .navbar-collapse.show,
                .collapse.navbar-collapse.show,
                .navbar .navbar-collapse.show {
                    display: block !important;
                        animation: dropdownSlide 0.25s ease-out !important;
                    right: 0 !important;
                    left: auto !important;
                    transform: none !important;
                }
                
                .navbar-collapse:not(.show) {
                    display: none !important;
                }
                
                .navbar-collapse.collapsing,
                .collapse.navbar-collapse.collapsing {
                    display: block !important;
                    width: 0 !important;
                    right: 0 !important;
                    left: auto !important;
                    overflow: hidden !important;
                    transition: width 0.25s ease !important;
                }
                
                @keyframes dropdownSlide {
                    from {
                        opacity: 0;
                        transform: translateX(20px);
                    }
                    to {
                        opacity: 1;
                        transform: translateX(0);
                    }
                }
                
                .navbar-collapse .navbar-nav {
                    flex-direction: column !important;
                    align-items: stretch !important;
                    justify-content: flex-start !important;
                    width: 100% !important;
                    gap: 4px !important;
                    padding: 8px 12px !important;
                }
                
                .navbar-collapse .nav-item {
                    width: 100% !important;
                    text-align: left !important;
                    position: relative !important;
                }
                
                /* Staggered animation for nav items */
                .navbar-collapse .nav-item:nth-child(1) { animation: fadeInUp 0.25s 0.02s both !important; }
                .navbar-collapse .nav-item:nth-child(2) { animation: fadeInUp 0.25s 0.06s both !important; }
                .navbar-collapse .nav-item:nth-child(3) { animation: fadeInUp 0.25s 0.10s both !important; }
                .navbar-collapse .nav-item:nth-child(4) { animation: fadeInUp 0.25s 0.14s both !important; }
                .navbar-collapse .nav-item:nth-child(5) { animation: fadeInUp 0.25s 0.18s both !important; }
                .navbar-collapse .nav-item:nth-child(6) { animation: fadeInUp 0.25s 0.22s both !important; }
                
                @keyframes fadeInUp {
                    from {
                        opacity: 0;
                        transform: translateY(10px);
                    }
                    to {
                        opacity: 1;
                        transform: translateY(0);
                    }
                }
                
                .navbar-collapse .nav-link {
                    display: block !important;
                    width: 100% !important;
                    padding: 12px 16px !important;
                    font-size: 0.9rem !important;
                    font-weight: 500 !important;
                    text-align: left !important;
                    color: rgba(255, 255, 255, 0.9) !important;
                    background: rgba(255, 255, 255, 0.04) !important;
                    border-radius: 10px !important;
                    margin: 2px 0 !important;
                    transition: all 0.2s ease !important;
                    border: 1px solid rgba(139, 92, 246, 0.15) !important;
                    position: relative !important;
                    letter-spacing: 0.2px !important;
                }
                
                .navbar-collapse .nav-link:hover,
                .navbar-collapse .nav-link:focus {
                    background: rgba(139, 92, 246, 0.2) !important;
                    color: #ffffff !important;
                    transform: translateX(-4px) !important;
                    border-color: rgba(139, 92, 246, 0.5) !important;
                    box-shadow: 0 4px 12px rgba(139, 92, 246, 0.2) !important;
                }
                
                .navbar-collapse .nav-link.active {
                    background: rgba(139, 92, 246, 0.25) !important;
                    color: #c4b5fd !important;
                    border-color: rgba(167, 139, 250, 0.6) !important;
                    box-shadow: 0 4px 12px rgba(139, 92, 246, 0.25) !important;
                }
                
                /* Force right alignment override for Bootstrap flexbox */
                .navbar-collapse,
                .collapse.navbar-collapse,
                div.navbar-collapse,
                div.collapse.navbar-collapse {
                    flex-basis: auto !important;
                    flex-grow: 0 !important;
                    margin-left: auto !important;
                    align-self: flex-end !important;
                }
                
                @keyframes slideDown {
                    from {
                        opacity: 0;
                        transform: translateY(-10px);
                    }
                    to {
                        opacity: 1;
                        transform: translateY(0);
                    }
                }
            }
            
            /* Light mode mobile dropdown styles */
            @media (max-width: 991px) {
                .navbar.navbar-light .navbar-collapse,
                .navbar.navbar-light .navbar-collapse.show,
                .navbar.navbar-light .navbar-collapse.collapsing,
                .bg-light .navbar-collapse,
                .bg-light .navbar-collapse.show,
                .bg-light .navbar-collapse.collapsing,
                [data-bs-theme="light"] .navbar-collapse,
                [data-bs-theme="light"] .navbar-collapse.show {
                    background: linear-gradient(165deg, 
                        rgba(255, 255, 255, 0.98) 0%, 
                        rgba(248, 250, 252, 0.98) 100%) !important;
                    border-left: 1px solid rgba(99, 102, 241, 0.25) !important;
                    border-radius: 0 0 0 16px !important;
                    box-shadow: -10px 10px 40px rgba(0, 0, 0, 0.12) !important;
                    right: 0 !important;
                    left: auto !important;
                }
                
                .navbar.navbar-light .navbar-collapse .nav-link,
                .bg-light .navbar-collapse .nav-link,
                [data-bs-theme="light"] .navbar-collapse .nav-link {
                    color: rgba(15, 23, 42, 0.9) !important;
                    background: rgba(0, 0, 0, 0.03) !important;
                    border: 1px solid rgba(99, 102, 241, 0.12) !important;
                }
                
                .navbar.navbar-light .navbar-collapse .nav-link:hover,
                .navbar.navbar-light .navbar-collapse .nav-link:focus,
                .bg-light .navbar-collapse .nav-link:hover,
                .bg-light .navbar-collapse .nav-link:focus,
                [data-bs-theme="light"] .navbar-collapse .nav-link:hover,
                [data-bs-theme="light"] .navbar-collapse .nav-link:focus {
                    background: rgba(99, 102, 241, 0.15) !important;
                    color: #4f46e5 !important;
                    border-color: rgba(99, 102, 241, 0.35) !important;
                    transform: translateX(-4px) !important;
                    box-shadow: 0 4px 12px rgba(99, 102, 241, 0.15) !important;
                }
                
                .navbar.navbar-light .navbar-collapse .nav-link.active,
                .bg-light .navbar-collapse .nav-link.active,
                [data-bs-theme="light"] .navbar-collapse .nav-link.active {
                    background: rgba(99, 102, 241, 0.2) !important;
                    color: #4f46e5 !important;
                    border-color: rgba(99, 102, 241, 0.4) !important;
                }
            }
            
            .navbar-toggler {
                border: 2px solid rgba(167, 139, 250, 0.5) !important;
                background: linear-gradient(135deg, 
                    rgba(99, 102, 241, 0.1) 0%, 
                    rgba(139, 92, 246, 0.15) 100%) !important;
                border-radius: 12px !important;
                transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1) !important;
                box-shadow: 0 2px 8px rgba(139, 92, 246, 0.2) !important;
                backdrop-filter: blur(10px) !important;
                -webkit-backdrop-filter: blur(10px) !important;
            }
            
            .navbar-toggler:hover {
                background: linear-gradient(135deg, 
                    rgba(99, 102, 241, 0.2) 0%, 
                    rgba(139, 92, 246, 0.25) 100%) !important;
                border-color: rgba(167, 139, 250, 0.8) !important;
                transform: scale(1.05) !important;
                box-shadow: 
                    0 4px 15px rgba(139, 92, 246, 0.35),
                    0 0 20px rgba(139, 92, 246, 0.15) !important;
            }
            
            .navbar-toggler:focus {
                outline: none !important;
                box-shadow: 
                    0 0 0 3px rgba(139, 92, 246, 0.3),
                    0 4px 15px rgba(139, 92, 246, 0.35) !important;
            }
            
            .navbar-toggler:active {
                transform: scale(0.95) !important;
            }
            
            .navbar-toggler-icon {
                filter: brightness(1.2) !important;
                transition: transform 0.3s ease !important;
            }
            
            .navbar-toggler:hover .navbar-toggler-icon {
                transform: rotate(90deg) !important;
            }
            
            /* Animated hamburger to X transition when menu is open */
            .navbar-toggler[aria-expanded="true"] .navbar-toggler-icon {
                transform: rotate(180deg) !important;
            }
            
            .navbar-toggler[aria-expanded="true"] {
                background: linear-gradient(135deg, 
                    rgba(99, 102, 241, 0.25) 0%, 
                    rgba(139, 92, 246, 0.3) 100%) !important;
                border-color: rgba(167, 139, 250, 0.9) !important;
            }
            
            /* ============================================
               Mobile Responsive Styles
               ============================================ */
            @media (max-width: 991px) {
                /* Hide resume button with high specificity */
                .resume-download-btn,
                a.resume-download-btn,
                [class*="resume-download"] {
                    display: none !important;
                    visibility: hidden !important;
                    width: 0 !important;
                    height: 0 !important;
                    overflow: hidden !important;
                    opacity: 0 !important;
                    pointer-events: none !important;
                }
                
                .navbar-brand {
                    position: absolute !important;
                    left: 12px !important;
                    top: 50% !important;
                    transform: translateY(-50%) !important;
                    font-size: 0.55rem !important;
                    padding: 6px 10px 6px 22px !important;
                    letter-spacing: 0.5px !important;
                    margin: 0 !important;
                    flex-shrink: 0 !important;
                    max-width: calc(100vw - 160px) !important;
                    overflow: hidden !important;
                    text-overflow: ellipsis !important;
                    white-space: nowrap !important;
                    z-index: 1050 !important;
                }
                
                .navbar-brand::before {
                    left: 7px !important;
                    font-size: 0.6rem !important;
                }
                
                .navbar-brand:hover {
                    transform: translateY(-50%) scale(1.02) !important;
                }
                
                .navbar {
                    padding: 0.5rem 0.75rem !important;
                    display: flex !important;
                    flex-wrap: nowrap !important;
                    align-items: center !important;
                    justify-content: flex-end !important;
                    position: relative !important;
                    min-height: 60px !important;
                }
                
                .navbar > .container,
                .navbar > .container-fluid {
                    display: flex !important;
                    flex-wrap: nowrap !important;
                    align-items: center !important;
                    justify-content: flex-end !important;
                    width: 100% !important;
                    padding: 0 !important;
                }
                
                /* Theme toggle button - LEFT of hamburger menu with more gap */
                .theme-toggle-btn,
                button.theme-toggle-btn {
                    position: fixed !important;
                    right: 80px !important;
                    top: 10px !important;
                    margin: 0 !important;
                    width: 38px !important;
                    height: 38px !important;
                    min-width: 38px !important;
                    min-height: 38px !important;
                    flex-shrink: 0 !important;
                    z-index: 1200 !important;
                    transform: none !important;
                }
                
                /* Hamburger menu - FAR RIGHT position with smaller width */
                .navbar-toggler {
                    position: fixed !important;
                    right: 12px !important;
                    top: 10px !important;
                    margin: 0 !important;
                    flex-shrink: 0 !important;
                    padding: 6px 10px !important;
                    width: 50px !important;
                    min-width: 50px !important;
                    max-width: 50px !important;
                    min-height: 38px !important;
                    height: 38px !important;
                    z-index: 1150 !important;
                    background: rgba(255, 255, 255, 0.08) !important;
                    border-radius: 10px !important;
                }
            }
            
            @media (max-width: 768px) {
                .navbar-brand {
                    font-size: 0.5rem !important;
                    padding: 5px 8px 5px 18px !important;
                    letter-spacing: 0 !important;
                    max-width: calc(100vw - 150px) !important;
                    left: 10px !important;
                }
                
                .navbar-brand::before {
                    left: 5px !important;
                    font-size: 0.55rem !important;
                }
                
                .theme-toggle-btn,
                button.theme-toggle-btn {
                    width: 36px !important;
                    height: 36px !important;
                    min-width: 36px !important;
                    min-height: 36px !important;
                    right: 72px !important;
                    top: 11px !important;
                }
                
                .navbar-toggler {
                    padding: 5px 8px !important;
                    right: 10px !important;
                    top: 11px !important;
                    width: 46px !important;
                    min-width: 46px !important;
                    max-width: 46px !important;
                    min-height: 36px !important;
                    height: 36px !important;
                }
            }
            
            @media (max-width: 576px) {
                .navbar-brand {
                    font-size: 0.45rem !important;
                    padding: 4px 6px 4px 15px !important;
                    letter-spacing: 0 !important;
                    max-width: calc(100vw - 140px) !important;
                    border-width: 1px !important;
                    left: 8px !important;
                }
                
                .navbar-brand::before {
                    left: 4px !important;
                    font-size: 0.45rem !important;
                }
                
                .navbar-toggler {
                    padding: 4px 7px !important;
                    right: 8px !important;
                    top: 13px !important;
                    width: 42px !important;
                    min-width: 42px !important;
                    max-width: 42px !important;
                    min-height: 32px !important;
                    height: 32px !important;
                }
                
                .theme-toggle-btn,
                button.theme-toggle-btn {
                    width: 32px !important;
                    height: 32px !important;
                    min-width: 32px !important;
                    min-height: 32px !important;
                    right: 62px !important;
                    top: 13px !important;
                    border-radius: 8px !important;
                }
                
                .navbar {
                    padding: 0.4rem 0.5rem !important;
                    min-height: 55px !important;
                }
            }
            
            @media (max-width: 400px) {
                .navbar-brand {
                    font-size: 0.4rem !important;
                    padding: 3px 5px 3px 12px !important;
                    max-width: calc(100vw - 130px) !important;
                    left: 6px !important;
                }
                
                .navbar-brand::before {
                    left: 3px !important;
                    font-size: 0.4rem !important;
                }
                
                .navbar-toggler {
                    padding: 4px 6px !important;
                    right: 6px !important;
                    top: 14px !important;
                    width: 38px !important;
                    min-width: 38px !important;
                    max-width: 38px !important;
                    min-height: 28px !important;
                    height: 28px !important;
                }
                
                .theme-toggle-btn,
                button.theme-toggle-btn {
                    width: 28px !important;
                    height: 28px !important;
                    min-width: 28px !important;
                    min-height: 28px !important;
                    right: 54px !important;
                    top: 14px !important;
                }
                
                .navbar {
                    min-height: 52px !important;
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

