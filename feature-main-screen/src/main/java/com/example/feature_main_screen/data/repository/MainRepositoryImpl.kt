package com.example.feature_main_screen.data.repository

import android.util.Log
import com.example.core.data.network.exceptions.ResponseIsNotSuccessfulException
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.core.utils.Config
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.data.models.mappers.ResponseToDailyListMapper
import com.example.feature_main_screen.data.models.mappers.ResponseToHourlyListMapper
import com.example.feature_main_screen.data.network.retrofit.WeatherService
import com.example.feature_main_screen.data.storage.repository.StorageRepository
import com.example.feature_main_screen.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainRepositoryImpl @Inject constructor(
    private val responseToDailyListMapper: ResponseToDailyListMapper,
    private val responseToHourlyListMapper: ResponseToHourlyListMapper,
    private val storageRepository: StorageRepository,
    private val service: WeatherService,
    @CoroutineContextIO private val coroutineContext: CoroutineContext
) : MainRepository {

    override suspend fun fetchWeatherForWeek(): Pair<List<DailyWeather>, List<HourlyWeather>> =
        withContext(context = coroutineContext) {

            return@withContext try {
                val response = service.executeByDefaultRequest().also { retrofitResponse ->
                    if (!retrofitResponse.isSuccessful) throw ResponseIsNotSuccessfulException(
                        isLogged = true, message = retrofitResponse.message()
                    )
                }.body()!!

                Log.d(Config.NETWORK_TAG, "FMS: $response")

                val hourlyWeatherList = responseToHourlyListMapper.map(from = response)
                val dailyWeatherList = responseToDailyListMapper.map(from = response)

                storageRepository.apply {
                    insertHourlyWeather(hourlyWeatherList)
                    insertDailyWeather(dailyWeatherList)
                }

                dailyWeatherList to hourlyWeatherList
            } catch (e: Exception) {
                storageRepository.getDailyWeather() to storageRepository.getHourlyWeather()
            }
        }
}