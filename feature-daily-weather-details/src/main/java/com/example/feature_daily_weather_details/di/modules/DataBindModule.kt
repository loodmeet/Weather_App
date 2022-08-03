package com.example.feature_daily_weather_details.di.modules

import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.models.mappers.WeatherResponseToDailyWeatherListMapper
import com.example.feature_daily_weather_details.data.models.mappers.WeatherResponseToHourlyWeatherListMapper
import com.example.feature_daily_weather_details.di.annotations.FeatureDailyWeatherDetails
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.data.network.repository.NetworkRepository
import com.example.feature_daily_weather_details.data.storage.database.LocalDatabase
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyWeatherEntity
import com.example.feature_daily_weather_details.data.storage.models.entities.HourlyWeatherEntity
import com.example.feature_daily_weather_details.data.storage.models.mappers.DailyWeatherToEntityMapper
import com.example.feature_daily_weather_details.data.storage.models.mappers.HourlyWeatherToEntityMapper
import com.example.feature_daily_weather_details.data.storage.repository.StorageRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.time.LocalDate

@Module
internal interface DataBindModule {

    @[FeatureDailyWeatherDetails Binds] fun bindNetworkRepository(
        repository: NetworkRepository
    ): BaseNetworkRepository<WeatherResponse>

    @[FeatureDailyWeatherDetails Binds] fun bindStorageRepository(
        repository: StorageRepository
    ): BaseStorageRepository<WeatherResponse>

    @[FeatureDailyWeatherDetails Binds] fun bindWeatherResponseToDailyWeatherListMapper(
        mapper: WeatherResponseToDailyWeatherListMapper
    ): Mapper<@kotlin.jvm.JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<DailyWeather>>

    @[FeatureDailyWeatherDetails Binds] fun bindWeatherResponseToHourlyWeatherListMapper(
        mapper: WeatherResponseToHourlyWeatherListMapper
    ): Mapper<@JvmSuppressWildcards WeatherResponse, @JvmSuppressWildcards List<HourlyWeather>>

    @[FeatureDailyWeatherDetails Binds] fun bindHourlyWeatherToEntityMapper(
        mapper: HourlyWeatherToEntityMapper
    ): Mapper<Pair<HourlyWeather, LocalDate>, HourlyWeatherEntity>

    @[FeatureDailyWeatherDetails Binds] fun bindDailyWeatherToEntityMapper(
        mapper: DailyWeatherToEntityMapper
    ): Mapper<DailyWeather, DailyWeatherEntity>
}