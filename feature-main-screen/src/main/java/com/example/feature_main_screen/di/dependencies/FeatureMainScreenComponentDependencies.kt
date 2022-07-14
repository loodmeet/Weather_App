package com.example.feature_main_screen.di.dependencies

import com.example.feature_main_screen.navigation.FeatureMainScreenNavCommandProvider
import retrofit2.Retrofit

interface FeatureMainScreenComponentDependencies {

    val featureMainScreenNavCommandProvider: FeatureMainScreenNavCommandProvider

    val retrofit: Retrofit

}