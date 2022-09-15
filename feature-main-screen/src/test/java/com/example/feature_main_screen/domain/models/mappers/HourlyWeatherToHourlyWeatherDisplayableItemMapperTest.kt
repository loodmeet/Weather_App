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

    private val whatTimeOfDay = mockk<WhatTimeOfDay>()
    private val weatherCodeToTranslatedWeatherMapper: CodeToTranslatedWeatherMapper = mockk()
    private val translatedWeatherToResourceMapper: Mapper<Pair<TranslatedWeather, TimeOfDay>, Int> =
        mockk()
    private val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.CANADA)
    private val time = LocalTime.parse("10:00", formatter)!!
    private val timeOfDay = TimeOfDay.MORNING
    private val mapper = HourlyWeatherToHourlyWeatherDisplayableItemMapper(
        formatter = formatter,
        weatherCodeToTranslatedWeatherMapper = weatherCodeToTranslatedWeatherMapper,
        translatedWeatherToResourceMapper = translatedWeatherToResourceMapper,
        whatTimeOfDay = whatTimeOfDay
    )

    @Test fun `try to map a default value`() = runTest {

        val hourlyWeather = HourlyWeather(
            time = time,
            weatherCode = 1,
            temperature = 0.1
        )
        coEvery {
            weatherCodeToTranslatedWeatherMapper.map(from = hourlyWeather.weatherCode)
        } returns TranslatedWeather.CLEAR_SKY
        coEvery {
            translatedWeatherToResourceMapper.map(from = TranslatedWeather.CLEAR_SKY to timeOfDay)
        } returns 1


        val expected = HourlyWeatherDisplayableItem(
            weatherCode = hourlyWeather.weatherCode,
            time = formatter.format(time),
            temperature = Temperature(value = hourlyWeather.temperature),
            imageResId = translatedWeatherToResourceMapper.map(
                from = weatherCodeToTranslatedWeatherMapper.map(
                    from = hourlyWeather.weatherCode
                ) to timeOfDay,
            )
        )
        val actual = mapper.map(from = hourlyWeather)

        assertEquals(expected, actual)
    }


}