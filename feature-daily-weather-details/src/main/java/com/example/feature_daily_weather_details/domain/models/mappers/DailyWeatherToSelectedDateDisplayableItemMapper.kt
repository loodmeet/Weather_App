package com.example.feature_daily_weather_details.domain.models.mappers

import com.example.core.di.annotation.qualifiers.Daily
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal typealias DailyToSelectedDateMapper =
        Mapper<@JvmSuppressWildcards DailyWeather, SelectedDateDisplayableItem>

internal class DailyWeatherToSelectedDateDisplayableItemMapper @Inject constructor(
    @param: Daily private val formatter: DateTimeFormatter
) : Mapper<@JvmSuppressWildcards DailyWeather, SelectedDateDisplayableItem> {

    override suspend fun map(from: DailyWeather): SelectedDateDisplayableItem =
        SelectedDateDisplayableItem(date = formatter.format(from.date))
}
