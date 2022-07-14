package com.example.feature_daily_weather_details.data.repository

import android.util.Log
import com.example.core.data.models.DateTimeProvider.TimeOfDay.*
import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.di.annotation.CoroutineContextIO
import com.example.core.utils.Config
import com.example.feature_daily_weather_details.data.models.mapper.WeatherResponseToSelectedDateMapper
import com.example.feature_daily_weather_details.data.models.mapper.WeatherResponseToWeatherForTimeOfDayMapper
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainRepositoryImpl @Inject constructor(
    private val networkRepository: BaseNetworkRepository<WeatherResponse>,
    private val storageRepository: BaseStorageRepository<WeatherResponse>,
    private val weatherResponseToWeatherForTimeOfDayMapper: WeatherResponseToWeatherForTimeOfDayMapper,
    @CoroutineContextIO private val coroutineContext: CoroutineContext,
    private val weatherResponseToSelectedDateMapper: WeatherResponseToSelectedDateMapper
) : MainRepository {

    init { Log.d(Config.MAIN_TAG, "repo in feature_daily_weather_details created") }

    lateinit var response: WeatherResponse

    override suspend fun fetchDateByDayNumber(dayNumber: Int): SelectedDateDisplayableItem =
        withContext(context = coroutineContext) {
            response = try {
                storageRepository.getData()
            } catch(e: StorageException) {
                storageRepository.updateData(data = networkRepository.fetchData())
                storageRepository.getData()
            }
            return@withContext weatherResponseToSelectedDateMapper.map(
                from = response,
                day = dayNumber
            )
        }

    override suspend fun fetchWeatherByDayNumber(
        dayNumber: Int
    ): List<WeatherForTimeOfDayDisplayableItem> = withContext(context = coroutineContext) {

        response = try {
            storageRepository.getData()
        } catch(e: StorageException) {
            storageRepository.updateData(data = networkRepository.fetchData())
            storageRepository.getData()
        }
        with(weatherResponseToWeatherForTimeOfDayMapper) {
            val weatherForDay = map(from = response, day = dayNumber, timeOfDay = DAY)
            val weatherForNight = map(from = response, day = dayNumber, timeOfDay = NIGHT)
            val weatherForEvening = map(from = response, day = dayNumber, timeOfDay = EVENING)
            val weatherForMorning = map(from = response, day = dayNumber, timeOfDay = MORNING)

            return@withContext listOf(
                weatherForMorning,
                weatherForDay,
                weatherForEvening,
                weatherForNight
            )
        }
    }
}
