package com.example.feature_daily_weather_details.di.dependencies

internal interface FeatureDailyWeatherDetailsDependenciesProvider {

    val dependencies: FeatureDailyWeatherDetailsComponentDependencies

    companion object : FeatureDailyWeatherDetailsDependenciesProvider by FeatureStore
}