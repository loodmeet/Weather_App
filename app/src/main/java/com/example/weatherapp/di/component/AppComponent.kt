package com.example.weatherapp.di.component

import com.example.feature_daily_weather_details.di.dependencies.FeatureDailyWeatherDetailsComponentDependencies
import com.example.feature_main_screen.di.dependencies.FeatureMainScreenComponentDependencies
import com.example.weatherapp.di.modules.AppModule
import com.example.feature_main_screen.di.qualifiers.ActionToDailyWeatherDetails
import com.example.weatherapp.di.scopes.AppScope
import com.example.weatherapp.ui.screens.MainActivity
import dagger.Component
import retrofit2.Retrofit

@[AppScope Component(modules = [AppModule::class])]
internal interface AppComponent : FeatureMainScreenComponentDependencies,
    FeatureDailyWeatherDetailsComponentDependencies {

    fun inject(activity: MainActivity)

    override val retrofit: Retrofit

    override val actionToFragmentDailyWeatherDetails: Int // todo need to use qualifiers
}