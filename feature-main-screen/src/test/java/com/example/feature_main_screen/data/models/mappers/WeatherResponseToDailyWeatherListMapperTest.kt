package com.example.feature_main_screen.data.models.mappers

import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.data.network.models.DailyWeatherResponse
import com.example.feature_main_screen.data.network.models.WeatherResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
internal class WeatherResponseToDailyWeatherListMapperTest {

    private val response: WeatherResponse = mockk()
    private val responseDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.CANADA)
    private val date = responseDateFormat.parse("2022-07-01T10:00")!!
    private val mapper = WeatherResponseToDailyWeatherListMapper()

    @Test fun `try to map a default response`() = runTest() {
        coEvery { response.daily } returns DailyWeatherResponse(
            weatherCode = listOf(1, 2, 3),
            temperature2mMin = listOf(0.1, 0.2, 0.3),
            temperature2mMax = listOf(0.1, 0.2, 0.3),
            date = List(size = 3) { date }
        )

        val expected = listOf(
            DailyWeather(date = date, weatherCode = 1, temperatureMin = 0.1, temperatureMax = 0.1),
            DailyWeather(date = date, weatherCode = 2, temperatureMin = 0.2, temperatureMax = 0.2),
            DailyWeather(date = date, weatherCode = 3, temperatureMin = 0.3, temperatureMax = 0.3)
        )

        val actual = mapper.map(from = response)

        assertEquals(expected, actual)
    }

    @Test fun `try to map an empty response`() = runTest() {
        coEvery { response.daily } returns DailyWeatherResponse(
            weatherCode = listOf(),
            temperature2mMin = listOf(),
            temperature2mMax = listOf(),
            date = listOf()
        )

        val expected = listOf<HourlyWeather>()

        val actual = mapper.map(from = response)

        assertEquals(expected, actual)
    }
}