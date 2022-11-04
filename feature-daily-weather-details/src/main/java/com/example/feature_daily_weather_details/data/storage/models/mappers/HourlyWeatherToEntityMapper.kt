package com.example.feature_daily_weather_details.data.storage.models.mappers

import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyDetailsHourlyWeatherEntity
import java.time.LocalDate
import javax.inject.Inject

internal typealias HourlyToEntityMapper =
        Mapper<@JvmSuppressWildcards Pair<HourlyWeather, LocalDate>, DailyDetailsHourlyWeatherEntity>

internal class HourlyWeatherToEntityMapper  @Inject constructor() :
    Mapper<@JvmSuppressWildcards Pair<HourlyWeather, LocalDate>, DailyDetailsHourlyWeatherEntity> {

    override suspend fun map(from: Pair<HourlyWeather, LocalDate>): DailyDetailsHourlyWeatherEntity =
        with(from.first) {
            DailyDetailsHourlyWeatherEntity(
                id = 0,
                dayDate = from.second,
                weatherCode = weatherCode,
                apparentTemperature = apparentTemperature,
                temperature = temperature,
                precipitation = precipitation,
                windSpeed = windSpeed,
                windDirection = windDirection,
                time = time,
                relativeHumidity = relativeHumidity
            )
        }
}