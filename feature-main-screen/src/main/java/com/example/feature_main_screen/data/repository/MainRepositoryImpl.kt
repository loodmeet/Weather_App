package com.example.feature_main_screen.data.repository

import com.example.core.data.network.exceptions.ResponseIsNotSuccessfulException
import com.example.core.data.network.exceptions.ServerIsNotAvailableException
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.data.models.mappers.ResponseToDailyListMapper
import com.example.feature_main_screen.data.models.mappers.ResponseToHourlyListMapper
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.feature_main_screen.data.network.retrofit.WeatherService
import com.example.feature_main_screen.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainRepositoryImpl @Inject constructor(
    private val responseToDailyListMapper: ResponseToDailyListMapper,
    private val responseToHourlyListMapper: ResponseToHourlyListMapper,
    private val storageRepository: BaseStorageRepository<WeatherResponse>,
    private val service: WeatherService,
    @CoroutineContextIO private val coroutineContext: CoroutineContext // todo: delete
) : MainRepository {

    // todo: REWRITE
    override suspend fun fetchWeatherForWeek(): Pair<List<DailyWeather>, List<HourlyWeather>> =
        withContext(context = coroutineContext) {

            val response = try {
                storageRepository.getData()
            } catch (e: StorageException) {
                storageRepository.updateData(
                    data = try {
                        service.executeByDefaultRequest().also { retrofitResponse ->
                            if (!retrofitResponse.isSuccessful) throw ResponseIsNotSuccessfulException(
                                isLogged = true, message = retrofitResponse.message()
                            )
                        }.body()!!
                    } catch (e: Exception) {
                        throw ServerIsNotAvailableException(
                            isLogged = true, message = e.stackTraceToString()
                        )
                    }
                )
                storageRepository.getData()
            }

            val hourlyWeatherList = responseToHourlyListMapper.map(from = response)
            val dailyWeatherList = responseToDailyListMapper.map(from = response)

            return@withContext dailyWeatherList to hourlyWeatherList
        }
}