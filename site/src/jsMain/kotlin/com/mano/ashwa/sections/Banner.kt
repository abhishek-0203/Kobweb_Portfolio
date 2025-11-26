package com.mano.ashwa.sections

import androidx.compose.runtime.Composable
import com.mano.ashwa.components.rememberAnimatedText
import com.mano.ashwa.styles.GradientTagLineStyle
import com.mano.ashwa.styles.bannerStyle
import com.mano.ashwa.styles.buttonStyle
import com.mano.ashwa.styles.upDownAnim
import com.mano.ashwa.styles.zoomIn
import com.mano.ashwa.utils.Assets
import com.mano.ashwa.utils.atBreakpointMd
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaCircleArrowRight
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.animation.toAnimation
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.P

@Composable
fun Banner() {
    val animatedText = rememberAnimatedText(
        toRotate = listOf("Full Stack Developer"),
        period = 2000
    )
    SimpleGrid(
        numColumns(base = 1, md = 2),
        modifier = bannerStyle.toModifier().id("home").styleModifier {
            // Apply background specifically for the Banner on the home page
            property("background-image", "url(${Assets.BannerBg})")
            property("background-position", "center")
            property("background-size", "cover")
            property("background-repeat", "no-repeat")
        }
    ) {
        BannerText(animatedText.value)
        Image(
            src = Assets.HeaderImg,
            modifier = Modifier
                .width(100.percent)
                .padding(0.px atBreakpointMd 50.px)
                .height(auto)
                .animation(
                    zoomIn.toAnimation(
                        duration = 1.s,
                        timingFunction = AnimationTimingFunction.Ease,
                        iterationCount = AnimationIterationCount.of(1)
                    ),
                    upDownAnim.toAnimation(
                        duration = 2.s,
                        direction = AnimationDirection.Alternate,
                        iterationCount = AnimationIterationCount.Infinite
                    )
                )
        )
    }
}

@Composable
fun BannerText(text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.px, 50.px)
    ) {
        SpanText(
            text = "Welcome to my Portfolio",
            modifier = GradientTagLineStyle.toModifier()
        )
        H1(
            attrs = Modifier.fontSize(65.px atBreakpointMd 45.px)
                .fontWeight(700)
                .letterSpacing(0.80.px)
                .lineHeight(1)
                .margin(bottom = 20.px)
                .display(DisplayStyle.Block)
                .toAttrs()
        ) {
            SpanText(
                text = "Hi! I'm Abhishek $text",
                modifier = Modifier.borderRight(
                    width = 0.08.em,
                    style = LineStyle.Solid,
                    color = Color("#666")
                )
            )
        }
        P(
            attrs = Modifier.color(Color.white)
                .fontSize(18.px)
                .letterSpacing(0.8.px)
                .lineHeight(1.5.em)
                .width(100.percent)
                .toAttrs()
        ) {
            // SpanText("Constants.LOREM")
        }

        // More attractive bio block: a short gradient-highlighted headline followed by a readable paragraph
        Row(
            modifier = Modifier
                .padding(top = 60.px)
                .fontSize(24.px atBreakpointMd 20.px)
                .lineHeight(1.6.em)
        ) {
            Column(Modifier.fillMaxWidth()) {
                // Headline with gradient text
                SpanText(
                    text = "A motivated and quick-learning developer passionate about the intersection of AI and software development, continuously learning Kotlin, GoLang and machine learning to create smart and impactful solutions.",
                    modifier = Modifier
                        .fontSize(26.px atBreakpointMd 22.px)
                        .fontWeight(600)
                        .styleModifier {
                            property("background", "linear-gradient(90deg, #60A5FA, #7C3AED)")
                            property("-webkit-background-clip", "text")
                            property("-webkit-text-fill-color", "transparent")
                            property("text-shadow", "0 6px 18px rgba(124,58,237,0.08)")
                            property("display", "block")
                        }
                )


            }
        }
    }
 }
