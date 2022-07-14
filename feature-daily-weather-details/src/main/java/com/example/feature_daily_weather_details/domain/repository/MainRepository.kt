package com.example.feature_daily_weather_details.domain.repository

import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem

internal interface MainRepository {

    // todo: rename
    suspend fun fetchWeatherByDayNumber(dayNumber: Int): List<WeatherForTimeOfDayDisplayableItem>

    suspend fun fetchDateByDayNumber(dayNumber: Int): SelectedDateDisplayableItem
}
