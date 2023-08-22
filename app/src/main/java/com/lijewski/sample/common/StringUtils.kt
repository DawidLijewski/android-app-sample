package com.lijewski.sample.common

import android.util.Patterns

const val PASSWORD_MIN_LENGTH = 6

fun String?.isValidEmail() =
    !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
