package com.example.feature_daily_weather_details.domain.usecases

import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject

internal interface FetchWeatherByDayUseCase {

    suspend fun execute(date: LocalDate): Result<List<WeatherForTimeOfDayDisplayableItem>>

    class Base @Inject constructor(
        private val repository: MainRepository
    ) : FetchWeatherByDayUseCase {
        override suspend fun execute(date: LocalDate): Result<List<WeatherForTimeOfDayDisplayableItem>> {

            val items = try {
                repository.fetchWeatherByDay(date = date)
            } catch (e: Exception) {
                return Result.failure(exception = e)
            }
            return Result.success(value = items)
        }
    }
}



