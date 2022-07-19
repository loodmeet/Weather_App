package com.example.feature_daily_weather_details.data.repository

import android.util.Log
import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.DateTimeProvider.TimeOfDay.*
import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.di.annotation.CoroutineContextIO
import com.example.core.utils.Config
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainRepositoryImpl @Inject constructor(
    private val dateTimeProvider: DateTimeProvider,
    private val networkRepository: BaseNetworkRepository<WeatherResponse>,
    private val storageRepository: BaseStorageRepository<WeatherResponse>,
    @param: CoroutineContextIO private val coroutineContext: CoroutineContext,
    private val hourlyWeatherListToWeatherForTimeOfDayMapper:
    Mapper<@JvmSuppressWildcards List<HourlyWeather>, WeatherForTimeOfDayDisplayableItem>,
    private val weatherResponseToDailyWeatherListMapper:
    Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<DailyWeather>>,
    private val weatherResponseToHourlyWeatherListMapper:
    Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<HourlyWeather>>,
    private val dailyWeatherToSelectedDateMapper:
    Mapper<@JvmSuppressWildcards DailyWeather, SelectedDateDisplayableItem>
) : MainRepository {

    init {
        Log.d(Config.MAIN_TAG, "repo in feature_daily_weather_details created")
    }

    lateinit var response: WeatherResponse

    override suspend fun fetchDateByDayNumber(dayNumber: Int): SelectedDateDisplayableItem =
        withContext(context = coroutineContext) {
            response = try {
                storageRepository.getData()
            } catch (e: StorageException) {
                storageRepository.updateData(data = networkRepository.fetchData())
                storageRepository.getData()
            }
            return@withContext dailyWeatherToSelectedDateMapper.map(
                from = weatherResponseToDailyWeatherListMapper.map(from = response)[dayNumber]
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

        val hourlyWeatherList = weatherResponseToHourlyWeatherListMapper.map(from = response)
            .subList(fromIndex = 0, toIndex = 24)
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

            with(hourlyWeatherListToWeatherForTimeOfDayMapper) {
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
