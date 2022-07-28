package com.example.feature_daily_weather_details.data.repository

import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.di.annotation.CoroutineContextIO
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToDailyListMapper
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToHourlyListMapper
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainRepositoryImpl @Inject constructor(
    @param: CoroutineContextIO private val coroutineContext: CoroutineContext,

    private val networkRepository: BaseNetworkRepository<WeatherResponse>,
    private val storageRepository: BaseStorageRepository<WeatherResponse>,
    private val responseToDailyListMapper: ResponseToDailyListMapper,
    private val responseToHourlyListMapper: ResponseToHourlyListMapper,
) : MainRepository {
//
//    init {
//        Log.d(Config.MAIN_TAG, "repo in feature_daily_weather_details created")
//    }
    lateinit var response: WeatherResponse

    override suspend fun fetchWeatherByDate(date: LocalDate): List<HourlyWeather> =
        withContext(context = coroutineContext) {
            response = try {
                storageRepository.getData()
            } catch (e: StorageException) {
                storageRepository.updateData(data = networkRepository.fetchData())
                storageRepository.getData()
            }

            val dayNumber = responseToDailyListMapper.map(from = response)
                .indexOfFirst { dailyWeather -> dailyWeather.date == date }
            response = try {
                storageRepository.getData()
            } catch (e: StorageException) {
                storageRepository.updateData(data = networkRepository.fetchData())
                storageRepository.getData()
            }

            return@withContext responseToHourlyListMapper.map(from = response)
                .subList(fromIndex = dayNumber * 24 + 0, toIndex = dayNumber * 24 + 24)

        }
}
