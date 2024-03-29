package com.example.feature_daily_weather_details.data.repository

import android.util.Log
import com.example.core.data.network.exceptions.ResponseIsNotSuccessfulException
import com.example.core.data.network.exceptions.ServerIsNotAvailableException
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.core.utils.Config
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToDailyListMapper
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToHourlyListMapper
import com.example.feature_daily_weather_details.data.network.retrofit.WeatherService
import com.example.feature_daily_weather_details.data.storage.repository.StorageRepository
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainRepositoryImpl @Inject constructor(
    @param: CoroutineContextIO private val coroutineContext: CoroutineContext,
    private val storageRepository: StorageRepository,
    private val responseToDailyListMapper: ResponseToDailyListMapper,
    private val responseToHourlyListMapper: ResponseToHourlyListMapper,
    private val service: WeatherService
) : MainRepository {

    override suspend fun fetchWeatherByDate(
        date: LocalDate
    ): List<HourlyWeather> = withContext(context = coroutineContext) {
        with(storageRepository) {

            val response = try {
                service.executeByDefaultRequest().also { retrofitResponse ->
                    if (!retrofitResponse.isSuccessful) throw ResponseIsNotSuccessfulException(
                        isLogged = true, message = retrofitResponse.message()
                    )
                }.body()!!.also {
                    Log.d(Config.NETWORK_TAG, "Feature Daily Weather Details: $it")
                }
            } catch (e: Exception) {
                val hourlyWeather = getHourlyWeatherForDay(dayDate = date)
                if (hourlyWeather.isEmpty()) throw StorageException(
                    isLogged = true, message = "Storage is empty"
                )

                return@withContext hourlyWeather
            }

            val hourlyWeatherList = responseToHourlyListMapper.map(from = response)
            val dailyWeatherList = responseToDailyListMapper.map(from = response)

            insertDailyWeather(dailyWeather = dailyWeatherList)
            insertHourlyWeather(hourlyWeather = hourlyWeatherList.associateBy({ hourly ->
                hourly
            }, { hourly ->
                val dailyIndex = (hourlyWeatherList.indexOf(hourly)) / 24
                dailyWeatherList[dailyIndex].date
            }))

            return@withContext hourlyWeatherList
        }
    }
}