package com.example.feature_main_screen.domain.use_cases

import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.domain.models.UpdateDateDisplayableItem
import com.example.feature_main_screen.domain.repository.MainRepository
import javax.inject.Inject
import com.example.core.di.annotation.HourlyWeatherDateFormat
import java.text.DateFormat
import java.util.*

internal interface FetchDataUseCase {

    suspend fun execute(): Result<List<DisplayableItem>>

    class Base @Inject constructor(
        private val repository: MainRepository,
        @param: HourlyWeatherDateFormat private val dateFormat: DateFormat,
        private val locale: Locale
    ) : FetchDataUseCase {

        override suspend fun execute(): Result<List<DisplayableItem>> {

            val items = try {
                repository.fetchData()
            } catch (e: Exception) {
                return Result.failure(exception = e)
            }

            val currentDate = repository.currentDate()
            val currentTime = dateFormat.format(currentDate)

            return Result.success(
                value = items.toMutableList().apply {
                    add(UpdateDateDisplayableItem(date = currentTime))
                }
            )
        }
    }
}

