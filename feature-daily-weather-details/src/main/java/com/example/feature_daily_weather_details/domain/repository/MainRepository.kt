package com.example.feature_daily_weather_details.domain.repository

import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import java.time.LocalDate

internal interface MainRepository {

    suspend fun fetchWeatherByDayNumber(dayNumber: Int): List<WeatherForTimeOfDayDisplayableItem>

    suspend fun fetchWeatherByDay(date: LocalDate): List<WeatherForTimeOfDayDisplayableItem>

    suspend fun fetchDateByDayNumber(dayNumber: Int): SelectedDateDisplayableItem
}
