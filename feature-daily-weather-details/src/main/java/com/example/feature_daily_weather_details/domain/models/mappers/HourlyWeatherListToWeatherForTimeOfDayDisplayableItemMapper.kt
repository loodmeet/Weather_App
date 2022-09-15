package com.example.feature_daily_weather_details.domain.models.mappers

import com.example.core.data.models.TimeOfDay
import com.example.core.data.models.Temperature
import com.example.core.data.models.TemperatureRange
import com.example.core.data.models.WhatTimeOfDay
import com.example.core.data.models.mappers.CodeToTranslatedWeatherMapper
import com.example.core.data.models.mappers.TranslatedWeatherToResMapper
import com.example.core.utils.Mapper
import com.example.core.utils.round
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import java.lang.IllegalArgumentException
import javax.inject.Inject

internal typealias HourlyListToWeatherForTimeOfDayMapper =
        Mapper<@JvmSuppressWildcards List<HourlyWeather>, WeatherForTimeOfDayDisplayableItem>

internal class HourlyWeatherListToWeatherForTimeOfDayDisplayableItemMapper @Inject constructor(
    private val whatTimeOfDay: WhatTimeOfDay,
    private val translatedWeatherToResourceMapper: TranslatedWeatherToResMapper,
    private val codeToTranslatedWeatherMapper: CodeToTranslatedWeatherMapper
) : Mapper<@JvmSuppressWildcards List<HourlyWeather>, WeatherForTimeOfDayDisplayableItem> {

    private var timeOfDay = TimeOfDay.DAY

    private suspend fun determineTimeOfDay(from: List<HourlyWeather>) {
        timeOfDay = whatTimeOfDay.timeOfDayByHour(hour = from[0].time.hour)
        for (hourlyWeather in from) {
            if (whatTimeOfDay.timeOfDayByHour(hour = hourlyWeather.time.hour) != timeOfDay)
                throw IllegalArgumentException("there are some problems with time of day")
        }
    }

    override suspend fun map(from: List<HourlyWeather>): WeatherForTimeOfDayDisplayableItem {
        determineTimeOfDay(from = from)
        val weatherCodeList = List(size = from.size) { index -> from[index].weatherCode }.sorted()
        val weatherCode = weatherCodeList[weatherCodeList.size / 2]
        val imageResId = translatedWeatherToResourceMapper.map(
            from = codeToTranslatedWeatherMapper.map(weatherCode) to timeOfDay,
        )
        val temperatureList = List(size = from.size) { index -> from[index].temperature }
        val temperatureRange = TemperatureRange(
            firstValue = Temperature(value = temperatureList.min()),
            secondValue = Temperature(value = temperatureList.max())
        )
        val windSpeed = List(size = from.size) { index ->
            from[index].windSpeed
        }.average().round(decimals = 1)
        val windDirection = List(size = from.size) { index ->
            from[index].windDirection
        }.average().toInt()
        val relativeHumidity = List(size = from.size) { index ->
            from[index].relativeHumidity
        }.average().toInt()
        val apparentTemperature = Temperature(
            value = List(size = from.size) { index ->
                from[index].apparentTemperature
            }.average().round(decimals = 1)
        )
        val precipitation = List(size = from.size) { index ->
            from[index].precipitation
        }.average().round(decimals = 1)

        return WeatherForTimeOfDayDisplayableItem(
            timeOfDay = timeOfDay,
            relativeHumidity = relativeHumidity,
            windDirection = windDirection,
            windSpeed = windSpeed,
            temperature = temperatureRange,
            apparentTemperature = apparentTemperature,
            imageResId = imageResId,
            precipitation = precipitation,
        )
    }
}
