package com.example.feature_daily_weather_details.data.storage.database

import com.example.feature_daily_weather_details.data.storage.models.daos.DailyWeatherDao
import com.example.feature_daily_weather_details.data.storage.models.daos.HourlyWeatherDao

interface FeatureDailyWeatherDao {

    fun hourlyWeatherDao(): HourlyWeatherDao
    fun dailyWeatherDao(): DailyWeatherDao
}
