package com.example.feature_daily_weather_details.ui.models.mappers

import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.TimeOfDay.DAY
import com.example.core.data.models.TimeOfDay.EVENING
import com.example.core.data.models.TimeOfDay.NIGHT
import com.example.core.data.models.TimeOfDay.MORNING
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.domain.models.DomainModel
import com.example.feature_daily_weather_details.ui.models.WeatherForTimeOfDay
import com.example.feature_daily_weather_details.ui.models.WeatherForTimeOfDayRecycler
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DomainModelToWeatherForTimeOfDayRecyclerMapper @Inject constructor(
    private val mapper: Mapper<List<HourlyWeather>, WeatherForTimeOfDay>,
    private val dateTimeProvider: DateTimeProvider,
    @param: CoroutineContextIO private val coroutineContext: CoroutineContext
) : Mapper<DomainModel, @JvmSuppressWildcards WeatherForTimeOfDayRecycler> {

    override suspend fun map(
        from: DomainModel
    ): WeatherForTimeOfDayRecycler = withContext(context = coroutineContext) {

        with(from) {
            with(dateTimeProvider) {
                val weatherForDay = mapper.map(
                    from = hourlyWeather.subList(
                        fromIndex = dateTimeProvider.hourRangeByTimeOfDay(DAY).first,
                        toIndex = dateTimeProvider.hourRangeByTimeOfDay(DAY).last
                    )
                )
                val weatherForNight = mapper.map(
                    from = hourlyWeather.subList(
                        fromIndex = hourRangeByTimeOfDay(NIGHT).first,
                        toIndex = hourRangeByTimeOfDay(NIGHT).last
                    )
                )
                val weatherForEvening = mapper.map(
                    from = hourlyWeather.subList(
                        fromIndex = hourRangeByTimeOfDay(EVENING).first,
                        toIndex = hourRangeByTimeOfDay(EVENING).last
                    )
                )
                val weatherForMorning = mapper.map(
                    from = hourlyWeather.subList(
                        fromIndex = hourRangeByTimeOfDay(MORNING).first,
                        toIndex = hourRangeByTimeOfDay(MORNING).last
                    )
                )
                return@withContext WeatherForTimeOfDayRecycler(
                    items = listOf(
                        weatherForMorning,
                        weatherForDay,
                        weatherForEvening,
                        weatherForNight
                    )
                )
            }
        }

    }
}