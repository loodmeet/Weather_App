package com.example.feature_daily_weather_details.domain.models

import com.example.feature_daily_weather_details.data.models.HourlyWeather
import java.time.LocalTime

internal data class DomainModel(
    val selectedTime: LocalTime,
    val hourlyWeather: List<HourlyWeather>
)