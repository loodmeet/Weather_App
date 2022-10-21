package com.example.feature_daily_weather_details.domain.usecases

import com.example.core.data.models.TimeOfDay.MORNING
import com.example.core.data.models.TimeOfDay.DAY
import com.example.core.data.models.TimeOfDay.NIGHT
import com.example.core.data.models.TimeOfDay.EVENING
import com.example.core.data.models.WhatTimeOfDay
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.di.annotation.qualifiers.CoroutineContextDefault
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.example.feature_daily_weather_details.domain.models.mappers.HourlyListToWeatherForTimeOfDayMapper
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.time.LocalDate
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class FetchWeatherByDateUseCase @Inject constructor(
    private val dateTimeProvider: WhatTimeOfDay,
    private val mapper: HourlyListToWeatherForTimeOfDayMapper,
    private val repository: MainRepository,
    @param: CoroutineContextDefault private val coroutineContext: CoroutineContext
) {
    suspend fun execute(date: LocalDate): Result<List<WeatherForTimeOfDayDisplayableItem>> =
        withContext(context = coroutineContext) {

            val hourlyWeatherList = try {
                repository.fetchWeatherByDate(date = date)
            } catch (e: StorageException) {
                return@withContext Result.failure(e)
            }

            Result.success(value = with(dateTimeProvider) {
                val weatherForDay = mapper.map(
                    from = hourlyWeatherList.subList(
                        fromIndex = hourRangeByTimeOfDay(DAY).first,
                        toIndex = hourRangeByTimeOfDay(DAY).last
                    )
                )
                val weatherForNight = mapper.map(
                    from = hourlyWeatherList.subList(
                        fromIndex = hourRangeByTimeOfDay(NIGHT).first,
                        toIndex = hourRangeByTimeOfDay(NIGHT).last
                    )
                )
                val weatherForEvening = mapper.map(
                    from = hourlyWeatherList.subList(
                        fromIndex = hourRangeByTimeOfDay(EVENING).first,
                        toIndex = hourRangeByTimeOfDay(EVENING).last
                    )
                )
                val weatherForMorning = mapper.map(
                    from = hourlyWeatherList.subList(
                        fromIndex = hourRangeByTimeOfDay(MORNING).first,
                        toIndex = hourRangeByTimeOfDay(MORNING).last
                    )
                )

                listOf(
                    weatherForMorning,
                    weatherForDay,
                    weatherForEvening,
                    weatherForNight
                )
            })
        }
}
