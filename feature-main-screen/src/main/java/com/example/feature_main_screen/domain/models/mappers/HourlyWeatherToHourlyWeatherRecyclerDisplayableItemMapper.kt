package com.example.feature_main_screen.domain.models.mappers

import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.domain.models.HourlyWeatherRecyclerDisplayableItem
import javax.inject.Inject

internal typealias HourlyToRecyclerDisplayableItemMapper =
        Mapper<@JvmSuppressWildcards List<HourlyWeather>, HourlyWeatherRecyclerDisplayableItem>

internal class HourlyWeatherToHourlyWeatherRecyclerDisplayableItemMapper @Inject constructor(
    private val hourlyWeatherMapper: HourlyToDisplayableItemMapper
) : Mapper<@JvmSuppressWildcards List<HourlyWeather>, HourlyWeatherRecyclerDisplayableItem> {

    override suspend fun map(from: List<HourlyWeather>): HourlyWeatherRecyclerDisplayableItem =
        HourlyWeatherRecyclerDisplayableItem(
            items = List(size = from.size) { index ->
                hourlyWeatherMapper.map(from = from[index])
            }
        )
}