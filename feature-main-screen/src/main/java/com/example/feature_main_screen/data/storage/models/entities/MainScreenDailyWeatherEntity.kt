package com.example.feature_main_screen.data.storage.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_main_screen.data.models.DailyWeather
import java.time.LocalDate

@Entity data class MainScreenDailyWeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val date: LocalDate,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val weatherCode: Int,
) {
    internal fun toDailyWeather() = DailyWeather(
        date = date,
        temperatureMax = temperatureMax,
        temperatureMin = temperatureMin,
        weatherCode = weatherCode
    )
}