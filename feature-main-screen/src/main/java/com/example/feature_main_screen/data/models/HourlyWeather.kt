package com.example.feature_main_screen.data.models

import java.util.*

internal data class HourlyWeather(
    val time: Date,
    val weatherCode: Int,
    val temperature: Double
)