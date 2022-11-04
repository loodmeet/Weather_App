package com.example.feature_main_screen.data.storage.models.daos

import androidx.room.*
import com.example.feature_main_screen.data.storage.models.entities.MainScreenHourlyWeatherEntity
import java.time.LocalDate

@Dao interface HourlyWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHourlyWeather(hourlyWeather: MainScreenHourlyWeatherEntity)

    @Delete fun deleteHourlyWeather(hourlyWeather: MainScreenHourlyWeatherEntity)

    @Query("SELECT * FROM MainScreenHourlyWeatherEntity")
    fun getHourlyWeatherList(): List<MainScreenHourlyWeatherEntity>
}