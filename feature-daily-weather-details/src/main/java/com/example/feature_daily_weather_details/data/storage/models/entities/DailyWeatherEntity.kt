package com.example.feature_daily_weather_details.data.storage.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_daily_weather_details.data.models.DailyWeather
import java.time.LocalDate

@Entity internal data class DailyWeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val date: LocalDate
) { fun toDailyWeather() = DailyWeather(date = date) }