package com.example.feature_daily_weather_details.data.models.mappers

import com.example.core.di.annotation.Base
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal class WeatherResponseToHourlyWeatherListMapper @Inject constructor(
    @param: Base private val formatter: DateTimeFormatter
) : Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<HourlyWeather>> {

    override suspend fun map(from: WeatherResponse): List<HourlyWeather> = with(from.hourly) {
        return@with List(size = time.size) { index ->
            HourlyWeather(
                time = LocalDateTime.parse(time[index], formatter).toLocalTime(),
                temperature = temperature2m[index],
                weatherCode = weatherCode[index],
                apparentTemperature = apparentTemperature[index],
                precipitation = precipitation[index],
                windSpeed = windSpeed10m[index],
                relativeHumidity = relativeHumidity2m[index],
                windDirection = windDirection10m[index]
            )
        }
    }
}