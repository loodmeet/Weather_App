package com.example.feature_daily_weather_details.data.storage.models.mappers

import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyWeatherEntity
import javax.inject.Inject

internal typealias  DailyToEntityMapper =
        Mapper<@JvmSuppressWildcards DailyWeather, DailyWeatherEntity>

internal class DailyWeatherToEntityMapper @Inject constructor() :
    Mapper<@JvmSuppressWildcards DailyWeather, DailyWeatherEntity> {

    override suspend fun map(from: DailyWeather): DailyWeatherEntity =
        DailyWeatherEntity(
            id = 0,
            date = from.date
        )
}