package com.example.feature_daily_weather_details.domain.models.mappers

import com.example.core.data.models.DateTimeProvider.TimeOfDay
import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.Temperature
import com.example.core.data.models.TemperatureRange
import com.example.core.domain.models.TranslatedWeather
import com.example.core.utils.Mapper
import com.example.core.utils.round
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import java.lang.IllegalArgumentException
import javax.inject.Inject
//
//internal interface WeatherResponseToWeatherForTimeOfDayMapper :
//    Mapper<WeatherResponse, WeatherForTimeOfDayDisplayableItem> {
//
//    suspend fun map(from: WeatherResponse, day: Int, timeOfDay: TimeOfDay) = map(from = from)
//
//    class Base @Inject constructor(
//        private val dateTimeProvider: DateTimeProvider,
//        private val translatedWeatherToResourceMapper: Mapper<@JvmSuppressWildcards Pair<TranslatedWeather, TimeOfDay>, Int>,
//        private val weatherCodeToTranslatedWeatherMapper: Mapper<@JvmSuppressWildcards Int, @JvmSuppressWildcards TranslatedWeather>
//    ) : WeatherResponseToWeatherForTimeOfDayMapper {
//
//        private var currentTimeOfDay = TimeOfDay.DAY
//        private var currentNumberOfDay = 0
//        private val numberOfHours = 24
//
//        override suspend fun map(
//            from: WeatherResponse,
//            day: Int,
//            timeOfDay: TimeOfDay
//        ): WeatherForTimeOfDayDisplayableItem {
//            currentNumberOfDay = day
//            currentTimeOfDay = timeOfDay
//            return super.map(from, day, timeOfDay)
//        }
//
//        override suspend fun map(from: WeatherResponse): WeatherForTimeOfDayDisplayableItem =
//            with(from.hourly) {
//                val startIndex = currentNumberOfDay * numberOfHours +
//                        dateTimeProvider.hourRangeByTimeOfDay(timeOfDay = currentTimeOfDay).first
//                val endIndex = currentNumberOfDay * numberOfHours +
//                        dateTimeProvider.hourRangeByTimeOfDay(timeOfDay = currentTimeOfDay).last
//                val weatherCodeList = weatherCode
//                    .subList(fromIndex = startIndex, toIndex = endIndex).sorted()
//                val sortedTemperatureList = temperature2m
//                    .subList(fromIndex = startIndex, toIndex = endIndex).sorted()
//                val temperature = TemperatureRange(
//                    firstValue = Temperature(value = sortedTemperatureList[0]),
//                    secondValue = Temperature(value = sortedTemperatureList[endIndex - startIndex - 1])
//                )
//                val apparentTemperature = Temperature(
//                    value = apparentTemperature
//                        .subList(fromIndex = startIndex, toIndex = endIndex).average()
//                        .round(decimals = 1)
//                )
//                val weatherCode = weatherCodeList[weatherCodeList.size / 2]
//                val relativeHumidity = relativeHumidity2m
//                    .subList(fromIndex = startIndex, toIndex = endIndex).average().toInt()
//                val windSpeed = windSpeed10m
//                    .subList(fromIndex = startIndex, toIndex = endIndex)
//                    .average().round(decimals = 1)
//                val windDirection = windDirection10m
//                    .subList(fromIndex = startIndex, toIndex = endIndex)
//                    .average().toInt()
//                val imageResId = translatedWeatherToResourceMapper.map(
//                    from = weatherCodeToTranslatedWeatherMapper.map(weatherCode) to currentTimeOfDay,
//                )
//                val precipitation = precipitation
//                    .subList(fromIndex = startIndex, toIndex = endIndex)
//                    .average().round(decimals = 1)
//                return WeatherForTimeOfDayDisplayableItem(
//                    timeOfDay = currentTimeOfDay,
//                    relativeHumidity = relativeHumidity,
//                    windDirection = windDirection,
//                    windSpeed = windSpeed,
//                    temperature = temperature,
//                    apparentTemperature = apparentTemperature,
//                    imageResId = imageResId,
//                    precipitation = precipitation,
//                )
//            }
//    }
//}

internal class HourlyWeatherListToWeatherForTimeOfDayDisplayableItemMapper @Inject constructor(
    private val dateTimeProvider: DateTimeProvider,
    private val translatedWeatherToResourceMapper: Mapper<@JvmSuppressWildcards Pair<TranslatedWeather, TimeOfDay>, Int>,
    private val weatherCodeToTranslatedWeatherMapper: Mapper<@JvmSuppressWildcards Int, @JvmSuppressWildcards TranslatedWeather>
) : Mapper<@JvmSuppressWildcards List<HourlyWeather>, WeatherForTimeOfDayDisplayableItem> {

    private var timeOfDay = TimeOfDay.DAY

    private suspend fun determineTimeOfDay(from: List<HourlyWeather>) {
        timeOfDay = dateTimeProvider.timeOfDayByHour(hour = from[0].time.hour)
        for (hourlyWeather in from) {
            if (dateTimeProvider.timeOfDayByHour(hour = hourlyWeather.time.hour) != timeOfDay)
                throw IllegalArgumentException("there are some problems with time of day")
        }
    }

    override suspend fun map(from: List<HourlyWeather>): WeatherForTimeOfDayDisplayableItem {
        determineTimeOfDay(from = from)
        val weatherCodeList = List(size = from.size) { index -> from[index].weatherCode }.sorted()
        val weatherCode = weatherCodeList[weatherCodeList.size / 2]
        val imageResId = translatedWeatherToResourceMapper.map(
            from = weatherCodeToTranslatedWeatherMapper.map(weatherCode) to timeOfDay,
        )
        val temperatureList = List(size = from.size) { index -> from[index].temperature }
        val temperatureRange = TemperatureRange(
            firstValue = Temperature(value = temperatureList.min()),
            secondValue = Temperature(value = temperatureList.max())
        )
        val windSpeed = List(size = from.size) { index ->
            from[index].windSpeed
        }.average().round(decimals = 1)
        val windDirection = List(size = from.size) { index ->
            from[index].windDirection
        }.average().toInt()
        val relativeHumidity = List(size = from.size) { index ->
            from[index].relativeHumidity
        }.average().toInt()
        val apparentTemperature = Temperature(
            value = List(size = from.size) { index ->
                from[index].apparentTemperature
            }.average().round(decimals = 1)
        )
        val precipitation = List(size = from.size) { index ->
            from[index].precipitation
        }.average().round(decimals = 1)

        return WeatherForTimeOfDayDisplayableItem(
            timeOfDay = timeOfDay,
            relativeHumidity = relativeHumidity,
            windDirection = windDirection,
            windSpeed = windSpeed,
            temperature = temperatureRange,
            apparentTemperature = apparentTemperature,
            imageResId = imageResId,
            precipitation = precipitation,
        )
    }
}
