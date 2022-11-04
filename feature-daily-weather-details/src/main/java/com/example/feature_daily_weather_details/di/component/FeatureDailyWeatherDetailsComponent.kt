package com.example.feature_daily_weather_details.di.component

import android.app.Application
import com.example.feature_daily_weather_details.di.annotations.FeatureDailyWeatherDetails
import com.example.core.di.dependensies.DisplayableItemsProvider
import com.example.core.di.modules.UtilsModule
import com.example.feature_daily_weather_details.di.dependencies.FeatureDailyWeatherDetailsComponentDependencies
import com.example.feature_daily_weather_details.di.modules.DataBindModule
import com.example.feature_daily_weather_details.di.modules.DataModule
import com.example.feature_daily_weather_details.di.modules.DomainBindModule
import com.example.feature_daily_weather_details.di.modules.DomainModule
import com.example.feature_daily_weather_details.di.modules.UiBindModule
import com.example.feature_daily_weather_details.ui.screens.FragmentDailyWeatherDetails
import dagger.BindsInstance
import dagger.Component

@[FeatureDailyWeatherDetails Component(
    dependencies = [FeatureDailyWeatherDetailsComponentDependencies::class],
    modules = [
        UtilsModule::class,
        DomainModule::class,
        DataModule::class,
        DomainBindModule::class,
        DataBindModule::class,
        UiBindModule::class
    ]
)] internal interface FeatureDailyWeatherDetailsComponent {

    fun inject(fragment: FragmentDailyWeatherDetails)

    @Component.Builder interface Builder {

        fun dependencies(dependencies: FeatureDailyWeatherDetailsComponentDependencies): Builder

        @BindsInstance fun displayableItems(items: DisplayableItemsProvider): Builder

        @BindsInstance fun application(application: Application): Builder

        fun build(): FeatureDailyWeatherDetailsComponent
    }
}