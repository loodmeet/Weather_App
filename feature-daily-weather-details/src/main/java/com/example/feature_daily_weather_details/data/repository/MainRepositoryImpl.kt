package com.example.feature_daily_weather_details.data.repository

import android.util.Log
import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.DateTimeProvider.TimeOfDay.*
import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.di.annotation.CoroutineContextIO
import com.example.core.utils.Config
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToDailyListMapper
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToHourlyListMapper
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.example.feature_daily_weather_details.domain.models.mappers.DailyToSelectedDateMapper
import com.example.feature_daily_weather_details.domain.models.mappers.HourlyListToWeatherForTimeOfDayMapper
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainRepositoryImpl @Inject constructor(
    @param: CoroutineContextIO private val coroutineContext: CoroutineContext,
    private val dateTimeProvider: DateTimeProvider,
    private val networkRepository: BaseNetworkRepository<WeatherResponse>,
    private val storageRepository: BaseStorageRepository<WeatherResponse>,
    private val hourlyListToWeatherForTimeOfDayMapper: HourlyListToWeatherForTimeOfDayMapper,
    private val responseToDailyListMapper: ResponseToDailyListMapper,
    private val responseToHourlyListMapper: ResponseToHourlyListMapper,
    private val dailyToSelectedDateMapper: DailyToSelectedDateMapper
) : MainRepository {

    init {
        Log.d(Config.MAIN_TAG, "repo in feature_daily_weather_details created")
    }

    lateinit var response: WeatherResponse

    override suspend fun fetchWeatherByDay(date: LocalDate): List<WeatherForTimeOfDayDisplayableItem> =
        withContext(context = coroutineContext) {
            response = try {
                storageRepository.getData()
            } catch (e: StorageException) {
                storageRepository.updateData(data = networkRepository.fetchData())
                storageRepository.getData()
            }

            return@withContext fetchWeatherByDayNumber(
                dayNumber = responseToDailyListMapper.map(from = response)
                    .indexOfFirst { dailyWeather -> dailyWeather.date == date }
            )
        }

    override suspend fun fetchDateByDayNumber(dayNumber: Int): SelectedDateDisplayableItem =
        withContext(context = coroutineContext) {
            response = try {
                storageRepository.getData()
            } catch (e: StorageException) {
                storageRepository.updateData(data = networkRepository.fetchData())
                storageRepository.getData()
            }
            return@withContext dailyToSelectedDateMapper.map(
                from = responseToDailyListMapper.map(from = response)[dayNumber]
            )
        }

    override suspend fun fetchWeatherByDayNumber(
        dayNumber: Int
    ): List<WeatherForTimeOfDayDisplayableItem> = withContext(context = coroutineContext) {

        response = try {
            storageRepository.getData()
        } catch (e: StorageException) {
            storageRepository.updateData(data = networkRepository.fetchData())
            storageRepository.getData()
        }

        val hourlyWeatherList = responseToHourlyListMapper.map(from = response)
            .subList(fromIndex = dayNumber * 24 + 0, toIndex = dayNumber * 24 + 24)
        with(dateTimeProvider) {

            val morningHours = hourlyWeatherList.subList(
                fromIndex = hourRangeByTimeOfDay(MORNING).first,
                toIndex = hourRangeByTimeOfDay(MORNING).last
            )
            val dayHours = hourlyWeatherList.subList(
                fromIndex = hourRangeByTimeOfDay(DAY).first,
                toIndex = hourRangeByTimeOfDay(DAY).last
            )
            val eveningHours = hourlyWeatherList.subList(
                fromIndex = hourRangeByTimeOfDay(EVENING).first,
                toIndex = hourRangeByTimeOfDay(EVENING).last
            )
            val nightHours = hourlyWeatherList.subList(
                fromIndex = hourRangeByTimeOfDay(NIGHT).first,
                toIndex = hourRangeByTimeOfDay(NIGHT).last
            )

            with(hourlyListToWeatherForTimeOfDayMapper) {
                val weatherForDay = map(from = dayHours)
                val weatherForNight = map(from = nightHours)
                val weatherForEvening = map(from = eveningHours)
                val weatherForMorning = map(from = morningHours)

                return@withContext listOf(
                    weatherForMorning,
                    weatherForDay,
                    weatherForEvening,
                    weatherForNight
                )
            }
        }
    }
}
