package com.example.feature_main_screen.domain.models.mappers

import com.example.core.data.models.Temperature
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import com.example.feature_main_screen.domain.models.HourlyWeatherRecyclerDisplayableItem
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
internal class HourlyWeatherToHourlyWeatherRecyclerDisplayableItemMapperTest {
    private val hourlyWeatherMapper: Mapper<HourlyWeather, HourlyWeatherDisplayableItem> = mockk()
    private val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.CANADA)
    private val time = LocalTime.parse("10:00", formatter)!!

    private val mapper = HourlyWeatherToHourlyWeatherRecyclerDisplayableItemMapper(
        hourlyWeatherMapper = hourlyWeatherMapper
    )

    @Test fun `try to map a default value`() = runTest {
        val hourlyWeatherList = listOf(
            HourlyWeather(time = time, weatherCode = 1, temperature = 0.1),
            HourlyWeather(time = time, weatherCode = 2, temperature = 0.2),
            HourlyWeather(time = time, weatherCode = 3, temperature = 0.3)
        )
        val hourlyWeatherDisplayableItemList = listOf(
            HourlyWeatherDisplayableItem(weatherCode = 1, time = time.format(formatter), temperature = Temperature(value = 0.1), imageResId = 10),
            HourlyWeatherDisplayableItem(weatherCode = 2, time = time.format(formatter), temperature = Temperature(value = 0.2), imageResId = 10),
            HourlyWeatherDisplayableItem(weatherCode = 3, time = time.format(formatter), temperature = Temperature(value = 0.3), imageResId = 10),
        )
        coEvery { hourlyWeatherMapper.map(from = hourlyWeatherList[0]) } returns hourlyWeatherDisplayableItemList[0]
        coEvery { hourlyWeatherMapper.map(from = hourlyWeatherList[1]) } returns hourlyWeatherDisplayableItemList[1]
        coEvery { hourlyWeatherMapper.map(from = hourlyWeatherList[2]) } returns hourlyWeatherDisplayableItemList[2]

        val expected = HourlyWeatherRecyclerDisplayableItem(items = hourlyWeatherDisplayableItemList)
        val actual = mapper.map(from = hourlyWeatherList)

        assertEquals(expected, actual)
    }

}