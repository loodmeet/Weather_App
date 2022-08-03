package com.example.feature_daily_weather_details.data.storage.models.mappers

import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.storage.models.entities.HourlyWeatherEntity
import java.time.LocalDate
import javax.inject.Inject

internal typealias HourlyToEntityMapper =
        Mapper<@JvmSuppressWildcards Pair<HourlyWeather, LocalDate>, HourlyWeatherEntity>

internal class HourlyWeatherToEntityMapper  @Inject constructor() :
    Mapper<@JvmSuppressWildcards Pair<HourlyWeather, LocalDate>, HourlyWeatherEntity> {

    override suspend fun map(from: Pair<HourlyWeather, LocalDate>): HourlyWeatherEntity =
        with(from.first) {
            HourlyWeatherEntity(
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