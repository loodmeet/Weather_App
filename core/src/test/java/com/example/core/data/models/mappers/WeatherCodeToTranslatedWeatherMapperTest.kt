package com.example.core.data.models.mappers

import com.example.core.domain.models.TranslatedWeather.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class) class WeatherCodeToTranslatedWeatherMapperTest {

    private val mapper = WeatherCodeToTranslatedWeatherMapper.Base()

    @Test fun `should return CLEAR_SKY`() = runTest {
        val expected = CLEAR_SKY
        val actual = mapper.map(from = 0)
        assertEquals(expected, actual)
    }

    @Test fun `should return DRIZZLE`() = runTest {
        val expected = DRIZZLE
        val actual = mapper.map(from = 53)
        assertEquals(expected, actual)
    }

    @Test fun `should return UNKNOWN`() = runTest {
        val expected = UNKNOWN
        val actual = mapper.map(from = 100)
        assertEquals(expected, actual)
    }
}