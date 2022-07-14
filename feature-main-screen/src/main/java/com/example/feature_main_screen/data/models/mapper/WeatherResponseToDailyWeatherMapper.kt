package com.example.feature_main_screen.data.models.mapper

import com.example.core.data.models.Temperature
import com.example.core.data.models.TemperatureRange
import com.example.core.data.models.mappers.TranslatedWeatherToResourceMapper
import com.example.core.data.models.mappers.WeatherCodeToTranslatedWeatherMapper
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.core.di.annotation.DailyWeatherDateFormat
import com.example.core.utils.Mapper
import com.example.feature_main_screen.domain.models.DailyWeatherDisplayableItem
import java.text.DateFormat
import javax.inject.Inject


internal interface WeatherResponseToDailyWeatherMapper :
    Mapper<WeatherResponse, DailyWeatherDisplayableItem> {

    suspend fun map(from: WeatherResponse, day: Int): DailyWeatherDisplayableItem = map(from = from)

    class Base @Inject constructor(
        @param: DailyWeatherDateFormat private val dailyWeatherDateFormat: DateFormat,
        private val weatherCodeToTranslatedWeatherMapper: WeatherCodeToTranslatedWeatherMapper,
        private val translatedWeatherToResourceMapper: TranslatedWeatherToResourceMapper
    ) : WeatherResponseToDailyWeatherMapper {

        private var dayNumber = 0

        override suspend fun map(from: WeatherResponse, day: Int): DailyWeatherDisplayableItem {
            this.dayNumber = day
            return super.map(from, day)
        }

        override suspend fun map(from: WeatherResponse): DailyWeatherDisplayableItem = with(from) {
            return DailyWeatherDisplayableItem(
                dayNumber = dayNumber,
                weatherCode = daily.weatherCode[dayNumber],
                temperature = TemperatureRange(
                    firstValue = Temperature(value = daily.temperature2mMin[dayNumber]),
                    secondValue = Temperature(value = daily.temperature2mMax[dayNumber])
                ),
                date = dailyWeatherDateFormat.format(daily.time[dayNumber]),
                imageResId = translatedWeatherToResourceMapper
                    .map(
                        weatherCodeToTranslatedWeatherMapper
                            .map(daily.weatherCode[dayNumber])
                    )
            )
        }

    }

}