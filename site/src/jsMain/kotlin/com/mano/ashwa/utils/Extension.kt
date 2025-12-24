@file:Suppress("unused", "NOTHING_TO_INLINE") // These are utility functions for responsive design

package com.mano.ashwa.utils

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint

/**
 * Selects a value based on the current screen [Breakpoint].
 *
 * @param primary The value returned when the breakpoint condition is met.
 * @param fallback The value returned when the breakpoint condition is not met.
 * @param condition A predicate that determines which value to return based on the current breakpoint.
 * @return [primary] if [condition] is true, otherwise [fallback].
 */
@Suppress("OPT_IN_USAGE")
@Composable
private inline fun <T> selectByBreakpoint(
    primary: T,
    fallback: T,
    condition: (Breakpoint) -> Boolean
): T {
    val currentBreakpoint = rememberBreakpoint()
    return if (condition(currentBreakpoint)) primary else fallback
}

/**
 * Returns [this] value for screens at [Breakpoint.SM] or larger, otherwise returns [fallback].
 *
 * Example: `24.px atBreakpointSm 16.px` returns 24px on SM+ screens, 16px on smaller.
 */
@Composable
infix fun <T> T.atBreakpointSm(fallback: T): T =
    selectByBreakpoint(this, fallback) { it >= Breakpoint.SM }

/**
 * Returns [this] value for screens at [Breakpoint.MD] or larger, otherwise returns [fallback].
 *
 * Example: `24.px atBreakpointMd 16.px` returns 24px on MD+ screens, 16px on smaller.
 */
@Composable
infix fun <T> T.atBreakpointMd(fallback: T): T =
    selectByBreakpoint(this, fallback) { it >= Breakpoint.MD }

/**
 * Returns [this] value for screens at [Breakpoint.LG] or larger, otherwise returns [fallback].
 *
 * Example: `4 atBreakpointLg 2` returns 4 on LG+ screens, 2 on smaller.
 */
@Composable
infix fun <T> T.atBreakpointLg(fallback: T): T =
    selectByBreakpoint(this, fallback) { it >= Breakpoint.LG }

/**
 * Returns [this] value for screens at [Breakpoint.XL] or larger, otherwise returns [fallback].
 *
 * Example: `1200.px atBreakpointXl 100.percent` returns fixed width on XL+, full width on smaller.
 */
@Composable
infix fun <T> T.atBreakpointXl(fallback: T): T =
    selectByBreakpoint(this, fallback) { it >= Breakpoint.XL }
