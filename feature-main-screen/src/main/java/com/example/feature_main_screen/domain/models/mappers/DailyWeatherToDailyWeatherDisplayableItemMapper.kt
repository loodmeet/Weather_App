package com.example.feature_main_screen.domain.models.mappers

import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.DateTimeProvider.TimeOfDay.DAY
import com.example.core.data.models.Temperature
import com.example.core.data.models.TemperatureRange
import com.example.core.di.annotation.Daily
import com.example.core.domain.models.TranslatedWeather
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.domain.models.DailyWeatherDisplayableItem
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal class DailyWeatherToDailyWeatherDisplayableItemMapper @Inject constructor(
    @param: Daily private val formatter: DateTimeFormatter,
    private val weatherCodeToTranslatedWeatherMapper: Mapper<@JvmSuppressWildcards Int, @JvmSuppressWildcards TranslatedWeather>,
    private val translatedWeatherToResourceMapper: Mapper<@JvmSuppressWildcards Pair<TranslatedWeather, DateTimeProvider.TimeOfDay>, Int>
) : Mapper<DailyWeather, DailyWeatherDisplayableItem> {

    override suspend fun map(from: DailyWeather): DailyWeatherDisplayableItem = with(from) {
        return@with DailyWeatherDisplayableItem(
            weatherCode = weatherCode,
            temperature = TemperatureRange(
                firstValue = Temperature(value = temperatureMin),
                secondValue = Temperature(value = temperatureMax)
            ),
            date = formatter.format(date),
            imageResId = translatedWeatherToResourceMapper
                .map(weatherCodeToTranslatedWeatherMapper.map(weatherCode) to DAY)
        )
    }
}