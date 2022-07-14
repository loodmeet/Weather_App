package com.example.feature_daily_weather_details.data.repository

import com.example.core.ui.DisplayableItem
import com.example.feature_daily_weather_details.data.models.WeatherByHours
import com.example.feature_daily_weather_details.data.models.WeatherByHoursParams
import com.example.feature_daily_weather_details.data.models.WeatherForDay
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class TestRepo @Inject constructor() {
    private val date: Date = Date()
    private val weatherForDay: WeatherForDay = WeatherForDay(
        date = date,
        windSpeed10mMax = 1.0,
        temperature2mMin = 1.0,
        temperature2mMax = 1.0,
        weatherCode = 1,
        weatherByHours = WeatherByHours(
            params = WeatherByHoursParams(
                weatherCode = listOf(1),
                apparentTemperature = listOf(1.0),
                windSpeed10m = listOf(1.0),
                windDirection10m = listOf(1),
                temperature2m = listOf(1.0),
                precipitation = listOf(1.0),
                relativeHumidity2m = listOf(1),
                cloudCover = listOf(1)
            )
        ),
        precipitationSum = 1.0,
        sunset = date,
        sunrise = date
    )

    fun getItems() = mutableListOf<DisplayableItem>().apply {
        for (i in 0..3) this.add(weatherForDay)
    }
}




