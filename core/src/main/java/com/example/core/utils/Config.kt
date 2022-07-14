package com.example.core.utils

import kotlin.math.pow
import kotlin.math.roundToInt

object Config {
    const val MAIN_TAG = "MainAct"
    const val NETWORK_TAG = "network_tag"
    const val DAY_NUMBER_KEY = "dayNumber"
}

fun Double.round(decimals: Int): Double {
    val multiplier = 10.0.pow(decimals)

    return (this * multiplier).roundToInt() / multiplier
}

