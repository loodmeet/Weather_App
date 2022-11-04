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
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
internal class WeatherResponseToDailyWeatherListMapperTest { // todo: rewrite

    private val response: WeatherResponse = mockk()
    private val dateString = ""
    private val formatter: DateTimeFormatter = mockk()
    private val date: LocalDate = mockk()
    private val mapper = WeatherResponseToDailyWeatherListMapper(formatter = formatter)

    @Test fun `try to map a default response`() = runTest {
        val dailyResponse: DailyWeatherResponse = mockk()
        val dailyWeatherStringList = List(size = 3) { index -> index.toString() }

        coEvery { dailyResponse.date } coAnswers { dailyWeatherStringList }
        coEvery { response.daily } returns dailyResponse
        coEvery { LocalDate.parse(dateString, formatter) } returns date

        val expected = List(size = 3) { mockk<DailyWeather>() }
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