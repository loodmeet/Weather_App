package com.example.feature_daily_weather_details.data.storage.models.daos

import androidx.room.*
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyDetailsDailyWeatherEntity

@Dao interface DailyWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(dailyWeather: DailyDetailsDailyWeatherEntity)

    @Delete fun deleteDailyWeather(dailyWeather: DailyDetailsDailyWeatherEntity)

    @Query("SELECT * FROM DailyDetailsDailyWeatherEntity")
    fun getAllDailyWeather(): List<DailyDetailsDailyWeatherEntity>
}