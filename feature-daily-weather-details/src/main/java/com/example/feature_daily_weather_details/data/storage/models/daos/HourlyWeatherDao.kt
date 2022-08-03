package com.example.feature_daily_weather_details.data.storage.models.daos

import androidx.room.*
import com.example.feature_daily_weather_details.data.storage.models.entities.HourlyWeatherEntity
import java.time.LocalDate

@Dao internal interface HourlyWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourlyWeather(hourlyWeather: HourlyWeatherEntity)

    @Delete fun deleteHourlyWeather(hourlyWeather: HourlyWeatherEntity)

    @Query("SELECT * FROM HourlyWeatherEntity WHERE dayDate == :dayDate")
    fun getHourlyWeatherListByDay(dayDate: LocalDate): List<HourlyWeatherEntity>

    @Query("SELECT * FROM HourlyWeatherEntity")
    fun getHourlyWeatherList(): List<HourlyWeatherEntity>
}