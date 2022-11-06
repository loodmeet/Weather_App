package com.example.feature_daily_weather_details.domain.repository

import com.example.feature_daily_weather_details.data.models.HourlyWeather
import java.time.LocalDate

internal interface MainRepository {

    suspend fun fetchWeatherByDate(date: LocalDate): List<HourlyWeather>
}
