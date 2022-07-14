package com.example.feature_daily_weather_details.di.modules

import com.example.feature_daily_weather_details.di.annotations.FeatureDailyWeatherDetails
import com.example.feature_daily_weather_details.data.network.retrofit.WeatherService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal object DataModule {

    @[FeatureDailyWeatherDetails Provides] fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}
