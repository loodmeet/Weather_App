package com.example.feature_main_screen.data.models.mappers

import com.example.core.di.annotation.Base
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.data.network.models.WeatherResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal typealias ResponseToHourlyListMapper =
        Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<HourlyWeather>>

internal class WeatherResponseToHourlyWeatherListMapper @Inject constructor(
    @param: Base private val formatter: DateTimeFormatter
) : Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<HourlyWeather>> {

    override suspend fun map(from: WeatherResponse): List<HourlyWeather> = with(from.hourly) {
        return@with List(size = time.size) { index ->
            HourlyWeather(
                time = LocalDateTime.parse(time[index], formatter).toLocalTime(),
                temperature = temperature2m[index],
                weatherCode = weatherCode[index]
            )
        }
    }
}