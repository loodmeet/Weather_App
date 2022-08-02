package com.example.feature_daily_weather_details.data.storage.models.entities

import androidx.room.Entity
import java.time.LocalDate

@Entity data class DailyWeatherEntity(
    val id: Long,
    val date: LocalDate
)