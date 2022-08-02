package com.example.feature_daily_weather_details.data.storage.models.entities

import androidx.room.Entity
import java.time.LocalDate
import java.time.LocalTime

@Entity data class HourlyWeatherEntity(
    val id: Long,
    val dayDate: LocalDate,
    val weatherCode: Int,
    val apparentTemperature: Double,
    val temperature: Double,
    val precipitation: Double,
    val windSpeed: Double,
    val windDirection: Int,
    val time: LocalTime,
    val relativeHumidity: Int
)
