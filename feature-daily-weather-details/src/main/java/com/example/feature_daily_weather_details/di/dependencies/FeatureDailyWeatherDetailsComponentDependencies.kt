package com.example.feature_daily_weather_details.di.dependencies

import com.example.feature_daily_weather_details.data.storage.database.FeatureDailyWeatherDao
import retrofit2.Retrofit

interface FeatureDailyWeatherDetailsComponentDependencies {

    val dailyWeatherRetrofit: Retrofit

    val featureDailyWeatherDao: FeatureDailyWeatherDao
}