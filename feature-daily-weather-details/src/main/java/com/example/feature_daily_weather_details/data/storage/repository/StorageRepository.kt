package com.example.feature_daily_weather_details.data.storage.repository

import com.example.core.data.storage.exceptions.StorageException
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.di.annotation.CoroutineContextIO
import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.data.storage.database.LocalDatabase
import com.example.feature_daily_weather_details.data.storage.models.mappers.DailyToEntityMapper
import com.example.feature_daily_weather_details.data.storage.models.mappers.HourlyToEntityMapper
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class StorageRepository @Inject constructor(
    private val database: LocalDatabase,
    private val dailyToEntityMapper: DailyToEntityMapper,
    private val hourlyToEntityMapper: HourlyToEntityMapper,
    @CoroutineContextIO private val coroutineContext: CoroutineContext
) : BaseStorageRepository<WeatherResponse>() {

    suspend fun insertDailyWeather(dailyWeather: List<DailyWeather>) =
        withContext(coroutineContext) {
            dailyWeather.forEach {
                database.dailyWeatherDao().insertDailyWeather(
                    dailyWeather = dailyToEntityMapper.map(from = it)
                )
            }
        }

    suspend fun insertHourlyWeather(hourlyWeather: Map<HourlyWeather, LocalDate>) =
        withContext(coroutineContext) {
            hourlyWeather.keys.forEach {
                database.hourlyWeatherDao().insertHourlyWeather(
                    hourlyWeather = hourlyToEntityMapper.map(
                        from = it to (hourlyWeather[it] ?: throw StorageException())
                    )
                )
            }
        }

    suspend fun getHourlyWeatherForDay(dayDate: LocalDate) = withContext(coroutineContext) {
        val entities = database.hourlyWeatherDao().getHourlyWeatherListByDay(dayDate = dayDate)

        return@withContext List(size = entities.size) { index ->
            entities[index].toHourlyWeather()
        }.also { if (it.isEmpty()) throw StorageException() }
    }

    suspend fun getAllHourlyWeather() = withContext(coroutineContext) {
        val entities = database.hourlyWeatherDao().getHourlyWeatherList()

        return@withContext List(size = entities.size) { index ->
            entities[index].toHourlyWeather()
        }.also { if (it.isEmpty()) throw StorageException() }
    }

    suspend fun getAllDailyList() = withContext(coroutineContext) {
        val entities = database.dailyWeatherDao().getAllDailyWeather()

        return@withContext List(size = entities.size) { index ->
            entities[index].toDailyWeather()
        }.also { if (it.isEmpty()) throw StorageException() }
    }
}