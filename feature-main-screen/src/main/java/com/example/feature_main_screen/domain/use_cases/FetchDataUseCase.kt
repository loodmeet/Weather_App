package com.example.feature_main_screen.domain.use_cases

import com.example.core.data.models.CurrentDateTimeProvider
import com.example.core.di.annotation.qualifiers.CoroutineContextDefault
import com.example.core.di.annotation.qualifiers.Daily
import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.domain.models.UpdateDateDisplayableItem
import com.example.feature_main_screen.domain.repository.MainRepository
import javax.inject.Inject
import com.example.core.di.annotation.qualifiers.Hourly
import com.example.feature_main_screen.domain.models.DividerDisplayableItem
import com.example.feature_main_screen.domain.models.MoreButtonDisplayableItem
import com.example.feature_main_screen.domain.models.mappers.DailyAndHourlyToHeaderMapper
import com.example.feature_main_screen.domain.models.mappers.DailyToDisplayableItemMapper
import com.example.feature_main_screen.domain.models.mappers.HourlyToRecyclerDisplayableItemMapper
import kotlinx.coroutines.withContext
import java.time.format.DateTimeFormatter
import kotlin.coroutines.CoroutineContext

internal class FetchDataUseCase @Inject constructor(
    private val hourlyToRecyclerMapper: HourlyToRecyclerDisplayableItemMapper,
    private val headerMapper: DailyAndHourlyToHeaderMapper,
    private val dailyToDisplayableItemMapper: DailyToDisplayableItemMapper,
    private val dividerDisplayableItem: DividerDisplayableItem,
    private val repository: MainRepository,
    @param: Hourly private val hourlyFormatter: DateTimeFormatter,
    @param: Daily private val dailyFormatter: DateTimeFormatter,
    private val currentDateTimeProvider: CurrentDateTimeProvider,
    @param: CoroutineContextDefault private val coroutineContext: CoroutineContext
) {

    suspend fun execute(): Result<List<DisplayableItem>> = withContext(context = coroutineContext) {
        val currentHour = currentDateTimeProvider.currentHour()
        val weather = try {
            repository.fetchWeatherForWeek()
        } catch (e: Exception) {
            return@withContext Result.failure(exception = e)
        }
        val dailyWeatherList = weather.first
        val hourlyWeatherList = weather.second
        val dailyWeatherDisplayableItemList = List(size = 6) { index ->
            dailyToDisplayableItemMapper.map(from = dailyWeatherList[index + 1])
        }
        val hourlyWeatherRecycler = hourlyToRecyclerMapper
            .map(
                from = hourlyWeatherList.subList(
                    fromIndex = currentHour + 0,
                    toIndex = currentHour + 24
                )
            )
        val headerMapper = headerMapper.map(dailyWeatherList[0] to hourlyWeatherList[0])
        val currentDateTime = currentDateTimeProvider.currentDateTime()
        val currentDate = dailyFormatter.format(currentDateTime)
        val currentTime = hourlyFormatter.format(currentDateTime)
        val updateDate = UpdateDateDisplayableItem(date = currentTime)
        val moreButton = MoreButtonDisplayableItem(date = currentDate)

        Result.success(
            value = mutableListOf(
                hourlyWeatherRecycler, headerMapper,
                updateDate, dividerDisplayableItem, moreButton
            ).apply {
                addAll(dailyWeatherDisplayableItemList)
            })
    }
}
