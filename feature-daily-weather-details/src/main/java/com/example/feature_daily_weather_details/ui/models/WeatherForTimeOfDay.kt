package com.example.feature_daily_weather_details.ui.models

import com.example.core.data.models.Temperature
import com.example.core.data.models.TemperatureRange
import com.example.core.data.models.TimeOfDay
import com.example.core.ui.DisplayableItem

internal data class WeatherForTimeOfDay(
    val timeOfDay: TimeOfDay,
    val relativeHumidity: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val temperature: TemperatureRange,
    val apparentTemperature: Temperature,
    val precipitation: Double,
    val imageResId: Int
) : DisplayableItem