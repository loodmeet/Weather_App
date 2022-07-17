package com.example.feature_main_screen.data.models.mapper

import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.data.network.models.WeatherResponse
import javax.inject.Inject

internal class WeatherResponseToHourlyWeatherListMapper @Inject constructor() :
    Mapper<WeatherResponse, List<HourlyWeather>> {

    override suspend fun map(from: WeatherResponse): List<HourlyWeather> = with(from.hourly) {
        return@with List(size = time.size) { index ->
            HourlyWeather(
                time = time[index],
                temperature = temperature2m[index],
                weatherCode = weatherCode[index]
            )
        }
    }
}