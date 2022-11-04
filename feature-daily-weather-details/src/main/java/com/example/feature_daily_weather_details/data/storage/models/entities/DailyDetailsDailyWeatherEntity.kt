package com.example.feature_daily_weather_details.data.storage.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.feature_daily_weather_details.data.models.DailyWeather
import java.time.LocalDate

@Entity data class DailyDetailsDailyWeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val date: LocalDate
) { internal fun toDailyWeather() = DailyWeather(date = date) }