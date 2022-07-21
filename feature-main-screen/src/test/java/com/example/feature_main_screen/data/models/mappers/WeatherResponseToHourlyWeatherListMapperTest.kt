package com.example.feature_main_screen.data.models.mappers

import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.data.network.models.HourlyWeatherResponse
import com.example.feature_main_screen.data.network.models.WeatherResponse
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
internal class WeatherResponseToHourlyWeatherListMapperTest {

    private val response: WeatherResponse = mockk()
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.CANADA)
    private val timeString = "2022-07-01T10:00"
    private val time = LocalTime.parse(timeString, formatter)!!
    private val mapper = WeatherResponseToHourlyWeatherListMapper(formatter = formatter)

    @Test fun `try to map a default response`() = runTest() {
        coEvery { response.hourly } returns HourlyWeatherResponse(
            weatherCode = listOf(1, 2, 3),
            temperature2m = listOf(0.1, 0.2, 0.3),
            time = List(size = 3) { timeString }
        )

        val expected = listOf(
            HourlyWeather(time = time, weatherCode = 1, temperature = 0.1),
            HourlyWeather(time = time, weatherCode = 2, temperature = 0.2),
            HourlyWeather(time = time, weatherCode = 3, temperature = 0.3),
        )

        val actual = mapper.map(from = response)

        assertEquals(expected, actual)
    }

    @Test fun `try to map an empty response`() = runTest() {
        coEvery { response.hourly } returns HourlyWeatherResponse(
            weatherCode = listOf(),
            temperature2m = listOf(),
            time = listOf()
        )

        val expected = listOf<HourlyWeather>()

        val actual = mapper.map(from = response)

        assertEquals(expected, actual)
    }

}