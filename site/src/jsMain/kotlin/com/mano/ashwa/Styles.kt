package com.mano.ashwa

import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.cursor
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.StyleSheet
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.borderRadius
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.cursor
import org.jetbrains.compose.web.css.filter
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.fontWeight
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.transitions
import org.jetbrains.compose.web.css.value
import org.jetbrains.compose.web.css.variable

@OptIn(ExperimentalComposeWebApi::class)
object AppStyle : StyleSheet() {
    const val CONTENT_BACKGROUND_COLOR = "#212125"
    const val LINK_COLOR = "#75C9CE"
    const val LINK_HOVER_COLOR = "#95fbff"
    const val MONO_FONT_FAMILY = "JetBrains Mono"
    const val SPECIAL_TEXT_COLOR = "#B4BBFF"
    const val TITLE_BACKGROUND_COLOR = "#252525"
    const val TITLE_BORDER_COLOR = "#797979"

    val mobileFirstBreak = 890.px
    val mobileSecondBreak = 810.px
    val mobileThirdBreak = 510.px
    val mobileFourthBreak = 386.px

    val scrollbarColor = Color("#2D2D2D")
    val scrollbarThumbColor = Color("#555555")
    val scrollbarWidth = 13.px

    val buttonColor by variable<CSSColorValue>()

    val monoFont by style {
        fontFamily(MONO_FONT_FAMILY, "monospace")
    }

    val button by style {
        backgroundColor(buttonColor.value(Color("#252525")))
        borderRadius(.4.cssRem)
        color(Color.white)
        fontSize(1.1.cssRem)
        fontWeight(700)
        padding(.7.cssRem, 1.2.cssRem)

        cursor(Cursor.Pointer)

        filter {
            brightness(.8)
        }

        transitions {
            defaultDelay(.25.s)
            properties("filter")
        }

        group(hover(self), self + active) style {
            filter {
                brightness(1.0)
            }
        }
    }

    // White link style with hover color change to LINK_HOVER_COLOR
    val linkWhite by style {
        color(Color("#FFFFFF"))
        property("text-decoration", "none")
        transitions {
            properties("color")
            defaultDuration(.15.s)
        }
        hover(self) style {
            property("color", LINK_HOVER_COLOR)
        }
    }

}