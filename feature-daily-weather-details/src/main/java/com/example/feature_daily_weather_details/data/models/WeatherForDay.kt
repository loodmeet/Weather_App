package com.example.feature_daily_weather_details.data.models

import com.example.core.ui.DisplayableItem
import java.util.*

data class WeatherForDay(
    val date: Date,
    val windSpeed10mMax: Double,
    val temperature2mMax: Double,
    val weatherCode: Int,
    val sunset: Date,
    val temperature2mMin: Double,
    val sunrise: Date,
    val precipitationSum: Double,
    val weatherByHours: WeatherByHours
) : DisplayableItem





