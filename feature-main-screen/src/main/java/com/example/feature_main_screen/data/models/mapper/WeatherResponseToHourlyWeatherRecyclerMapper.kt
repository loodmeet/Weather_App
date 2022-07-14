package com.example.feature_main_screen.data.models.mapper

import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.feature_main_screen.domain.models.HourlyWeatherRecyclerDisplayableItem
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import javax.inject.Inject

internal interface WeatherResponseToHourlyWeatherRecyclerMapper :
    Mapper<WeatherResponse, HourlyWeatherRecyclerDisplayableItem> {

    suspend fun map(from: WeatherResponse, startingHour: Int) = map(from = from)

    class Base @Inject constructor(
        private val hourlyWeatherMapper: WeatherResponseToHourlyWeatherMapper
    ) : WeatherResponseToHourlyWeatherRecyclerMapper {
        private var startingHour: Int = 1


        override suspend fun map(from: WeatherResponse, startingHour: Int): HourlyWeatherRecyclerDisplayableItem {
            this.startingHour = startingHour
            return super.map(from, startingHour)
        }

        override suspend fun map(from: WeatherResponse): HourlyWeatherRecyclerDisplayableItem =
            with(from) {
                val items = mutableListOf<HourlyWeatherDisplayableItem>().apply {

                    for (hourNumber in (startingHour..startingHour + 23)) {
                        val hour = if (hourNumber < 24) hourNumber else hourNumber - 24
                        this.add(hourlyWeatherMapper.map(from = from, hour = hour))
                    }
                }
                return HourlyWeatherRecyclerDisplayableItem(
                    items = items
                )
            }
    }
}
