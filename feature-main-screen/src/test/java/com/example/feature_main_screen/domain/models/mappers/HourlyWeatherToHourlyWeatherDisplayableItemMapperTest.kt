package com.example.feature_main_screen.domain.models.mappers

import com.example.core.data.models.Temperature
import com.example.core.data.models.TimeOfDay
import com.example.core.data.models.WhatTimeOfDay
import com.example.core.data.models.mappers.CodeToTranslatedWeatherMapper
import com.example.core.domain.models.TranslatedWeather
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
class HourlyWeatherToHourlyWeatherDisplayableItemMapperTest { // todo: rewrite

    private val whatTimeOfDay: WhatTimeOfDay = mockk()
    private val weatherCodeToTranslatedWeatherMapper: CodeToTranslatedWeatherMapper = mockk()
    private val translatedWeatherToResourceMapper: Mapper<Pair<TranslatedWeather, TimeOfDay>, Int> =
        mockk()
    private val formatter: DateTimeFormatter = mockk()
    private val localTime: LocalTime = mockk()
    private val timeOfDay: TimeOfDay = mockk()
    private val timeString: String = ""
    private val translatedWeather: TranslatedWeather = mockk()
    private val mapper = HourlyWeatherToHourlyWeatherDisplayableItemMapper(
        formatter = formatter,
        weatherCodeToTranslatedWeatherMapper = weatherCodeToTranslatedWeatherMapper,
        translatedWeatherToResourceMapper = translatedWeatherToResourceMapper,
        whatTimeOfDay = whatTimeOfDay
    )

    @Test fun `try to map a default value`() = runTest {

        val hourlyWeather = HourlyWeather(
            time = localTime,
            weatherCode = 1,
            temperature = 0.1
        )
        coEvery {
            weatherCodeToTranslatedWeatherMapper.map(from = hourlyWeather.weatherCode)
        } returns translatedWeather
        coEvery {
            translatedWeatherToResourceMapper.map(from = translatedWeather to timeOfDay)
        } returns 1
        coEvery { localTime.hour } coAnswers { 1 }
        coEvery { whatTimeOfDay.timeOfDayByHour(hour = 1) } returns timeOfDay
        coEvery { formatter.format(localTime) } returns timeString

        val expected = HourlyWeatherDisplayableItem(
            weatherCode = hourlyWeather.weatherCode,
            time = timeString,
            temperature = Temperature(value = hourlyWeather.temperature),
            imageResId = 1
        )
        val actual = mapper.map(from = hourlyWeather)

        assertEquals(expected, actual)
    }


}