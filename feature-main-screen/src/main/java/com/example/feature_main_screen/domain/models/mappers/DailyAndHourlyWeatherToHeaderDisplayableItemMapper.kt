package com.example.feature_main_screen.domain.models.mappers

import com.example.core.domain.models.TranslatedWeather
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.domain.models.DailyWeatherDisplayableItem
import com.example.feature_main_screen.domain.models.HeaderDisplayableItem
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import javax.inject.Inject

internal class DailyAndHourlyWeatherToHeaderDisplayableItemMapper @Inject constructor(
    private val weatherCodeToTranslatedWeatherMapper: Mapper<@JvmSuppressWildcards Int, @JvmSuppressWildcards TranslatedWeather>,
    private val dailyWeatherMapper: Mapper<@JvmSuppressWildcards DailyWeather, DailyWeatherDisplayableItem>,
    private val hourlyWeatherMapper: Mapper<@JvmSuppressWildcards HourlyWeather, HourlyWeatherDisplayableItem>
) : Mapper<@JvmSuppressWildcards Pair<DailyWeather, HourlyWeather>, HeaderDisplayableItem> {

    override suspend fun map(from: Pair<DailyWeather, HourlyWeather>): HeaderDisplayableItem {
        val dailyWeatherDisplayableItem = dailyWeatherMapper.map(from = from.first)
        val hourlyWeatherDisplayableItem = hourlyWeatherMapper.map(from = from.second)

        return HeaderDisplayableItem(
            currentTemperature = hourlyWeatherDisplayableItem.temperature,
            dailyTemperature = dailyWeatherDisplayableItem.temperature,
            weatherDescriptionResId = weatherCodeToTranslatedWeatherMapper
                .map(dailyWeatherDisplayableItem.weatherCode)
                .stringResId,
            imageResId = dailyWeatherDisplayableItem.imageResId
        )
    }
}