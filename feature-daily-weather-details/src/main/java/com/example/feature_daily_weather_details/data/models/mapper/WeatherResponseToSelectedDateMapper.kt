package com.example.feature_daily_weather_details.data.models.mapper

import com.example.core.di.annotation.DailyWeatherDateFormat
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import java.text.DateFormat
import javax.inject.Inject

internal interface WeatherResponseToSelectedDateMapper :
    Mapper<WeatherResponse, SelectedDateDisplayableItem> {

    suspend fun map(from: WeatherResponse, day: Int) = map(from = from)

    class Base @Inject constructor(
        @DailyWeatherDateFormat private val dateFormat: DateFormat
    ) : WeatherResponseToSelectedDateMapper {

        private var dayNumber = 0

        override suspend fun map(from: WeatherResponse, day: Int): SelectedDateDisplayableItem {
            dayNumber = day
            return super.map(from, day)
        }

        override suspend fun map(from: WeatherResponse): SelectedDateDisplayableItem =
            SelectedDateDisplayableItem(date = dateFormat.format(from.daily.time[dayNumber]))
    }
}
