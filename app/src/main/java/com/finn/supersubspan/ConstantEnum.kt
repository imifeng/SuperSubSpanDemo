package com.finn.supersubspan

import android.content.res.Resources

enum class SubSpanType {
    DEFAULT,
    TOP,
    CENTER
}


fun Int.toDp(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()