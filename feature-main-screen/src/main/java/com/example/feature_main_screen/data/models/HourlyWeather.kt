package com.example.feature_main_screen.data.models

import java.time.LocalTime

internal data class HourlyWeather(
    val time: LocalTime,
    val weatherCode: Int,
    val temperature: Double
)