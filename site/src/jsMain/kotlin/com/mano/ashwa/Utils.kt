package com.mano.ashwa

/**
 * Executes [block] if this CharSequence is not blank, returning the result.
 * Returns an empty string if this CharSequence is blank.
 */
inline fun <C : CharSequence, R : C> C.ifNotBlank(block: (C) -> R): CharSequence =
    if (isNotBlank()) block(this) else ""

/** Contact email for the portfolio owner. */
const val MAIL_TO = "v.abhishek0203@gmail.com"
