package com.example.feature_daily_weather_details.di.modules

import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.feature_daily_weather_details.di.annotations.FeatureDailyWeatherDetails
import com.example.feature_daily_weather_details.data.models.mapper.WeatherResponseToSelectedDateMapper
import com.example.feature_daily_weather_details.data.models.mapper.WeatherResponseToWeatherForTimeOfDayMapper
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.data.network.repository.NetworkRepository
import com.example.feature_daily_weather_details.data.storage.repository.StorageRepository
import dagger.Binds
import dagger.Module

@Module
internal interface DataBindModule {

    @[FeatureDailyWeatherDetails Binds] fun bindNetworkRepository(
        repository: NetworkRepository
    ): BaseNetworkRepository<WeatherResponse>

    @[FeatureDailyWeatherDetails Binds] fun bindStorageRepository(
        repository: StorageRepository
    ): BaseStorageRepository<WeatherResponse>

    @[FeatureDailyWeatherDetails Binds] fun bindWeatherForTimeOfDayMapper(
        repository: WeatherResponseToWeatherForTimeOfDayMapper.Base
    ): WeatherResponseToWeatherForTimeOfDayMapper

    @[FeatureDailyWeatherDetails Binds] fun bindWeatherResponseToSelectedDateMapper(
        repository: WeatherResponseToSelectedDateMapper.Base
    ): WeatherResponseToSelectedDateMapper




}