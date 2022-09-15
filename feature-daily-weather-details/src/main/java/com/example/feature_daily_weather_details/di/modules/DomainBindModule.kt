package com.example.feature_daily_weather_details.di.modules

import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.di.annotations.FeatureDailyWeatherDetails
import com.example.feature_daily_weather_details.data.repository.MainRepositoryImpl
import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.example.feature_daily_weather_details.domain.models.mappers.DailyWeatherToSelectedDateDisplayableItemMapper
import com.example.feature_daily_weather_details.domain.models.mappers.HourlyWeatherListToWeatherForTimeOfDayDisplayableItemMapper
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import dagger.Binds
import dagger.Module

@Module internal interface DomainBindModule {

    @[FeatureDailyWeatherDetails Binds] fun bindMainRepository(
        repository: MainRepositoryImpl
    ): MainRepository

    @[FeatureDailyWeatherDetails Binds] fun bindWeatherForTimeOfDayMapper(
        mapper: HourlyWeatherListToWeatherForTimeOfDayDisplayableItemMapper
    ): Mapper<@JvmSuppressWildcards List<HourlyWeather>, WeatherForTimeOfDayDisplayableItem>

    @[FeatureDailyWeatherDetails Binds] fun bindWeatherResponseToSelectedDateMapper(
        mapper: DailyWeatherToSelectedDateDisplayableItemMapper
    ): Mapper<@JvmSuppressWildcards DailyWeather, SelectedDateDisplayableItem>
}