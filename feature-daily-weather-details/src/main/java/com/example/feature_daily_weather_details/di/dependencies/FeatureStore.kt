package com.example.feature_daily_weather_details.di.dependencies

import kotlin.properties.Delegates.notNull

object FeatureStore : FeatureDailyWeatherDetailsDependenciesProvider {

    override var dependencies: FeatureDailyWeatherDetailsComponentDependencies by notNull()
}
