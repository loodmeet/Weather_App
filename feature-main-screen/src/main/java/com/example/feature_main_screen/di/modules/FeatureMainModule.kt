package com.example.feature_main_screen.di.modules

import com.example.core.di.modules.DateFormats
import com.example.core.di.modules.UtilsModule
import com.example.feature_main_screen.di.dependencies.FeatureMainScreenComponentDependencies
import com.example.feature_main_screen.di.qualifiers.ActionToDailyWeatherDetails
import com.example.feature_main_screen.di.qualifiers.FeatureMainScreen
import dagger.Module
import dagger.Provides

@Module(includes = [
    DomainBindModule::class,
    DataBindModule::class,
    UtilsModule::class,
    DataModule::class,
    DateFormats::class,
    UiBindModule::class
]) internal class FeatureMainModule {

    @[FeatureMainScreen Provides ActionToDailyWeatherDetails] fun provideActionToDailyWeatherDetails(
        dependencies: FeatureMainScreenComponentDependencies
    ) = dependencies.actionToFragmentDailyWeatherDetails
}