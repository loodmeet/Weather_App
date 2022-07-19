package com.example.feature_daily_weather_details.data.models.mappers

import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.DateTimeProvider.TimeOfDay.DAY
import com.example.core.data.models.Temperature
import com.example.core.data.models.TemperatureRange
import com.example.core.data.models.mappers.TranslatedWeatherToResourceMapper
import com.example.core.data.models.mappers.WeatherCodeToTranslatedWeatherMapper
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.example.api.R.drawable.ic_snow_32
import com.example.core.domain.models.TranslatedWeather.SLIGHT_INTENSITY_SNOW
import com.example.feature_daily_weather_details.data.network.models.responce.HourlyWeatherResponse
import com.example.feature_daily_weather_details.domain.models.mappers.WeatherResponseToWeatherForTimeOfDayMapper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherResponseToWeatherForTimeOfDayMapperBaseTest {
    private val dateTimeProvider = mockk<DateTimeProvider>()
    private val translatedWeatherToResourceMapper = mockk<TranslatedWeatherToResourceMapper>()
    private val weatherCodeToTranslatedWeatherMapper = mockk<WeatherCodeToTranslatedWeatherMapper>()
    private val testWeatherResponseToWeatherForTimeOfDayMapper =
        WeatherResponseToWeatherForTimeOfDayMapper.Base(
            dateTimeProvider = dateTimeProvider,
            translatedWeatherToResourceMapper = translatedWeatherToResourceMapper,
            weatherCodeToTranslatedWeatherMapper = weatherCodeToTranslatedWeatherMapper
        )
    private val weatherResponse = mockk<WeatherResponse>()
    private val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.CANADA)
    private val dayRange = 12..16
    private val timeOfDay = DAY
    private val numberOfDays = 7
    private val hourListSize = 24 * numberOfDays
    private val intList = List(size = hourListSize) { 0 + it }
    private val doubleList = List(size = hourListSize) { 0.1 + it }
    private val dateList = List(size = hourListSize) { dateFormat.parse("2022-07-05T00:00")!! }
    private val hourlyWeatherCodeForDay = listOf(
        0, 1, 2, 3, 45, 51, 53, 55, 56, 57, 61, 63,
        66, 67, 71, 73, 75, 80, 81, 82, 85, 86, 96, 65
    )
    private val weatherCodeList = mutableListOf<Int>().apply {
        for (i in 0..numberOfDays) addAll(hourlyWeatherCodeForDay)
    }

    init {
        every { dateTimeProvider.hourRangeByTimeOfDay(timeOfDay = DAY) } returns dayRange
        every { weatherResponse.hourly } returns HourlyWeatherResponse(
            weatherCode = weatherCodeList,
            apparentTemperature = doubleList,
            windDirection10m = intList,
            temperature2m = doubleList,
            precipitation = doubleList,
            windSpeed10m = doubleList,
            time = dateList,
            relativeHumidity2m = intList,
            cloudCover = intList
        )

    }

    // todo: for example
    @AfterEach fun tearDown() {
//        Mockito.reset(testWeatherResponseToWeatherForTimeOfDayMapper)

    }

    @Test fun `test to map the 1st day`() = runTest {
        val dayNumber = 1
        val fromIndex = dayNumber * 24 + 12
        val toIndex = dayNumber * 24 + 16
        val intHourlySubList = intList.subList(fromIndex = fromIndex, toIndex = toIndex)
        val doubleHourlySubList = doubleList.subList(fromIndex = fromIndex, toIndex = toIndex)
        val intHourlySubListAverage = intHourlySubList.average().toInt()
        val doubleHourlySubListAverage = doubleHourlySubList.average()
        val weatherCode = 71

        coEvery {
            weatherCodeToTranslatedWeatherMapper.map(from = weatherCode)
        } returns SLIGHT_INTENSITY_SNOW
        coEvery {
            translatedWeatherToResourceMapper.map(from = SLIGHT_INTENSITY_SNOW to timeOfDay)
        } returns ic_snow_32

        val actual = testWeatherResponseToWeatherForTimeOfDayMapper
            .map(from = weatherResponse, day = dayNumber, timeOfDay = timeOfDay)
        val expected = WeatherForTimeOfDayDisplayableItem(
            timeOfDay = timeOfDay,
            relativeHumidity = intHourlySubListAverage,
            windSpeed = doubleHourlySubListAverage,
            windDirection = intHourlySubListAverage,
            temperature = TemperatureRange(
                firstValue = Temperature(value = doubleHourlySubList[0]),
                secondValue = Temperature(value = doubleHourlySubList[doubleHourlySubList.size - 1])
            ),
            apparentTemperature = Temperature(value = doubleHourlySubListAverage),
            precipitation = doubleHourlySubListAverage,
            imageResId = ic_snow_32
        )
        assertEquals(expected, actual)
    }

    @Test fun `test to map the zero day`() = runTest {
        val dayNumber = 0
        val fromIndex = dayNumber * 24 + 12
        val toIndex = dayNumber * 24 + 16
        val intHourlySubList = intList.subList(fromIndex = fromIndex, toIndex = toIndex)
        val doubleHourlySubList = doubleList.subList(fromIndex = fromIndex, toIndex = toIndex)
        val intHourlySubListAverage = intHourlySubList.average().toInt()
        val doubleHourlySubListAverage = doubleHourlySubList.average()
        val weatherCode = 71

        coEvery {
            weatherCodeToTranslatedWeatherMapper.map(from = weatherCode)
        } returns SLIGHT_INTENSITY_SNOW
        coEvery {
            translatedWeatherToResourceMapper.map(from = SLIGHT_INTENSITY_SNOW to timeOfDay)
        } returns ic_snow_32

        val actual = testWeatherResponseToWeatherForTimeOfDayMapper
            .map(from = weatherResponse, day = dayNumber, timeOfDay = timeOfDay)
        val expected = WeatherForTimeOfDayDisplayableItem(
            timeOfDay = timeOfDay,
            relativeHumidity = intHourlySubListAverage,
            windSpeed = doubleHourlySubListAverage,
            windDirection = intHourlySubListAverage,
            temperature = TemperatureRange(
                firstValue = Temperature(value = doubleHourlySubList[0]),
                secondValue = Temperature(value = doubleHourlySubList[doubleHourlySubList.size - 1])
            ),
            apparentTemperature = Temperature(value = doubleHourlySubListAverage),
            precipitation = doubleHourlySubListAverage,
            imageResId = ic_snow_32
        )
        assertEquals(expected, actual)
    }

    @Test fun `test to map the last day`() = runTest {
        val dayNumber = numberOfDays - 1
        val fromIndex = dayNumber * 24 + 12
        val toIndex = dayNumber * 24 + 16
        val intHourlySubList = intList.subList(fromIndex = fromIndex, toIndex = toIndex)
        val doubleHourlySubList = doubleList.subList(fromIndex = fromIndex, toIndex = toIndex)
        val intHourlySubListAverage = intHourlySubList.average().toInt()
        val doubleHourlySubListAverage = doubleHourlySubList.average()
        val weatherCode = 71

        coEvery {
            weatherCodeToTranslatedWeatherMapper.map(from = weatherCode)
        } returns SLIGHT_INTENSITY_SNOW
        coEvery {
            translatedWeatherToResourceMapper.map(from = SLIGHT_INTENSITY_SNOW to timeOfDay)
        } returns ic_snow_32

        val actual = testWeatherResponseToWeatherForTimeOfDayMapper
            .map(from = weatherResponse, day = dayNumber, timeOfDay = timeOfDay)
        val expected = WeatherForTimeOfDayDisplayableItem(
            timeOfDay = timeOfDay,
            relativeHumidity = intHourlySubListAverage,
            windSpeed = doubleHourlySubListAverage,
            windDirection = intHourlySubListAverage,
            temperature = TemperatureRange(
                firstValue = Temperature(value = doubleHourlySubList[0]),
                secondValue = Temperature(value = doubleHourlySubList[doubleHourlySubList.size - 1])
            ),
            apparentTemperature = Temperature(value = doubleHourlySubListAverage),
            precipitation = doubleHourlySubListAverage,
            imageResId = ic_snow_32
        )
        assertEquals(expected, actual)
    }

}

