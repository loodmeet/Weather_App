package com.example.feature_daily_weather_details.data.storage.repository

import com.example.core.data.storage.exceptions.StorageException
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.data.storage.models.daos.FeatureDailyWeatherDao
import com.example.feature_daily_weather_details.data.storage.models.mappers.DailyToEntityMapper
import com.example.feature_daily_weather_details.data.storage.models.mappers.HourlyToEntityMapper
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

// todo: rewrite database logic (delete daily entity, add relationship between entities)
internal class StorageRepository @Inject constructor(
    private val dao: FeatureDailyWeatherDao,
    private val dailyToEntityMapper: DailyToEntityMapper,
    private val hourlyToEntityMapper: HourlyToEntityMapper,
    @CoroutineContextIO private val coroutineContext: CoroutineContext
) : BaseStorageRepository<WeatherResponse>() {

    suspend fun insertDailyWeather(dailyWeather: List<DailyWeather>) =
        withContext(coroutineContext) {
            dailyWeather.forEach {
                dao.dailyDetailsDailyWeatherDao().insertDailyWeather(
                    dailyWeather = dailyToEntityMapper.map(from = it)
                )
            }
        }

    suspend fun insertHourlyWeather(hourlyWeather: Map<HourlyWeather, LocalDate>) =
        withContext(coroutineContext) {
            hourlyWeather.keys.forEach {
                dao.dailyDetailsHourlyWeatherDao().insertHourlyWeather(
                    hourlyWeather = hourlyToEntityMapper.map(
                        from = it to (hourlyWeather[it] ?: throw StorageException(isLogged = true))
                    )
                )
            }
        }

    suspend fun getHourlyWeatherForDay(dayDate: LocalDate) = withContext(coroutineContext) {
        val entities = dao.dailyDetailsHourlyWeatherDao().getHourlyWeatherListByDay(dayDate = dayDate)

        return@withContext List(size = entities.size) { index ->
            entities[index].toHourlyWeather()
        }.also { if (it.isEmpty()) throw StorageException() }
    }

    suspend fun getAllHourlyWeather() = withContext(coroutineContext) {
        val entities = dao.dailyDetailsHourlyWeatherDao().getHourlyWeatherList()

        return@withContext List(size = entities.size) { index ->
            entities[index].toHourlyWeather()
        }.also { if (it.isEmpty()) throw StorageException() }
    }

    suspend fun getAllDailyList() = withContext(coroutineContext) {
        val entities = dao.dailyDetailsDailyWeatherDao().getAllDailyWeather()

        return@withContext List(size = entities.size) { index ->
            entities[index].toDailyWeather()
        }.also { if (it.isEmpty()) throw StorageException() }
    }
}