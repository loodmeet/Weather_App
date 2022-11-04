package com.example.feature_main_screen.data.storage.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_main_screen.data.models.HourlyWeather
import java.time.LocalTime

@Entity data class MainScreenHourlyWeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val weatherCode: Int,
    val temperature: Double,
    val time: LocalTime,
) {
    internal fun toHourlyWeather() = HourlyWeather(
        weatherCode = weatherCode,
        time = time,
        temperature = temperature
    )
}
