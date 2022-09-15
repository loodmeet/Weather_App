package com.example.feature_main_screen.domain.models.mappers

import com.example.core.data.models.TimeOfDay.DAY
import com.example.core.data.models.Temperature
import com.example.core.data.models.TemperatureRange
import com.example.core.data.models.mappers.CodeToTranslatedWeatherMapper
import com.example.core.data.models.mappers.TranslatedWeatherToResMapper
import com.example.core.di.annotation.qualifiers.Daily
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.domain.models.DailyWeatherDisplayableItem
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal typealias DailyToDisplayableItemMapper =
        Mapper<@JvmSuppressWildcards DailyWeather, DailyWeatherDisplayableItem>

internal class DailyWeatherToDailyWeatherDisplayableItemMapper @Inject constructor(
    @param: Daily private val formatter: DateTimeFormatter,
    private val codeToTranslatedWeatherMapper: CodeToTranslatedWeatherMapper,
    private val translatedWeatherToResourceMapper: TranslatedWeatherToResMapper
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
                .map(codeToTranslatedWeatherMapper.map(weatherCode) to DAY)
        )
    }
}