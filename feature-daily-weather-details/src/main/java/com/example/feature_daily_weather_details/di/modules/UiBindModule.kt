package com.example.feature_daily_weather_details.di.modules

import com.example.core.di.annotation.qualifiers.Main
import com.example.core.domain.use_case.UseCase
import com.example.core.ui.DisplayableItemDelegationAdapter
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.di.annotations.FeatureDailyWeatherDetails
import com.example.feature_daily_weather_details.domain.models.DomainModel
import com.example.feature_daily_weather_details.ui.adapters_and_delegates.delegation_adapters.MainDelegationAdapter
import com.example.feature_daily_weather_details.ui.models.WeatherForTimeOfDayRecycler
import com.example.feature_daily_weather_details.ui.models.mappers.DomainModelToWeatherForTimeOfDayRecyclerMapper
import dagger.Binds
import dagger.Module

@Module internal interface UiBindModule {

    @[FeatureDailyWeatherDetails Binds Main] fun bindHourlyDisplayableItemDelegationAdapter(
        adapter: MainDelegationAdapter
    ): DisplayableItemDelegationAdapter

    @[FeatureDailyWeatherDetails Binds] fun bindDomainModelToWeatherForTimeOfDayRecyclerMapper(
        mapper: DomainModelToWeatherForTimeOfDayRecyclerMapper
    ): Mapper<DomainModel, @JvmSuppressWildcards WeatherForTimeOfDayRecycler>
}