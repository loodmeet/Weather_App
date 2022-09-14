package com.example.feature_daily_weather_details.domain.usecases

import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.core.di.annotation.qualifiers.Daily
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal interface FetchSelectedDateUseCase {

    suspend fun execute(date: String): Result<LocalDate>

    class Base @Inject constructor(
        @param: CoroutineContextIO private val coroutineContext: CoroutineContext,
        @param: Daily private val formatter: DateTimeFormatter,
    ) : FetchSelectedDateUseCase {

        override suspend fun execute(date: String): Result<LocalDate> =
            withContext(context = coroutineContext) {

                return@withContext try {
                    Result.success(value = LocalDate.parse(date, formatter))
                } catch (e: Exception) {
                    Result.failure<LocalDate>(exception = e)
                }
            }
    }
}
