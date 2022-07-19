package com.example.feature_daily_weather_details.data.models.mappers

import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
internal class WeatherResponseToSelectedDateMapperBaseTest {
    private val weatherResponse = mockk<WeatherResponse>()
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm", Locale.CANADA)
    private val mapper = WeatherResponseToSelectedDateMapper.Base(dateFormat = dateFormat)
    private val numberOfDays = 7
    private val dateList = List(size = numberOfDays) { dateFormat.parse("2022-07-05T00:00")!! }

    init { every { weatherResponse.daily.date } returns dateList }

    @Test fun `test to map the 1st day`() = runTest {
        val dayNumber = 1
        val date = weatherResponse.daily.date[dayNumber]
        val expected = SelectedDateDisplayableItem(date = dateFormat.format(date))
        val actual = mapper.map(from = weatherResponse, day = dayNumber)

        assertEquals(expected, actual)
    }

    @Test fun `test to map the zero day`() = runTest {
        val dayNumber = 0
        val date = weatherResponse.daily.date[dayNumber]
        val expected = SelectedDateDisplayableItem(date = dateFormat.format(date))
        val actual = mapper.map(from = weatherResponse, day = dayNumber)

        assertEquals(expected, actual)
    }

    @Test fun `test to map the last day`() = runTest {
        val dayNumber = weatherResponse.daily.date.size - 1
        val date = weatherResponse.daily.date[dayNumber]
        val expected = SelectedDateDisplayableItem(date = dateFormat.format(date))
        val actual = mapper.map(from = weatherResponse, day = dayNumber)

        assertEquals(expected, actual)
    }
}
