package com.example.feature_daily_weather_details.domain.usecases

import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.DateTimeProvider.TimeOfDay.MORNING
import com.example.core.data.models.DateTimeProvider.TimeOfDay.DAY
import com.example.core.data.models.DateTimeProvider.TimeOfDay.NIGHT
import com.example.core.data.models.DateTimeProvider.TimeOfDay.EVENING
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.example.feature_daily_weather_details.domain.models.mappers.HourlyListToWeatherForTimeOfDayMapper
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject

internal interface FetchWeatherByDateUseCase {

    suspend fun execute(date: LocalDate): Result<List<WeatherForTimeOfDayDisplayableItem>>

    class Base @Inject constructor(
        private val dateTimeProvider: DateTimeProvider,
        private val hourlyListToWeatherForTimeOfDayMapper: HourlyListToWeatherForTimeOfDayMapper,
        private val repository: MainRepository
    ) : FetchWeatherByDateUseCase {
        override suspend fun execute(date: LocalDate): Result<List<WeatherForTimeOfDayDisplayableItem>> {

            val hourlyWeatherList = repository.fetchWeatherByDate(date = date)
            val items = try {
                with(dateTimeProvider) {
                    val morningHours = hourlyWeatherList.subList(
                        fromIndex = hourRangeByTimeOfDay(MORNING).first,
                        toIndex = hourRangeByTimeOfDay(MORNING).last
                    )
                    val dayHours = hourlyWeatherList.subList(
                        fromIndex = hourRangeByTimeOfDay(DAY).first,
                        toIndex = hourRangeByTimeOfDay(DAY).last
                    )
                    val eveningHours = hourlyWeatherList.subList(
                        fromIndex = hourRangeByTimeOfDay(EVENING).first,
                        toIndex = hourRangeByTimeOfDay(EVENING).last
                    )
                    val nightHours = hourlyWeatherList.subList(
                        fromIndex = hourRangeByTimeOfDay(NIGHT).first,
                        toIndex = hourRangeByTimeOfDay(NIGHT).last
                    )

                    with(hourlyListToWeatherForTimeOfDayMapper) {
                        val weatherForDay = map(from = dayHours)
                        val weatherForNight = map(from = nightHours)
                        val weatherForEvening = map(from = eveningHours)
                        val weatherForMorning = map(from = morningHours)

                        listOf(
                            weatherForMorning,
                            weatherForDay,
                            weatherForEvening,
                            weatherForNight
                        )
                    }
                }
            } catch (e: Exception) {
                return Result.failure(exception = e)
            }
            return Result.success(value = items)
        }
    }
}



