package com.example.feature_daily_weather_details.di.modules

import com.example.feature_daily_weather_details.di.annotations.FeatureDailyWeatherDetails
import com.example.feature_daily_weather_details.ui.adapters_and_delegates.delegation_adapters.MainDelegationAdapter
import dagger.Binds
import dagger.Module

@Module internal interface UiBindModule {

    @[FeatureDailyWeatherDetails Binds] fun bindMainDelegationAdapter(
        hourlyWeatherDelegationAdapter: MainDelegationAdapter.Base
    ): MainDelegationAdapter
}