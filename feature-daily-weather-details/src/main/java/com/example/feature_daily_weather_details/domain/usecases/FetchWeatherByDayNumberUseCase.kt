package com.example.feature_daily_weather_details.domain.usecases

import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import java.lang.Exception
import javax.inject.Inject

internal interface FetchWeatherByDayNumberUseCase {

    suspend fun execute(dayNumber: Int): Result<List<WeatherForTimeOfDayDisplayableItem>>


    class Base @Inject constructor(
        private val repository: MainRepository
    ) : FetchWeatherByDayNumberUseCase {
        override suspend fun execute(dayNumber: Int): Result<List<WeatherForTimeOfDayDisplayableItem>> {

            val items = try {
                repository.fetchWeatherByDayNumber(dayNumber = dayNumber)
            } catch (e: Exception) {
                return Result.failure(exception = e)
            }
            return Result.success(value = items)
        }
    }
}



