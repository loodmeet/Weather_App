package com.example.feature_main_screen.domain.models.mappers

import com.example.core.data.models.mappers.CodeToTranslatedWeatherMapper
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.domain.models.HeaderDisplayableItem
import javax.inject.Inject

internal typealias DailyAndHourlyToHeaderMapper =
        Mapper<@JvmSuppressWildcards Pair<DailyWeather, HourlyWeather>, HeaderDisplayableItem>

internal class DailyAndHourlyWeatherToHeaderDisplayableItemMapper @Inject constructor(
    private val codeToTranslatedWeatherMapper: CodeToTranslatedWeatherMapper,
    private val dailyWeatherMapper: DailyToDisplayableItemMapper,
    private val hourlyWeatherMapper: HourlyToDisplayableItemMapper
) : Mapper<@JvmSuppressWildcards Pair<DailyWeather, HourlyWeather>, HeaderDisplayableItem> {

    override suspend fun map(from: Pair<DailyWeather, HourlyWeather>): HeaderDisplayableItem {
        val dailyWeatherDisplayableItem = dailyWeatherMapper.map(from = from.first)
        val hourlyWeatherDisplayableItem = hourlyWeatherMapper.map(from = from.second)

        return HeaderDisplayableItem(
            currentTemperature = hourlyWeatherDisplayableItem.temperature,
            dailyTemperature = dailyWeatherDisplayableItem.temperature,
            weatherDescriptionResId = codeToTranslatedWeatherMapper
                .map(dailyWeatherDisplayableItem.weatherCode)
                .stringResId,
            imageResId = dailyWeatherDisplayableItem.imageResId
        )
    }
}