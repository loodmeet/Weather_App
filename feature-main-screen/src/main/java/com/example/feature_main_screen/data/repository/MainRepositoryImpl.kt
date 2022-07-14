package com.example.feature_main_screen.data.repository

import android.util.Log
import com.example.core.data.models.DateTimeProvider
import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.di.annotation.CoroutineContextIO
import com.example.core.ui.DisplayableItem
import com.example.core.utils.Config
import com.example.feature_main_screen.data.models.mapper.WeatherResponseToDailyWeatherMapper
import com.example.feature_main_screen.data.models.mapper.WeatherResponseToHeaderMapper
import com.example.feature_main_screen.data.models.mapper.WeatherResponseToHourlyWeatherRecyclerMapper
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.feature_main_screen.data.storage.repository.StorageRepository
import com.example.feature_main_screen.domain.models.DailyWeatherDisplayableItem
import com.example.feature_main_screen.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainRepositoryImpl @Inject constructor(
    private val hourlyWeatherRecyclerMapper: WeatherResponseToHourlyWeatherRecyclerMapper,
    private val dailyWeatherMapper: WeatherResponseToDailyWeatherMapper,
    private val headerMapper: WeatherResponseToHeaderMapper,
    private val dateTimeProvider: DateTimeProvider,
    private val networkRepository: BaseNetworkRepository<WeatherResponse>,
    private val storageRepository: BaseStorageRepository<WeatherResponse>,
    @CoroutineContextIO private val coroutineContext: CoroutineContext
) : MainRepository {

    init { Log.d(Config.MAIN_TAG, "repo in feature_main_screen created") }


    override suspend fun currentDate(): Date = withContext(context = coroutineContext) {
        return@withContext dateTimeProvider.currentDate()
    }

    override suspend fun fetchData(): List<DisplayableItem> =
        withContext(context = coroutineContext) {
            val response = try {
                storageRepository.getData()
            } catch(e: StorageException) {
                storageRepository.updateData(data = networkRepository.fetchData())
                storageRepository.getData()
            }

            // todo: use "currentTime" fun

            val currentHour = dateTimeProvider.currentHour()

            val hourlyWeatherRecycler = hourlyWeatherRecyclerMapper
                .map(from = response, startingHour = currentHour)

            val dailyWeatherList = mutableListOf<DailyWeatherDisplayableItem>().apply {
                for (dayNumber in 1..6)
                    this.add(dailyWeatherMapper.map(from = response, day = dayNumber))
            }

            val headerMapper = headerMapper.map(response, day = 0, hour = 0)

            return@withContext mutableListOf(hourlyWeatherRecycler, headerMapper).apply {
                addAll(dailyWeatherList)
            }
        }

}

