package com.example.feature_main_screen.data.models

import java.time.LocalDate

internal data class DailyWeather(
    val date: LocalDate,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val weatherCode: Int
)
