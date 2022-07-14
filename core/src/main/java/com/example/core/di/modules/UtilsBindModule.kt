package com.example.core.di.modules

import com.example.core.data.models.DateTimeProvider
import com.example.core.data.models.mappers.TranslatedWeatherToResourceMapper
import com.example.core.data.models.mappers.WeatherCodeToTranslatedWeatherMapper
import com.example.core.data.network.CallExecutor
import com.example.core.utils.ItemsSortExecutor
import dagger.Binds
import dagger.Module

@Module
interface UtilsBindModule {
    @Binds
    fun bindDateTimeProvider(
        mapper: DateTimeProvider.Base
    ): DateTimeProvider

    @Binds
    fun bindTranslatedWeatherToResourceMapper(
        mapper: TranslatedWeatherToResourceMapper.Base
    ): TranslatedWeatherToResourceMapper

    @Binds
    fun bindWeatherCodeToTranslatedWeatherMapper(
        mapper: WeatherCodeToTranslatedWeatherMapper.Base
    ): WeatherCodeToTranslatedWeatherMapper

    @Binds
    fun bindCallExecutor(
        callExecutor: CallExecutor.Base
    ): CallExecutor

    @Binds
    fun bindItemsSortExecutor(
        itemsSortExecutor: ItemsSortExecutor.Base
    ): ItemsSortExecutor
}
