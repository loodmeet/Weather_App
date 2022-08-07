package com.example.feature_daily_weather_details.data.repository

import android.util.Log
import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.di.annotation.CoroutineContextIO
import com.example.core.utils.Config
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToDailyListMapper
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToHourlyListMapper
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.data.storage.repository.StorageRepository
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainRepositoryImpl @Inject constructor(
    @param: CoroutineContextIO private val coroutineContext: CoroutineContext,
    private val networkRepository: BaseNetworkRepository<WeatherResponse>,
    private val storageRepository: StorageRepository,
    private val responseToDailyListMapper: ResponseToDailyListMapper,
    private val responseToHourlyListMapper: ResponseToHourlyListMapper,
) : MainRepository {

    private lateinit var hourlyWeather: List<HourlyWeather>

    override suspend fun fetchWeatherByDate(date: LocalDate): List<HourlyWeather> =
        withContext(context = coroutineContext) {

            with(storageRepository) {
                try {
                    hourlyWeather = getHourlyWeatherForDay(dayDate = date)
                } catch (e: StorageException) {
                    val response = networkRepository.fetchData()

                    val hourlyWeatherList = responseToHourlyListMapper.map(from = response)
                    val dailyWeatherList = responseToDailyListMapper.map(from = response)

                    insertDailyWeather(dailyWeather = dailyWeatherList)
                    // todo: rewrite
                    insertHourlyWeather(hourlyWeather = hourlyWeatherList.associateBy({ hourly->
                        hourly
                    }, { hourly->
                        val dailyIndex = (hourlyWeatherList.indexOf(hourly)) / 24
                        dailyWeatherList[dailyIndex].date
                    }))
                    hourlyWeather = getHourlyWeatherForDay(dayDate = date)
                }
            }

            return@withContext hourlyWeather
        }
}