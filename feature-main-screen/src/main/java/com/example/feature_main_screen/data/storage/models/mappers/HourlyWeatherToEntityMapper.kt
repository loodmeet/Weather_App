package com.example.feature_main_screen.data.storage.models.mappers

import com.example.core.di.annotation.qualifiers.CoroutineContextDefault
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.data.storage.models.entities.MainScreenHourlyWeatherEntity
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal typealias HourlyToEntityMapper = Mapper<@JvmSuppressWildcards HourlyWeather, MainScreenHourlyWeatherEntity>

internal class HourlyWeatherToEntityMapper @Inject constructor(
    @param: CoroutineContextDefault private val coroutineContext: CoroutineContext
): Mapper<@JvmSuppressWildcards HourlyWeather, MainScreenHourlyWeatherEntity> {

    override suspend fun map(
        from: HourlyWeather
    ): MainScreenHourlyWeatherEntity = withContext(context = coroutineContext) {
        with(from) {
            return@withContext MainScreenHourlyWeatherEntity(
                id = 0,
                weatherCode = weatherCode,
                temperature = temperature,
                time = time
            )
        }
    }
}