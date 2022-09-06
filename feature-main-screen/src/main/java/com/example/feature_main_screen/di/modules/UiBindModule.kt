package com.example.feature_main_screen.di.modules

import com.example.core.di.annotation.Horizontal
import com.example.core.di.annotation.Vertical
import com.example.feature_main_screen.di.qualifiers.FeatureMainScreen
import com.example.feature_main_screen.ui.adapters_and_delegates.delegation_adapters.HourlyWeatherDelegationAdapter
import com.example.feature_main_screen.ui.adapters_and_delegates.layout_managers.LayoutManagerProvider
import com.example.feature_main_screen.ui.on_click_listeners.MoreButtonOnClickListenerProvider
import dagger.Binds
import dagger.Module

@Module
internal interface UiBindModule {

    @[FeatureMainScreen Binds Vertical] fun bindVerticalLayoutManagerProviderFactory(
        factory: LayoutManagerProvider.Vertical
    ): LayoutManagerProvider

    @[FeatureMainScreen Binds Horizontal] fun bindHorizontalLayoutManagerProviderFactory(
        factory: LayoutManagerProvider.Horizontal
    ): LayoutManagerProvider

    @[FeatureMainScreen Binds] fun bindHourlyWeatherDelegationAdapter(
        hourlyWeatherDelegationAdapter: HourlyWeatherDelegationAdapter.Base
    ): HourlyWeatherDelegationAdapter

    @[FeatureMainScreen Binds] fun bindDailyMoreButtonOnClickListenerProvider(
        moreButtonOnClickListenerProvider: MoreButtonOnClickListenerProvider.Base
    ): MoreButtonOnClickListenerProvider





}