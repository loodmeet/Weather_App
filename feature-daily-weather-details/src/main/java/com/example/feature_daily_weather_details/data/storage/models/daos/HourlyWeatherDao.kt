package com.example.feature_daily_weather_details.data.storage.models.daos

import androidx.room.*
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyDetailsHourlyWeatherEntity
import java.time.LocalDate

@Dao interface HourlyWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourlyWeather(hourlyWeather: DailyDetailsHourlyWeatherEntity)

    @Delete fun deleteHourlyWeather(hourlyWeather: DailyDetailsHourlyWeatherEntity)

    @Query("SELECT * FROM DailyDetailsHourlyWeatherEntity WHERE dayDate == :dayDate")
    fun getHourlyWeatherListByDay(dayDate: LocalDate): List<DailyDetailsHourlyWeatherEntity>

    @Query("SELECT * FROM DailyDetailsHourlyWeatherEntity")
    fun getHourlyWeatherList(): List<DailyDetailsHourlyWeatherEntity>
}