package com.example.feature_main_screen.data.storage.models.daos

import androidx.room.*
import com.example.feature_main_screen.data.storage.models.entities.MainScreenDailyWeatherEntity

@Dao interface DailyWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(dailyWeather: MainScreenDailyWeatherEntity)

    @Delete fun deleteDailyWeather(dailyWeather: MainScreenDailyWeatherEntity)

    @Query("SELECT * FROM MainScreenDailyWeatherEntity")
    fun getAllDailyWeather(): List<MainScreenDailyWeatherEntity>
}