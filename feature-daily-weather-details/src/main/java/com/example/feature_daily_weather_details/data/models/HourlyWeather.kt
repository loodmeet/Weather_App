package com.example.feature_daily_weather_details.data.models

import com.example.core.data.storage.models.DataEntity
import com.example.core.data.storage.models.StoredData
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyDetailsHourlyWeatherEntity
import java.time.LocalDate
import java.time.LocalTime

data class HourlyWeather(
    val date: LocalDate,
    val weatherCode: Int,
    val apparentTemperature: Double,
    val temperature: Double,
    val precipitation: Double,
    val windSpeed: Double,
    val windDirection: Int,
    val time: LocalTime,
    val relativeHumidity: Int
) : StoredData {

    override fun toEntity(): DataEntity<StoredData> = DailyDetailsHourlyWeatherEntity(
        id = 0,
        dayDate = date,
        weatherCode = weatherCode,
        apparentTemperature = apparentTemperature,
        temperature = temperature,
        precipitation = precipitation,
        windSpeed = windSpeed,
        windDirection = windDirection,
        time = time,
        relativeHumidity = relativeHumidity
    ) as DataEntity<StoredData>
}