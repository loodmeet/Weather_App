package com.example.feature_main_screen.di.modules

import com.example.core.di.modules.DateFormats
import com.example.core.di.modules.UtilsModule
import com.example.feature_main_screen.di.annotations.FeatureMainScreen
import com.example.feature_main_screen.di.annotations.ToFragmentDailyWeatherDetails
import com.example.feature_main_screen.di.dependencies.FeatureMainScreenComponentDependencies
import dagger.Module
import dagger.Provides

@Module(includes = [
    DomainBindModule::class,
    DataBindModule::class,
    UtilsModule::class,
    DataModule::class,
    DateFormats::class,
    UiBindModule::class
])
object FeatureMainModule {

    @[FeatureMainScreen ToFragmentDailyWeatherDetails Provides] fun provideToFragmentDailyWeatherDetailsResId(
        dependencies: FeatureMainScreenComponentDependencies
    ) = dependencies.featureMainScreenNavCommandProvider.toFragmentDailyWeatherDetails()
}