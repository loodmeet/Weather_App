package com.example.feature_daily_weather_details.data.models.mappers

import com.example.core.di.annotation.DailyBase
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal class WeatherResponseToDailyWeatherListMapper @Inject constructor(
    @param: DailyBase private val formatter: DateTimeFormatter
) : Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<DailyWeather>> {

    override suspend fun map(from: WeatherResponse): List<DailyWeather> = with(from.daily) {
        return@with List(size = date.size) { index ->
            DailyWeather(
                date = LocalDate.parse(date[index], formatter)
            )
        }
    }
}