package com.example.feature_main_screen.data.models

import java.util.*

internal data class DailyWeather(
    val date: Date,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val weatherCode: Int
)
