package com.example.feature_main_screen.data.models.mapper

import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.network.models.WeatherResponse
import javax.inject.Inject

internal class WeatherResponseToDailyWeatherListMapper @Inject constructor() :
    Mapper<WeatherResponse, List<DailyWeather>> {

    override suspend fun map(from: WeatherResponse): List<DailyWeather> = with(from.daily) {
        return@with List(size = date.size) { index ->
            DailyWeather(
                date = date[index],
                temperatureMin = temperature2mMin[index],
                temperatureMax = temperature2mMax[index],
                weatherCode = weatherCode[index]
            )
        }

    }
}