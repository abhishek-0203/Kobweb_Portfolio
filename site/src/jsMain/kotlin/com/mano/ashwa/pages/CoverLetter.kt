package com.mano.ashwa.pages

import androidx.compose.runtime.Composable
import com.mano.ashwa.components.layouts.PageLayoutData
import com.mano.ashwa.navigation.CoverLetter_Route
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.text.SpanText

@InitRoute
fun initCoverLetterPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Abhishek Cover Letter"))
}

@Page(CoverLetter_Route)
@Layout(".components.layouts.PageLayout")
@Composable
fun CoverLetter() {
    // You can add content here for the My CoverLetter page
    SpanText("My CoverLetter Page")
}