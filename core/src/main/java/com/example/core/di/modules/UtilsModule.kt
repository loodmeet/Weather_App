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

    // todo: maybe use the application context?
    @Provides fun provideLocale(): Locale = Locale.CANADA

    @Provides fun provideZoneOffset(): ZoneOffset = ZoneOffset.ofHours(3)
}