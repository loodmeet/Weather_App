package com.example.feature_daily_weather_details.di.modules

import com.example.core.domain.use_case.UseCase
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DailyWeather
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.di.annotations.FeatureDailyWeatherDetails
import com.example.feature_daily_weather_details.data.repository.MainRepositoryImpl
import com.example.feature_daily_weather_details.domain.models.DomainModel
import com.example.feature_daily_weather_details.ui.models.SelectedDateDisplayableItem
import com.example.feature_daily_weather_details.ui.models.WeatherForTimeOfDay
import com.example.feature_daily_weather_details.domain.models.mappers.DailyWeatherToSelectedDateDisplayableItemMapper
import com.example.feature_daily_weather_details.domain.models.mappers.HourlyWeatherListToWeatherForTimeOfDayDisplayableItemMapper
import com.example.feature_daily_weather_details.domain.repository.MainRepository
import com.example.feature_daily_weather_details.domain.usecases.FetchWeatherByDateUseCase
import com.example.feature_daily_weather_details.ui.models.WeatherForTimeOfDayRecycler
import dagger.Binds
import dagger.Module

@Module internal interface DomainBindModule {

    @[FeatureDailyWeatherDetails Binds] fun bindMainRepository(
        repository: MainRepositoryImpl
    ): MainRepository

    @[FeatureDailyWeatherDetails Binds] fun bindWeatherForTimeOfDayMapper(
        mapper: HourlyWeatherListToWeatherForTimeOfDayDisplayableItemMapper
    ): Mapper<@JvmSuppressWildcards List<HourlyWeather>, WeatherForTimeOfDay>

    @[FeatureDailyWeatherDetails Binds] fun bindWeatherResponseToSelectedDateMapper(
        mapper: DailyWeatherToSelectedDateDisplayableItemMapper
    ): Mapper<@JvmSuppressWildcards DailyWeather, SelectedDateDisplayableItem>

    @[FeatureDailyWeatherDetails Binds] fun bindFetchWeatherByDateUseCase(
        useCase: FetchWeatherByDateUseCase<@JvmSuppressWildcards WeatherForTimeOfDayRecycler>
    ): UseCase<@JvmSuppressWildcards DomainModel, WeatherForTimeOfDayRecycler>
}