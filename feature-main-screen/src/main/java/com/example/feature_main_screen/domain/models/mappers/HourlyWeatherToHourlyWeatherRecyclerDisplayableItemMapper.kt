package com.example.feature_main_screen.domain.models.mappers

import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.domain.models.HourlyWeatherRecyclerDisplayableItem
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import javax.inject.Inject

internal class HourlyWeatherToHourlyWeatherRecyclerDisplayableItemMapper @Inject constructor(
    private val hourlyWeatherMapper: Mapper<@JvmSuppressWildcards HourlyWeather, HourlyWeatherDisplayableItem>
) : Mapper<@JvmSuppressWildcards List<HourlyWeather>, HourlyWeatherRecyclerDisplayableItem> {

    override suspend fun map(from: List<HourlyWeather>): HourlyWeatherRecyclerDisplayableItem =
        HourlyWeatherRecyclerDisplayableItem(
            items = List(size = from.size) { index ->
                hourlyWeatherMapper.map(from = from[index])
            }
        )
}