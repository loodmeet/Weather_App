package com.example.core.di.modules

import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.mappers.TranslatedWeatherToResourceMapper
import com.example.core.data.models.mappers.WeatherCodeToTranslatedWeatherMapper
import com.example.core.data.network.CallExecutor
import com.example.core.domain.models.TranslatedWeather
import com.example.core.utils.ItemsSortExecutor
import com.example.core.utils.Mapper
import dagger.Binds
import dagger.Module

@Module
interface UtilsBindModule {
    @Binds
    fun bindDateTimeProvider(
        provider: DateTimeProvider.Base
    ): DateTimeProvider

    @Binds
    fun bindTranslatedWeatherToResourceMapper(
        mapper: TranslatedWeatherToResourceMapper
    ): Mapper<@JvmSuppressWildcards Pair<TranslatedWeather, DateTimeProvider.TimeOfDay>, Int>

    @Binds
    fun bindWeatherCodeToTranslatedWeatherMapper(
        mapper: WeatherCodeToTranslatedWeatherMapper
    ): Mapper<@JvmSuppressWildcards Int, @JvmSuppressWildcards TranslatedWeather>

    @Binds
    fun bindCallExecutor(
        callExecutor: CallExecutor.Base
    ): CallExecutor

    @Binds
    fun bindItemsSortExecutor(
        itemsSortExecutor: ItemsSortExecutor.Base
    ): ItemsSortExecutor
}
