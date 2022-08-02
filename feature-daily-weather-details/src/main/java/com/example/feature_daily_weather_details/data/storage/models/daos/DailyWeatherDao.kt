package com.example.feature_daily_weather_details.data.storage.models.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyWeatherEntity

@Dao interface DailyWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyWeather(dailyWeather: DailyWeatherEntity)

    @Delete fun deleteDailyWeather(dailyWeather: DailyWeatherEntity)
}