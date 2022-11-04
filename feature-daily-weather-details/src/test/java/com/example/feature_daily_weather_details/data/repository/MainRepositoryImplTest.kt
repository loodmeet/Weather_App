package com.example.feature_daily_weather_details.data.repository

import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToDailyListMapper
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToHourlyListMapper
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.data.network.retrofit.WeatherService
import com.example.feature_daily_weather_details.data.storage.repository.StorageRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import retrofit2.Response
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
internal class MainRepositoryImplTest {

    private val storageRepository: StorageRepository = mockk()
    private val responseToDailyListMapper: ResponseToDailyListMapper = mockk()
    private val responseToHourlyListMapper: ResponseToHourlyListMapper = mockk()
    private val service: WeatherService = mockk()
    private val retrofitResponse: Response<WeatherResponse> = mockk()
    private val weatherResponse: WeatherResponse = mockk()

    private val repository = MainRepositoryImpl(
        coroutineContext = Dispatchers.IO,
        storageRepository = storageRepository,
        responseToDailyListMapper = responseToDailyListMapper,
        responseToHourlyListMapper = responseToHourlyListMapper,
        service = service
    )

    @Test fun `should return the correct hourly weather from storage`() = runTest {
        val date: LocalDate = mockk()
        val storageHourlyWeather: List<HourlyWeather> = mockk()
        val storageDailyWeather: List<DailyWeather> = mockk()

        coEvery { storageHourlyWeather.isEmpty() } returns false
        coEvery { storageDailyWeather.isEmpty() } returns false
        coEvery { storageRepository.getHourlyWeatherForDay(dayDate = date) } returns storageHourlyWeather
        coEvery { responseToHourlyListMapper.map(from = weatherResponse) } returns storageHourlyWeather
        coEvery { responseToDailyListMapper.map(from = weatherResponse) } returns storageDailyWeather
        coEvery { service.executeByDefaultRequest() } returns retrofitResponse

        val actual = repository.fetchWeatherByDate(date = date)

        assertEquals(storageHourlyWeather, actual)
    }

    @Test fun `should insert the correct hourly weather to the storage repository if the StorageException was thrown`() {
        // todo
    }
}