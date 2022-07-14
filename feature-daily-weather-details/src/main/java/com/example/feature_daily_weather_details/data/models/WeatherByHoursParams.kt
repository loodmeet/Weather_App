package com.example.feature_daily_weather_details.data.models

data class WeatherByHoursParams(
    val weatherCode: List<Int>,
    val apparentTemperature: List<Double>,
    val windDirection10m: List<Int>,
    val temperature2m: List<Double>,
    val precipitation: List<Double>,
    val windSpeed10m: List<Double>,
    val relativeHumidity2m: List<Int>,
    val cloudCover: List<Int>
)