package com.example.feature_main_screen.domain.models.mappers

import com.example.core.data.models.Temperature
import com.example.core.data.models.WhatTimeOfDay
import com.example.core.data.models.mappers.CodeToTranslatedWeatherMapper
import com.example.core.data.models.mappers.TranslatedWeatherToResMapper
import com.example.core.di.annotation.qualifiers.Hourly
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal typealias HourlyToDisplayableItemMapper =
        Mapper<@JvmSuppressWildcards  HourlyWeather, HourlyWeatherDisplayableItem>

internal class HourlyWeatherToHourlyWeatherDisplayableItemMapper @Inject constructor(
    @param: Hourly private val formatter: DateTimeFormatter,
    private val weatherCodeToTranslatedWeatherMapper: CodeToTranslatedWeatherMapper,
    private val translatedWeatherToResourceMapper: TranslatedWeatherToResMapper,
    private val whatTimeOfDay: WhatTimeOfDay
) : Mapper<HourlyWeather, HourlyWeatherDisplayableItem> {

    override suspend fun map(from: HourlyWeather): HourlyWeatherDisplayableItem {
        val timeOfDay = whatTimeOfDay.timeOfDayByHour(hour = from.time.hour)

        with(from) {
            return HourlyWeatherDisplayableItem(
                weatherCode = weatherCode,
                time = formatter.format(time),
                temperature = Temperature(value = temperature),
                imageResId = translatedWeatherToResourceMapper.map(
                    from = weatherCodeToTranslatedWeatherMapper.map(from = weatherCode) to timeOfDay,
                )
            )
        }
    }

}