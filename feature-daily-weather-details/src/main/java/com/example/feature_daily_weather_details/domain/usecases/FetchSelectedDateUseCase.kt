package com.example.feature_daily_weather_details.domain.usecases

import com.example.core.di.annotation.CoroutineContextIO
import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal interface FetchSelectedDateUseCase {

    suspend fun execute(dayNumber: Int): Result<SelectedDateDisplayableItem>

    class Base @Inject constructor(
        @CoroutineContextIO private val coroutineContext: CoroutineContext,
        private val repository: MainRepository
    ) : FetchSelectedDateUseCase {

        override suspend fun execute(dayNumber: Int) = withContext(context = coroutineContext) {

            return@withContext try {
                Result.success(value = repository.fetchDateByDayNumber(dayNumber = dayNumber))
            } catch (e: Exception) {
                Result.failure(exception = e)
            }
        }
    }
}