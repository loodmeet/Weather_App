package com.example.feature_daily_weather_details.data.models.mapper

import com.example.core.data.models.DateTimeProvider.TimeOfDay
import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.Temperature
import com.example.core.data.models.TemperatureRange
import com.example.core.data.models.mappers.TranslatedWeatherToResourceMapper
import com.example.core.data.models.mappers.WeatherCodeToTranslatedWeatherMapper
import com.example.core.utils.Mapper
import com.example.core.utils.round
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import javax.inject.Inject

internal interface WeatherResponseToWeatherForTimeOfDayMapper :
    Mapper<WeatherResponse, WeatherForTimeOfDayDisplayableItem> {

    suspend fun map(from: WeatherResponse, day: Int, timeOfDay: TimeOfDay) = map(from = from)

    class Base @Inject constructor(
        private val dateTimeProvider: DateTimeProvider,
        private val translatedWeatherToResourceMapper: TranslatedWeatherToResourceMapper,
        private val weatherCodeToTranslatedWeatherMapper: WeatherCodeToTranslatedWeatherMapper
    ) : WeatherResponseToWeatherForTimeOfDayMapper {

        private var currentTimeOfDay = TimeOfDay.DAY
        private var currentNumberOfDay = 0
        private val numberOfHours = 24

        override suspend fun map(
            from: WeatherResponse,
            day: Int,
            timeOfDay: TimeOfDay
        ): WeatherForTimeOfDayDisplayableItem {
            currentNumberOfDay = day
            currentTimeOfDay = timeOfDay
            return super.map(from, day, timeOfDay)
        }

        override suspend fun map(from: WeatherResponse): WeatherForTimeOfDayDisplayableItem =
            with(from.hourly) {
                val startIndex = currentNumberOfDay * numberOfHours +
                        dateTimeProvider.hourRangeByTimeOfDay(timeOfDay = currentTimeOfDay).first
                val endIndex = currentNumberOfDay * numberOfHours +
                        dateTimeProvider.hourRangeByTimeOfDay(timeOfDay = currentTimeOfDay).last
                val weatherCodeList = weatherCode
                    .subList(fromIndex = startIndex, toIndex = endIndex).sorted()
                val sortedTemperatureList = temperature2m
                    .subList(fromIndex = startIndex, toIndex = endIndex).sorted()
                val temperature = TemperatureRange(
                    firstValue = Temperature(value = sortedTemperatureList[0]),
                    secondValue = Temperature(value = sortedTemperatureList[endIndex - startIndex - 1])
                )
                val apparentTemperature = Temperature(
                    value = apparentTemperature
                        .subList(fromIndex = startIndex, toIndex = endIndex).average()
                        .round(decimals = 1)
                )
                val weatherCode = weatherCodeList[weatherCodeList.size / 2]
                val relativeHumidity = relativeHumidity2m
                    .subList(fromIndex = startIndex, toIndex = endIndex).average().toInt()
                val windSpeed = windSpeed10m
                    .subList(fromIndex = startIndex, toIndex = endIndex)
                    .average().round(decimals = 1)
                val windDirection = windDirection10m
                    .subList(fromIndex = startIndex, toIndex = endIndex)
                    .average().toInt()
                val imageResId = translatedWeatherToResourceMapper.map(
                    from = weatherCodeToTranslatedWeatherMapper.map(weatherCode),
                    timeOfDay = currentTimeOfDay
                )
                val precipitation = precipitation
                    .subList(fromIndex = startIndex, toIndex = endIndex)
                    .average().round(decimals = 1)
                return WeatherForTimeOfDayDisplayableItem(
                    timeOfDay = currentTimeOfDay,
                    relativeHumidity = relativeHumidity,
                    windDirection = windDirection,
                    windSpeed = windSpeed,
                    temperature = temperature,
                    apparentTemperature = apparentTemperature,
                    imageResId = imageResId,
                    precipitation = precipitation,
                )
            }
    }
}
