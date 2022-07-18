package com.example.feature_main_screen.domain.models.mappers

import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.Temperature
import com.example.core.di.annotation.HourlyWeatherDateFormat
import com.example.core.domain.models.TranslatedWeather
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import java.text.DateFormat
import javax.inject.Inject

internal class HourlyWeatherToHourlyWeatherDisplayableItemMapper @Inject constructor(
    @param: HourlyWeatherDateFormat private val hourlyWeatherDateFormat: DateFormat,
    private val weatherCodeToTranslatedWeatherMapper: Mapper<@JvmSuppressWildcards Int, @JvmSuppressWildcards TranslatedWeather>,
    private val translatedWeatherToResourceMapper: Mapper<@JvmSuppressWildcards Pair<TranslatedWeather, DateTimeProvider.TimeOfDay>, Int>,
    private val dateTimeProvider: DateTimeProvider
) : Mapper<HourlyWeather, HourlyWeatherDisplayableItem> {
    private var hourNumber = 0

    override suspend fun map(from: HourlyWeather): HourlyWeatherDisplayableItem {
        val timeOfDay = dateTimeProvider.timeOfDayByHour(hour = hourNumber)

        with(from) {
            return HourlyWeatherDisplayableItem(
                weatherCode = weatherCode,
                time = hourlyWeatherDateFormat.format(time),
                temperature = Temperature(value = temperature),
                imageResId = translatedWeatherToResourceMapper.map(
                    from = weatherCodeToTranslatedWeatherMapper.map(from = weatherCode) to timeOfDay,
                )
            )
        }
    }

}




