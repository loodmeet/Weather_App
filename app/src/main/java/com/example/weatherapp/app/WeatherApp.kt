package com.example.weatherapp.app

import android.app.Application
import android.content.Context
import com.example.feature_main_screen.di.dependencies.FeatureStore as MainScreenFeatureStore
import com.example.feature_daily_weather_details.di.dependencies.FeatureStore as DailyWeatherDetailsFeatureStore
import com.example.weatherapp.common.Common.BASE_URL
import com.example.weatherapp.di.component.AppComponent
import com.example.weatherapp.di.component.DaggerAppComponent
import kotlin.math.pow
import kotlin.math.roundToInt


class WeatherApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .baseUrl(baseUrl = BASE_URL)
            .build()
        MainScreenFeatureStore.dependencies = appComponent
        DailyWeatherDetailsFeatureStore.dependencies = appComponent
    }

}

val Context.appComponent: AppComponent
    get() = when (this) {
        is WeatherApp -> appComponent
        else -> this.applicationContext.appComponent
    }

//fun Double.round(decimals: Int): Double {
//    var multiplier = 1.0
//    repeat(decimals) { multiplier *= 10 }
//    return round(this * multiplier) / multiplier
//}

