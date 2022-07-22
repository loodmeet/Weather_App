package com.example.feature_main_screen.data.repository

import android.util.Log
import com.example.core.data.models.DateTimeProvider
import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.di.annotation.CoroutineContextIO
import com.example.core.ui.DisplayableItem
import com.example.core.utils.Config
import com.example.feature_main_screen.data.models.mappers.ResponseToDailyListMapper
import com.example.feature_main_screen.data.models.mappers.ResponseToHourlyListMapper
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.feature_main_screen.domain.models.mappers.DailyAndHourlyToHeaderMapper
import com.example.feature_main_screen.domain.models.mappers.DailyToDisplayableItemMapper
import com.example.feature_main_screen.domain.models.mappers.HourlyToRecyclerDisplayableItemMapper
import com.example.feature_main_screen.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainRepositoryImpl @Inject constructor(
    private val hourlyToRecyclerMapper: HourlyToRecyclerDisplayableItemMapper,
    private val dailyToDisplayableItemMapper: DailyToDisplayableItemMapper,
    private val responseToDailyListMapper: ResponseToDailyListMapper,
    private val responseToHourlyListMapper: ResponseToHourlyListMapper,
    private val headerMapper: DailyAndHourlyToHeaderMapper,
    private val dateTimeProvider: DateTimeProvider,
    private val networkRepository: BaseNetworkRepository<WeatherResponse>,
    private val storageRepository: BaseStorageRepository<WeatherResponse>,
    @CoroutineContextIO private val coroutineContext: CoroutineContext
) : MainRepository {

    init { Log.d(Config.MAIN_TAG, "repo in feature_main_screen created") }

    override suspend fun currentDate(): LocalDateTime = withContext(context = coroutineContext) {
        return@withContext LocalDateTime.now()
    }

    override suspend fun fetchData(): List<DisplayableItem> =
        withContext(context = coroutineContext) {
            val response = try {
                storageRepository.getData()
            } catch(e: StorageException) {
                storageRepository.updateData(data = networkRepository.fetchData())
                storageRepository.getData()
            }

            val currentHour = dateTimeProvider.currentHour()

            val hourlyWeatherList = responseToHourlyListMapper.map(from = response)
            val dailyWeatherList = responseToDailyListMapper.map(from = response)
            val dailyWeatherDisplayableItemList = List(size = 6) { index ->
                dailyToDisplayableItemMapper.map(from = dailyWeatherList[index + 1])
            }

            val hourlyWeatherRecycler = hourlyToRecyclerMapper
                .map(from = hourlyWeatherList.subList(
                    fromIndex = currentHour + 0,
                    toIndex = currentHour + 24
                ))

            // todo: not a repository logic (should be in the domain layer)
            val headerMapper = headerMapper.map(dailyWeatherList[0] to hourlyWeatherList[0])

            return@withContext mutableListOf(hourlyWeatherRecycler, headerMapper).apply {
                addAll(dailyWeatherDisplayableItemList)
            }
        }
}
