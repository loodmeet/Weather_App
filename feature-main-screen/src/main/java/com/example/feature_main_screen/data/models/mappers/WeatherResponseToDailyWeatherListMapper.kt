package com.example.feature_main_screen.data.models.mappers

import com.example.core.di.annotation.DailyBase
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.network.models.WeatherResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal typealias ResponseToDailyListMapper =
        Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<DailyWeather>>

internal class WeatherResponseToDailyWeatherListMapper @Inject constructor(
    @param: DailyBase private val formatter: DateTimeFormatter
) : Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<DailyWeather>> {

    override suspend fun map(from: WeatherResponse): List<DailyWeather> = with(from.daily) {
        return@with List(size = date.size) { index ->
            DailyWeather(
                date = LocalDate.parse(date[index], formatter),
                temperatureMin = temperature2mMin[index],
                temperatureMax = temperature2mMax[index],
                weatherCode = weatherCode[index]
            )
        }

    }
}