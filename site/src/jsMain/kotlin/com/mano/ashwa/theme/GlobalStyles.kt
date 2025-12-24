package com.mano.ashwa.theme

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Style

/**
 * Injects global CSS styles, animations, and enhancements.
 * Call this composable once in the app entry or layout.
 */
@Composable
fun GlobalStyles() {
    Div({
        style { property("display", "none") }
        attr("id", "global-styles-injector")
    }) {
        Style {
            """
            /* ============================================
               Global Animations
               ============================================ */
            
            @keyframes fadeInUp {
                from {
                    opacity: 0;
                    transform: translateY(30px);
                }
                to {
                    opacity: 1;
                    transform: translateY(0);
                }
            }
            
            @keyframes fadeIn {
                from { opacity: 0; }
                to { opacity: 1; }
            }
            
            @keyframes slideInLeft {
                from {
                    opacity: 0;
                    transform: translateX(-30px);
                }
                to {
                    opacity: 1;
                    transform: translateX(0);
                }
            }
            
            @keyframes blink {
                50% { border-color: transparent; }
            }
            
            @keyframes shimmer {
                0% { background-position: -200% 0; }
                100% { background-position: 200% 0; }
            }
            
            @keyframes glow {
                0%, 100% {
                    box-shadow: 0 0 20px rgba(99, 102, 241, 0.3);
                }
                50% {
                    box-shadow: 0 0 40px rgba(139, 92, 246, 0.5);
                }
            }
            
            @keyframes gradient-shift {
                0% { background-position: 0% 50%; }
                50% { background-position: 100% 50%; }
                100% { background-position: 0% 50%; }
            }
            
            @keyframes pulse {
                0%, 100% {
                    opacity: 1;
                    transform: scale(1);
                }
                50% {
                    opacity: 0.7;
                    transform: scale(1.1);
                }
            }
            
            @keyframes float {
                0%, 100% { 
                    transform: translateY(0) rotate(0deg); 
                }
                50% { 
                    transform: translateY(-20px) rotate(5deg); 
                }
            }
            
            /* ============================================
               Scrollbar Styling
               ============================================ */
            
            ::-webkit-scrollbar {
                width: 10px;
                height: 10px;
            }
            
            ::-webkit-scrollbar-track {
                background: #0f172a;
                border-radius: 5px;
            }
            
            ::-webkit-scrollbar-thumb {
                background: linear-gradient(135deg, #6366f1, #a855f7);
                border-radius: 5px;
            }
            
            ::-webkit-scrollbar-thumb:hover {
                background: linear-gradient(135deg, #818cf8, #c084fc);
            }
            
            /* ============================================
               Selection Styling
               ============================================ */
            
            ::selection {
                background: rgba(139, 92, 246, 0.3);
                color: #ffffff;
            }
            
            ::-moz-selection {
                background: rgba(139, 92, 246, 0.3);
                color: #ffffff;
            }
            
            /* ============================================
               Card Hover Effects
               ============================================ */
            
            .skill-card:hover,
            .project-card:hover,
            .experience-card:hover {
                transform: translateY(-5px);
                box-shadow: 0 20px 40px rgba(0, 0, 0, 0.4), 
                            0 0 30px rgba(99, 102, 241, 0.15);
            }
            
            /* ============================================
               Link Styling
               ============================================ */
            
            a {
                transition: color 0.2s ease, opacity 0.2s ease;
            }
            
            a:hover {
                opacity: 0.9;
            }
            
            /* ============================================
               Button Base Styles
               ============================================ */
            
            .btn-gradient {
                background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 50%, #a855f7 100%);
                border: none;
                color: white;
                padding: 12px 24px;
                border-radius: 25px;
                font-weight: 600;
                cursor: pointer;
                transition: all 0.3s ease;
                box-shadow: 0 4px 15px rgba(99, 102, 241, 0.3);
            }
            
            .btn-gradient:hover {
                transform: translateY(-2px);
                box-shadow: 0 8px 25px rgba(99, 102, 241, 0.4);
            }
            
            /* ============================================
               Page Section Animations
               ============================================ */
            
            .animate-on-scroll {
                opacity: 0;
                animation: fadeInUp 0.6s ease forwards;
            }
            
            .animate-delay-1 { animation-delay: 0.1s; }
            .animate-delay-2 { animation-delay: 0.2s; }
            .animate-delay-3 { animation-delay: 0.3s; }
            .animate-delay-4 { animation-delay: 0.4s; }
            
            /* ============================================
               Typography Enhancements
               ============================================ */
            
            h1, h2, h3, h4, h5, h6 {
                letter-spacing: -0.02em;
            }
            
            /* ============================================
               Focus States for Accessibility
               ============================================ */
            
            :focus-visible {
                outline: 2px solid #a855f7;
                outline-offset: 2px;
            }
            
            /* ============================================
               Smooth Page Transitions
               ============================================ */
            
            body {
                scroll-behavior: smooth;
            }
            
            /* Reduce motion for users who prefer it */
            @media (prefers-reduced-motion: reduce) {
                *,
                *::before,
                *::after {
                    animation-duration: 0.01ms !important;
                    animation-iteration-count: 1 !important;
                    transition-duration: 0.01ms !important;
                }
            }
            """.trimIndent()
        }
    }
}

