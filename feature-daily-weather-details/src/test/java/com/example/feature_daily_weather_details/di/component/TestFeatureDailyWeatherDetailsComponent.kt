package com.example.feature_daily_weather_details.di.component

import com.example.feature_daily_weather_details.data.models.mapper.WeatherResponseToSelectedDateMapperBaseTest
import com.example.feature_daily_weather_details.data.models.mapper.WeatherResponseToWeatherForTimeOfDayMapperBaseTest
import com.example.feature_daily_weather_details.di.modules.TestMainModule
import dagger.Component
import javax.inject.Singleton

@[Singleton Component(
    modules = [TestMainModule::class],
//    dependencies = [FeatureDailyWeatherDetailsComponent::class]
)]
internal interface TestFeatureDailyWeatherDetailsComponent {

    fun inject(testClass: WeatherResponseToWeatherForTimeOfDayMapperBaseTest)
    fun inject(testClass: WeatherResponseToSelectedDateMapperBaseTest)

//    @Component.Builder interface Builder {
//
//        fun dependencies(dependencies: FeatureDailyWeatherDetailsComponent): Builder
//
//        fun build(): TestFeatureDailyWeatherDetailsComponent
//
//    }

}