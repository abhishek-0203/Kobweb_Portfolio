package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mano.ashwa.components.widgets.NavTabDataList
import com.mano.ashwa.navigation.Screen
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
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.cssRem
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Div
import kotlinx.browser.window

@Composable
fun BSHeader(ctx: PageContext) {
    // Render navbar
    Div({
        // Make the header fixed so it doesn't scroll with the page. Keep full width and high z-index.
        style {
            property("position", "fixed")
            property("top", "0")
            property("left", "0")
            property("right", "0")
            property("z-index", "1100")
            // Ensure the container uses the same height as the navbar so absolute children align correctly
            property("height", "4rem")
        }
    }) {

        // Determine active tab based on current path so active nav link is highlighted
        val currentPath = window.location.pathname
        val initialSelectedTab = when (currentPath) {
            Screen.Home.route -> Screen.Home.id
            Screen.Skill.route -> Screen.Skill.id
            Screen.Project.route -> Screen.Project.id
            Screen.CoverLetter.route -> Screen.CoverLetter.id
            else -> Screen.Home.id
        }

        // Remember a local selected state so that when a tab is clicked it becomes active immediately
        var selectedTab by remember { mutableStateOf(initialSelectedTab) }

        BSNavBar(
            // Add left padding so the brand isn't flush to the viewport edge. Tune 2.cssRem as needed.
            modifier = Modifier.fillMaxWidth().height(4.cssRem).padding(left = 2.cssRem),
            stickyTop = false,
            itemsAlignment = Alignment.CenterHorizontally,
            brand = NavBarBrand(
                title = "ABHISHEK'S PORTFOLIO",
                //image = "https://getbootstrap.com/docs/5.3/assets/brand/bootstrap-logo.svg",
                href = "#"
            ),
            expand = NavBarExpand.LG,
            backgroundStyle = BackgroundStyle.Dark,
            // It creates Navigation Tabs and handles selection redirections and states
            items = NavTabDataList(
                ctx,
                selectedTab,
                onSelect = { id ->
                    // Set the local selected tab immediately so the UI shows the item as active on click
                    selectedTab = id
                }
            )
        )

        // Render an anchor styled as a Bootstrap button and positioned to the right of the navbar.
        A(href = "/Resume/Abhishek_Verma.pdf", attrs = {
            // Use the actual resource filename that exists under resources/Resume
            attr("download", "Abhishek_Verma.pdf")
            // Use forceDownload helper to reliably fetch and save the file (avoids SPA interception)
            attr("onclick", "if (window.forceDownload) { window.forceDownload('/Resume/Abhishek_Verma.pdf','Abhishek_Verma.pdf'); } else { window.open('/Resume/Abhishek_Verma.pdf','_blank') }; return false;")
            attr("aria-label", "Download Abhishek Verma Resume")
            // Use Bootstrap button classes so the button is visible on the dark navbar
            attr("class", "btn btn-outline-light")
            // Position the anchor to the right/top of the navbar and ensure it's above the navbar
            attr("style", "position:absolute; right:12px; top:12px; z-index:2000;")
        }) {
            // Visible label so the anchor renders as a button with text
            SpanText(
                "Download Resume",
                modifier = Modifier.padding(6.px).styleModifier {
                    property("cursor", "pointer")
                }
            )
        }
    }
}
