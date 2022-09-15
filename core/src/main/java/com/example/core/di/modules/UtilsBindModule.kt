package com.example.core.di.modules

import com.example.core.data.models.CurrentDateTimeProvider
import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.TimeOfDay
import com.example.core.data.models.WhatTimeOfDay
import com.example.core.data.models.mappers.TranslatedWeatherToResourceMapper
import com.example.core.data.models.mappers.WeatherCodeToTranslatedWeatherMapper
import com.example.core.domain.models.TranslatedWeather
import com.example.core.utils.Mapper
import dagger.Binds
import dagger.Module

@Module interface UtilsBindModule {

    @Binds fun bindWhatTimeOfDay(
        provider: DateTimeProvider
    ): WhatTimeOfDay

    @Binds fun bindCurrentDateTimeProvider(
        provider: DateTimeProvider
    ): CurrentDateTimeProvider

    @Binds fun bindTranslatedWeatherToResourceMapper(
        mapper: TranslatedWeatherToResourceMapper
    ): Mapper<@JvmSuppressWildcards Pair<TranslatedWeather, TimeOfDay>, Int>

    @Binds fun bindWeatherCodeToTranslatedWeatherMapper(
        mapper: WeatherCodeToTranslatedWeatherMapper
    ): Mapper<@JvmSuppressWildcards Int, @JvmSuppressWildcards TranslatedWeather>

}