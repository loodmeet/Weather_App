package com.example.feature_main_screen.domain.use_cases

import com.example.core.data.models.DateTimeProvider
import com.example.core.di.annotation.Daily
import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.domain.models.UpdateDateDisplayableItem
import com.example.feature_main_screen.domain.repository.MainRepository
import javax.inject.Inject
import com.example.core.di.annotation.Hourly
import com.example.feature_main_screen.domain.models.DividerDisplayableItem
import com.example.feature_main_screen.domain.models.MoreButtonDisplayableItem
import com.example.feature_main_screen.domain.models.mappers.DailyAndHourlyToHeaderMapper
import com.example.feature_main_screen.domain.models.mappers.DailyToDisplayableItemMapper
import com.example.feature_main_screen.domain.models.mappers.HourlyToRecyclerDisplayableItemMapper
import java.time.format.DateTimeFormatter

internal interface FetchDataUseCase {

    suspend fun execute(): Result<List<DisplayableItem>>

    class Base @Inject constructor(
        private val hourlyToRecyclerMapper: HourlyToRecyclerDisplayableItemMapper,
        private val headerMapper: DailyAndHourlyToHeaderMapper,
        private val dailyToDisplayableItemMapper: DailyToDisplayableItemMapper,
        private val dividerDisplayableItem: DividerDisplayableItem,
        private val repository: MainRepository,
        @param: Hourly private val hourlyFormatter: DateTimeFormatter,
        @param: Daily private val dailyFormatter: DateTimeFormatter,
        private val dateTimeProvider: DateTimeProvider
    ) : FetchDataUseCase {

        override suspend fun execute(): Result<List<DisplayableItem>> {
            val currentHour = dateTimeProvider.currentHour()
            val weather = try {
                repository.fetchWeatherForWeek()
            } catch (e: Exception) {
                return Result.failure(exception = e)
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
            val currentDateTime = dateTimeProvider.currentDateTime()
            val currentDate = dailyFormatter.format(currentDateTime)
            val currentTime = hourlyFormatter.format(currentDateTime)
            val updateDate = UpdateDateDisplayableItem(date = currentTime)
            val moreButton = MoreButtonDisplayableItem(date = currentDate)

            return Result.success(value = mutableListOf(
                hourlyWeatherRecycler, headerMapper,
                updateDate, dividerDisplayableItem, moreButton
            ).apply {
                addAll(dailyWeatherDisplayableItemList)
            })
        }
    }
}
