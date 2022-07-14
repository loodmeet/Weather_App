package com.example.feature_daily_weather_details.data.models

data class WeatherForHour(
    val weatherCode: Int,
    val apparentTemperature: Double,
    val windDirection10m: Int,
    val temperature2m: Double,
    val precipitation: Double,
    val windSpeed10m: Double,
    val relativeHumidity2m: Int,
    val cloudCover: Int
)