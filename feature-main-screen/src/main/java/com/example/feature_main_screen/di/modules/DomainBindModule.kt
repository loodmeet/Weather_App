package com.example.feature_main_screen.di.modules

import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.data.repository.MainRepositoryImpl
import com.example.feature_main_screen.di.qualifiers.FeatureMainScreen
import com.example.feature_main_screen.domain.models.DailyWeatherDisplayableItem
import com.example.feature_main_screen.domain.models.HeaderDisplayableItem
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import com.example.feature_main_screen.domain.models.HourlyWeatherRecyclerDisplayableItem
import com.example.feature_main_screen.domain.models.mappers.DailyAndHourlyWeatherToHeaderDisplayableItemMapper
import com.example.feature_main_screen.domain.models.mappers.DailyWeatherToDailyWeatherDisplayableItemMapper
import com.example.feature_main_screen.domain.models.mappers.HourlyWeatherToHourlyWeatherDisplayableItemMapper
import com.example.feature_main_screen.domain.models.mappers.HourlyWeatherToHourlyWeatherRecyclerDisplayableItemMapper
import com.example.feature_main_screen.domain.repository.MainRepository
import com.example.feature_main_screen.domain.use_cases.FetchDataUseCase
import dagger.Binds
import dagger.Module

@Module internal interface DomainBindModule {

    @[FeatureMainScreen Binds] fun bindMainRepository(
        repository: MainRepositoryImpl
    ): MainRepository

    @[FeatureMainScreen Binds] fun bindFetchDataUseCase(
        useCase: FetchDataUseCase.Base
    ): FetchDataUseCase

    @[FeatureMainScreen Binds] fun bindDailyWeatherAndHourlyWeatherToHeaderDisplayableItemMapper(
        mapper: DailyAndHourlyWeatherToHeaderDisplayableItemMapper
    ): Mapper<@JvmSuppressWildcards Pair<DailyWeather, HourlyWeather>, HeaderDisplayableItem>

    @[FeatureMainScreen Binds] fun bindDailyWeatherToDailyWeatherDisplayableItemMapper(
        mapper: DailyWeatherToDailyWeatherDisplayableItemMapper
    ): Mapper<@JvmSuppressWildcards DailyWeather, DailyWeatherDisplayableItem>

    @[FeatureMainScreen Binds] fun bindHourlyWeatherToHourlyWeatherDisplayableItemMapper(
        mapper: HourlyWeatherToHourlyWeatherDisplayableItemMapper
    ): Mapper<@JvmSuppressWildcards HourlyWeather, HourlyWeatherDisplayableItem>

    @[FeatureMainScreen Binds] fun bindHourlyWeatherToHourlyWeatherRecyclerDisplayableItemMapper(
        mapper: HourlyWeatherToHourlyWeatherRecyclerDisplayableItemMapper
    ): Mapper<@JvmSuppressWildcards List<HourlyWeather>, HourlyWeatherRecyclerDisplayableItem>

}