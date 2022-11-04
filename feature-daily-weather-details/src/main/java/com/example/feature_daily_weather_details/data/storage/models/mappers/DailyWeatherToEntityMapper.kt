package com.example.feature_daily_weather_details.data.storage.models.mappers

import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyDetailsDailyWeatherEntity
import javax.inject.Inject

internal typealias  DailyToEntityMapper =
        Mapper<@JvmSuppressWildcards DailyWeather, DailyDetailsDailyWeatherEntity>

internal class DailyWeatherToEntityMapper @Inject constructor() :
    Mapper<@JvmSuppressWildcards DailyWeather, DailyDetailsDailyWeatherEntity> {

    override suspend fun map(from: DailyWeather): DailyDetailsDailyWeatherEntity =
        DailyDetailsDailyWeatherEntity(
            id = 0,
            date = from.date
        )
}