package com.example.core.di.modules

import dagger.Module
import dagger.Provides
import java.time.ZoneOffset
import java.util.*

@Module(
    includes = [
        UtilsBindModule::class,
        CoroutineContextModule::class,
        DateFormats::class
    ]
) class UtilsModule {

    @Provides fun provideCalendar(): Calendar = Calendar.getInstance()

    @Provides fun provideLocale(): Locale = Locale.getDefault()

    @Provides fun provideZoneOffset(): ZoneOffset = ZoneOffset.ofHours(3)
}