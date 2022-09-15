package com.example.feature_daily_weather_details.domain.usecases

import com.example.core.di.annotation.qualifiers.CoroutineContextDefault
import com.example.core.di.annotation.qualifiers.Daily
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class FetchSelectedDateUseCase @Inject constructor(
    @param: CoroutineContextDefault private val coroutineContext: CoroutineContext,
    @param: Daily private val formatter: DateTimeFormatter
) {
    suspend fun execute(date: String): Result<LocalDate> =
        withContext(context = coroutineContext) {

            return@withContext try {
                Result.success(value = LocalDate.parse(date, formatter))
            } catch (e: Exception) {
                Result.failure<LocalDate>(exception = e)
            }
        }
}
