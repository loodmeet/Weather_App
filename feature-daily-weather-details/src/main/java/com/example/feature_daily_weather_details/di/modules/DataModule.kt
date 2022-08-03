package com.example.feature_daily_weather_details.di.modules

import android.app.Application
import com.example.feature_daily_weather_details.di.annotations.FeatureDailyWeatherDetails
import com.example.feature_daily_weather_details.data.network.retrofit.WeatherService
import com.example.feature_daily_weather_details.data.storage.database.LocalDatabase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
internal object DataModule {

    @[FeatureDailyWeatherDetails Provides] fun provideWeatherService(retrofit: Retrofit): WeatherService {
        return retrofit.create(WeatherService::class.java)
    }

    @[FeatureDailyWeatherDetails Provides] fun provideDatabase(application: Application) =
        LocalDatabase.getLocalDatabase(application = application)
}
