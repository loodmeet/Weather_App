package com.example.weatherapp.di.modules

import com.example.feature_daily_weather_details.data.storage.database.FeatureDailyWeatherDao
import com.example.weatherapp.data.storage.LocalDatabase
import com.example.weatherapp.di.scopes.AppScope
import dagger.Binds
import dagger.Module

@Module interface AppBindModule {

    @[Binds AppScope] fun bindsFeatureDailyWeatherDao(
        database: LocalDatabase
    ): FeatureDailyWeatherDao

}