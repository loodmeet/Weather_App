package com.example.feature_main_screen.data.models.mapper

import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.Temperature
import com.example.core.data.models.mappers.TranslatedWeatherToResourceMapper
import com.example.core.data.models.mappers.WeatherCodeToTranslatedWeatherMapper
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.core.di.annotation.HourlyWeatherDateFormat
import com.example.core.utils.Mapper
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import java.text.DateFormat
import javax.inject.Inject

internal interface WeatherResponseToHourlyWeatherMapper :
    Mapper<WeatherResponse, HourlyWeatherDisplayableItem> {

    suspend fun map(from: WeatherResponse, hour: Int) = map(from = from)

    class Base @Inject constructor(
        @param: HourlyWeatherDateFormat private val hourlyWeatherDateFormat: DateFormat,
        private val weatherCodeToTranslatedWeatherMapper: WeatherCodeToTranslatedWeatherMapper,
        private val translatedWeatherToResourceMapper: TranslatedWeatherToResourceMapper,
        private val dateTimeProvider: DateTimeProvider
    ) : WeatherResponseToHourlyWeatherMapper {
        private var hourNumber = 0

        override suspend fun map(from: WeatherResponse, hour: Int): HourlyWeatherDisplayableItem {
            hourNumber = hour
            return super.map(from, hour)
        }

        override suspend fun map(from: WeatherResponse): HourlyWeatherDisplayableItem {

            val timeOfDay = dateTimeProvider.timeOfDayByHour(hour = hourNumber)

            with(from.hourly) {
                return HourlyWeatherDisplayableItem(
                    weatherCode = weatherCode[hourNumber],
                    time = hourlyWeatherDateFormat.format(time[hourNumber]),
                    temperature = Temperature(value = temperature2m[hourNumber]),
                    imageResId = translatedWeatherToResourceMapper
                        .map(from = weatherCodeToTranslatedWeatherMapper
                                .map(from = weatherCode[hourNumber]),
                            timeOfDay = timeOfDay
                        )
                )
            }
        }

    }
}

