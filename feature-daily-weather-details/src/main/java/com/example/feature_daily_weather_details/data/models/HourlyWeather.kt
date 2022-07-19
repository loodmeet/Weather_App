package com.example.feature_daily_weather_details.data.models

import java.time.LocalTime

internal data class HourlyWeather(
    val weatherCode: Int,
    val apparentTemperature: Double,
    val temperature: Double,
    val precipitation: Double,
    val windSpeed: Double,
    val windDirection: Int,
    val time: LocalTime,
    val relativeHumidity: Int
)