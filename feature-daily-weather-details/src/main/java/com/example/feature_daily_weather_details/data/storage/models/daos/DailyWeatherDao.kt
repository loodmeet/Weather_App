package com.example.feature_daily_weather_details.data.storage.models.daos

import androidx.room.*
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyWeatherEntity

@Dao internal interface DailyWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(dailyWeather: DailyWeatherEntity)

    @Delete fun deleteDailyWeather(dailyWeather: DailyWeatherEntity)

    @Query("SELECT * FROM DailyWeatherEntity")
    fun getAllDailyWeather(): List<DailyWeatherEntity>
}