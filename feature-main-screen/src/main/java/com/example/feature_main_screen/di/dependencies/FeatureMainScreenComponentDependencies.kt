package com.example.feature_main_screen.di.dependencies

import retrofit2.Retrofit

interface FeatureMainScreenComponentDependencies {

    val actionToFragmentDailyWeatherDetails: Int

    val retrofit: Retrofit
}