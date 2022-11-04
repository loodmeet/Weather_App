package com.example.feature_main_screen.di.dependencies

import com.example.feature_main_screen.data.storage.models.daos.FeatureMainScreenDao
import retrofit2.Retrofit

interface FeatureMainScreenComponentDependencies {

    val actionToFragmentDailyWeatherDetails: Int

    val featureMainScreenDao: FeatureMainScreenDao

    val mainScreenRetrofit: Retrofit
}