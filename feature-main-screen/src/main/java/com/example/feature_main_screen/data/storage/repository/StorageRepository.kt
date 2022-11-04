package com.example.feature_main_screen.data.storage.repository

import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.feature_main_screen.data.storage.models.daos.FeatureMainScreenDao
import com.example.feature_main_screen.data.storage.models.mappers.DailyToEntityMapper
import com.example.feature_main_screen.data.storage.models.mappers.HourlyToEntityMapper
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class StorageRepository @Inject constructor(
    @param: CoroutineContextIO private val coroutineContext: CoroutineContext,
    private val mainScreenDao: FeatureMainScreenDao,
    private val dailyMapper: DailyToEntityMapper,
    private val hourlyMapper: HourlyToEntityMapper
) : BaseStorageRepository<WeatherResponse>() {

    suspend fun insertDailyWeather(
        dailyWeather: List<DailyWeather>
    ) = withContext(context = coroutineContext) {
        dailyWeather.forEach { item ->
            mainScreenDao.mainScreenDailyWeatherDao()
                .insertDailyWeather(dailyWeather = dailyMapper.map(from = item))
        }
    }

    suspend fun insertHourlyWeather(
        hourlyWeather: List<HourlyWeather>
    ) = withContext(context = coroutineContext) {
        hourlyWeather.forEach { item ->
            mainScreenDao.mainScreenHourlyWeatherDao()
                .insertHourlyWeather(hourlyWeather = hourlyMapper.map(from = item))
        }
    }

    suspend fun getDailyWeather(): List<DailyWeather> = withContext(context = coroutineContext) {
        val entities = mainScreenDao.mainScreenDailyWeatherDao().getAllDailyWeather()
        return@withContext List(size = entities.size) { index -> entities[index].toDailyWeather() }
    }

    suspend fun getHourlyWeather(): List<HourlyWeather> = withContext(context = coroutineContext) {
        val entities = mainScreenDao.mainScreenHourlyWeatherDao().getHourlyWeatherList()
        return@withContext List(size = entities.size) { index -> entities[index].toHourlyWeather() }
    }
}