package com.example.feature_main_screen.di.modules

import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.utils.Mapper
import com.example.feature_main_screen.data.models.DailyWeather
import com.example.feature_main_screen.data.models.HourlyWeather
import com.example.feature_main_screen.data.models.mappers.WeatherResponseToDailyWeatherListMapper
import com.example.feature_main_screen.data.models.mappers.WeatherResponseToHourlyWeatherListMapper
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.feature_main_screen.data.storage.repository.StorageRepository
import com.example.feature_main_screen.di.qualifiers.FeatureMainScreen
import dagger.Binds
import dagger.Module


@Module
internal interface DataBindModule {

    @[FeatureMainScreen Binds] fun bindStorageRepository(
        repository: StorageRepository
    ): BaseStorageRepository<WeatherResponse>


    @[FeatureMainScreen Binds] fun bindWeatherResponseToDailyWeatherListMapper(
        mapper: WeatherResponseToDailyWeatherListMapper
    ): Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<DailyWeather>>


    @[FeatureMainScreen Binds] fun bindWeatherResponseToHourlyWeatherListMapper(
        mapper: WeatherResponseToHourlyWeatherListMapper
    ): Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<HourlyWeather>>

}