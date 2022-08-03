package com.example.feature_daily_weather_details.data.storage.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import java.time.LocalDate
import java.time.LocalTime

@Entity internal data class HourlyWeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val dayDate: LocalDate,
    val weatherCode: Int,
    val apparentTemperature: Double,
    val temperature: Double,
    val precipitation: Double,
    val windSpeed: Double,
    val windDirection: Int,
    val time: LocalTime,
    val relativeHumidity: Int
) {
    fun toHourlyWeather() = HourlyWeather(
        weatherCode = weatherCode,
        apparentTemperature = apparentTemperature,
        temperature = temperature,
        precipitation = precipitation,
        windSpeed = windSpeed,
        windDirection = windDirection,
        time = time,
        relativeHumidity = relativeHumidity
    )
}
