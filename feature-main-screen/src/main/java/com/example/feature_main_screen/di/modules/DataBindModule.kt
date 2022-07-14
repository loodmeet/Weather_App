package com.example.feature_main_screen.di.modules

import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.feature_main_screen.data.models.mapper.WeatherResponseToDailyWeatherMapper
import com.example.feature_main_screen.data.models.mapper.WeatherResponseToHeaderMapper
import com.example.feature_main_screen.data.models.mapper.WeatherResponseToHourlyWeatherMapper
import com.example.feature_main_screen.data.models.mapper.WeatherResponseToHourlyWeatherRecyclerMapper
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.feature_main_screen.data.network.repository.NetworkRepository
import com.example.feature_main_screen.data.storage.repository.StorageRepository
import com.example.feature_main_screen.di.annotations.FeatureMainScreen
import dagger.Binds
import dagger.Module


@Module
internal interface DataBindModule {

    @[FeatureMainScreen Binds] fun bindNetworkRepository(
        repository: NetworkRepository
    ): BaseNetworkRepository<WeatherResponse>

    @[FeatureMainScreen Binds] fun bindStorageRepository(
        repository: StorageRepository
    ): BaseStorageRepository<WeatherResponse>

    @[FeatureMainScreen Binds] fun bindWeatherResponseToHourlyWeatherMapper(
        mapper: WeatherResponseToHourlyWeatherMapper.Base
    ): WeatherResponseToHourlyWeatherMapper

    @[FeatureMainScreen Binds] fun bindDailyWeatherMapper(
        mapper: WeatherResponseToDailyWeatherMapper.Base
    ): WeatherResponseToDailyWeatherMapper

    @[FeatureMainScreen Binds] fun bindHourlyWeatherRecyclerMapper(
        mapper: WeatherResponseToHourlyWeatherRecyclerMapper.Base
    ): WeatherResponseToHourlyWeatherRecyclerMapper

    @[FeatureMainScreen Binds] fun bindHeaderMapper(
        mapper: WeatherResponseToHeaderMapper.Base
    ): WeatherResponseToHeaderMapper


}