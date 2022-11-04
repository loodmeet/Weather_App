package com.example.feature_main_screen.data.storage.models.daos

interface FeatureMainScreenDao {

    fun mainScreenHourlyWeatherDao(): HourlyWeatherDao
    fun mainScreenDailyWeatherDao(): DailyWeatherDao
}