package com.example.feature_main_screen.di.modules

import com.example.feature_main_screen.data.network.retrofit.WeatherService
import com.example.feature_main_screen.di.qualifiers.FeatureMainScreen
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module internal class DataModule {

    @[FeatureMainScreen Provides] fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }
}
