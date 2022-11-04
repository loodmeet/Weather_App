package com.example.feature_daily_weather_details.data.storage.models.daos

interface FeatureDailyWeatherDao {

    fun dailyDetailsHourlyWeatherDao(): HourlyWeatherDao
    fun dailyDetailsDailyWeatherDao(): DailyWeatherDao
}
