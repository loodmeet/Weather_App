package com.example.feature_main_screen.data.models.mapper

import com.example.core.data.models.mappers.WeatherCodeToTranslatedWeatherMapper
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.feature_main_screen.domain.models.HeaderDisplayableItem
import javax.inject.Inject

internal interface WeatherResponseToHeaderMapper :
    Mapper<WeatherResponse, HeaderDisplayableItem> {

    suspend fun map(from: WeatherResponse, day: Int, hour: Int) = map(from = from)

    class Base @Inject constructor(
        private val weatherCodeToTranslatedWeatherMapper: WeatherCodeToTranslatedWeatherMapper,
        private val dailyWeatherMapper: WeatherResponseToDailyWeatherMapper,
        private val hourlyWeatherMapper: WeatherResponseToHourlyWeatherMapper
    ) : WeatherResponseToHeaderMapper {

        private var dayNumber = 0
        private var hourNumber = 0

        override suspend fun map(from: WeatherResponse, day: Int, hour: Int) : HeaderDisplayableItem {
            dayNumber = day
            hourNumber = hour
            return super.map(from = from, day = day, hour = hour)
        }

        override suspend fun map(from: WeatherResponse): HeaderDisplayableItem = with(from) {
            val dailyWeatherDisplayableItem = dailyWeatherMapper.map(from = from, day = dayNumber)
            val hourlyWeatherDisplayableItem = hourlyWeatherMapper.map(from = from, hour = hourNumber)

            return HeaderDisplayableItem(
                currentTemperature = hourlyWeatherDisplayableItem.temperature,
                dailyTemperature = dailyWeatherDisplayableItem.temperature,
                weatherDescriptionResId = weatherCodeToTranslatedWeatherMapper
                    .map(dailyWeatherDisplayableItem.weatherCode)
                    .stringResId,
                imageResId = dailyWeatherDisplayableItem.imageResId
            )
        }
    }
}
