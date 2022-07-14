package com.example.feature_daily_weather_details.di.modules

import com.example.feature_daily_weather_details.di.annotations.FeatureDailyWeatherDetails
import com.example.feature_daily_weather_details.data.repository.MainRepositoryImpl
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import com.example.feature_daily_weather_details.domain.usecases.FetchSelectedDateUseCase
import com.example.feature_daily_weather_details.domain.usecases.FetchWeatherByDayNumberUseCase
import dagger.Binds
import dagger.Module

@Module
internal interface DomainBindModule {

    @[FeatureDailyWeatherDetails Binds] fun bindMainRepository(
        repository: MainRepositoryImpl
    ): MainRepository

    @[FeatureDailyWeatherDetails Binds] fun bindFetchDataUseCase(
        useCase: FetchWeatherByDayNumberUseCase.Base
    ): FetchWeatherByDayNumberUseCase

    @[FeatureDailyWeatherDetails Binds] fun bindFetchSelectedDateUseCase(
        useCase: FetchSelectedDateUseCase.Base
    ): FetchSelectedDateUseCase

}