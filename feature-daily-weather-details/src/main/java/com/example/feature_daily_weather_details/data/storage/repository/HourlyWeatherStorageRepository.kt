package com.example.feature_daily_weather_details.data.storage.repository

import com.example.core.data.storage.models.Dao
import com.example.core.data.storage.repository.StorageRepository
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HourlyWeatherStorageRepository @Inject constructor(
    @CoroutineContextIO coroutineContext: CoroutineContext,
    dao: Dao<HourlyWeather>
) : StorageRepository<HourlyWeather>(
    dao = dao,
    coroutineContext = coroutineContext
)