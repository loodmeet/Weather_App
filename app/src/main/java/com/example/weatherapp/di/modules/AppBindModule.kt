package com.example.weatherapp.di.modules

import com.example.feature_daily_weather_details.data.storage.models.daos.FeatureDailyWeatherDao
import com.example.feature_main_screen.data.storage.models.daos.FeatureMainScreenDao
import com.example.weatherapp.data.storage.LocalDatabase
import com.example.weatherapp.di.scopes.AppScope
import dagger.Binds
import dagger.Module

@Module interface AppBindModule {

    @[Binds AppScope] fun bindsFeatureDailyWeatherDao(
        database: LocalDatabase
    ): FeatureDailyWeatherDao

    @[Binds AppScope] fun bindsFeatureMainScreenDao(
        database: LocalDatabase
    ): FeatureMainScreenDao
}