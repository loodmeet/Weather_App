package com.example.feature_main_screen.domain.models.mappers

import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.Temperature
import com.example.core.data.models.TemperatureRange
import com.example.core.data.models.mappers.WeatherCodeToTranslatedWeatherMapper
import com.example.core.domain.models.TranslatedWeather
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.domain.models.DailyWeatherDisplayableItem
import com.example.feature_main_screen.domain.models.HeaderDisplayableItem
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
internal class DailyWeatherAndHourlyWeatherToHeaderDisplayableItemMapperTest {

    private val weatherCodeToTranslatedWeatherMapper: WeatherCodeToTranslatedWeatherMapper = mockk()
    private val translatedWeatherToResourceMapper: Mapper<Pair<TranslatedWeather, DateTimeProvider.TimeOfDay>, Int> = mockk()
    private val dailyMapper: DailyWeatherToDailyWeatherDisplayableItemMapper = mockk()
    private val hourlyMapper: HourlyWeatherToHourlyWeatherDisplayableItemMapper = mockk()
    private val responseDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.CANADA)
    private val hourlyDateFormat = SimpleDateFormat("HH:mm", Locale.CANADA)
    private val dailyDateFormat = SimpleDateFormat("EE, d MMM", Locale.CANADA)
    private val date = responseDateFormat.parse("2022-07-01T10:00")!!
    private val mapper = DailyWeatherAndHourlyWeatherToHeaderDisplayableItemMapper(
        weatherCodeToTranslatedWeatherMapper = weatherCodeToTranslatedWeatherMapper,
        dailyWeatherMapper = dailyMapper,
        hourlyWeatherMapper = hourlyMapper
    )

    @Test fun `try to map a default value`() = runTest {
        val hourlyWeather = HourlyWeather(
            time = date, temperature = 0.1, weatherCode = 1
        )
        val dailyWeather = DailyWeather(
            date = date, temperatureMin = 0.1,  temperatureMax = 0.2, weatherCode = 1
        )

        val temperatureRange = TemperatureRange(
            firstValue = Temperature(dailyWeather.temperatureMin),
            secondValue = Temperature(dailyWeather.temperatureMax)
        )

        coEvery { weatherCodeToTranslatedWeatherMapper.map(1) } returns TranslatedWeather.CLEAR_SKY
        coEvery { translatedWeatherToResourceMapper.map(TranslatedWeather.CLEAR_SKY to DateTimeProvider.TimeOfDay.DAY) } returns 10
        coEvery { dailyMapper.map(from = dailyWeather) } returns DailyWeatherDisplayableItem(
            weatherCode = dailyWeather.weatherCode,
            temperature = temperatureRange,
            date = dailyDateFormat.format(dailyWeather.date),
            imageResId = 10
        )
        coEvery { hourlyMapper.map(from = hourlyWeather) } returns HourlyWeatherDisplayableItem(
            weatherCode = hourlyWeather.weatherCode,
            time = hourlyDateFormat.format(hourlyWeather.time),
            temperature = temperatureRange.getValues().first,
            imageResId = 10
        )



        val expected = HeaderDisplayableItem(
            currentTemperature = Temperature(value = hourlyWeather.temperature),
            dailyTemperature = temperatureRange,
            weatherDescriptionResId = weatherCodeToTranslatedWeatherMapper.map(1).stringResId,
            imageResId = 10
        )


        val actual = mapper.map(from = dailyWeather to hourlyWeather)

        assertEquals(expected, actual)
    }

}