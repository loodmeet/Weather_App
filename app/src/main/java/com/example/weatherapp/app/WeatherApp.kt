package com.example.weatherapp.app

import android.app.Application
import android.content.Context
import com.example.feature_main_screen.di.dependencies.FeatureStore as MainScreenFeatureStore
import com.example.feature_daily_weather_details.di.dependencies.FeatureStore as DailyWeatherDetailsFeatureStore
import com.example.weatherapp.di.component.AppComponent
import com.example.weatherapp.di.component.DaggerAppComponent

internal class WeatherApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()

        MainScreenFeatureStore.dependencies = appComponent
        DailyWeatherDetailsFeatureStore.dependencies = appComponent
    }
}

internal val Context.appComponent: AppComponent
    get() = when (this) {
        is WeatherApp -> appComponent
        else -> this.applicationContext.appComponent
    }
