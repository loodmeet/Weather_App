package com.example.feature_main_screen.domain.repository

import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.models.HourlyWeather


internal interface MainRepository {

    suspend fun fetchWeatherForWeek(): Pair<List<DailyWeather>, List<HourlyWeather>>

}
