package com.example.feature_main_screen.data.storage.models.mappers

import com.example.core.di.annotation.qualifiers.CoroutineContextDefault
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.storage.models.entities.MainScreenDailyWeatherEntity
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal typealias DailyToEntityMapper = Mapper<@JvmSuppressWildcards DailyWeather, MainScreenDailyWeatherEntity>

internal class DailyWeatherToEntityMapper @Inject constructor(
    @param: CoroutineContextDefault private val coroutineContext: CoroutineContext
): Mapper<@JvmSuppressWildcards DailyWeather, MainScreenDailyWeatherEntity> {

    override suspend fun map(
        from: DailyWeather
    ): MainScreenDailyWeatherEntity = withContext(context = coroutineContext) {
        with(from) {
            return@withContext MainScreenDailyWeatherEntity(
                id = 0,
                date = date,
                temperatureMin = temperatureMin,
                temperatureMax = temperatureMax,
                weatherCode = weatherCode
            )
        }
    }
}