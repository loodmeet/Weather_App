package com.example.feature_daily_weather_details.data.repository

import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToDailyListMapper
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToHourlyListMapper
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.data.network.repository.NetworkRepository
import com.example.feature_daily_weather_details.data.storage.repository.StorageRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
internal class MainRepositoryImplTest {

    private val networkRepository: NetworkRepository = mockk()
    private val storageRepository: StorageRepository = mockk()
    private val responseToDailyListMapper: ResponseToDailyListMapper = mockk()
    private val responseToHourlyListMapper: ResponseToHourlyListMapper = mockk()

    private val repository = MainRepositoryImpl(
        coroutineContext = Dispatchers.IO,
        networkRepository = networkRepository,
        storageRepository = storageRepository,
        responseToDailyListMapper = responseToDailyListMapper,
        responseToHourlyListMapper = responseToHourlyListMapper
    )

    @Test fun `should return the correct hourly weather from storage`() = runTest {
        val date = LocalDate.of(2022, 12, 1)
        val hourlyWeather: HourlyWeather = mockk()
        val dailyWeather = DailyWeather(date = date)
        val storageHourlyWeather = listOf(hourlyWeather, hourlyWeather)
        val storageDailyWeather = listOf(dailyWeather, dailyWeather)
        val response: WeatherResponse = mockk()

        coEvery { networkRepository.fetchData() } returns response
        coEvery { storageRepository.getHourlyWeatherForDay(dayDate = date) } returns storageHourlyWeather
        coEvery { responseToHourlyListMapper.map(from = response) } returns storageHourlyWeather
        coEvery { responseToDailyListMapper.map(from = response) } returns storageDailyWeather

        val actual = repository.fetchWeatherByDate(date = date)

        assertEquals(storageHourlyWeather, actual)
    }

    @Test fun `should insert the correct hourly weather to the storage repository if the StorageException was thrown`() {
        // todo
    }
}