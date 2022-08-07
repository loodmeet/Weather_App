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
)
object UtilsModule {

    @Provides fun provideCalendar(): Calendar = Calendar.getInstance()

    // maybe use the application context?
    @Provides fun provideLocale(): Locale = Locale.CANADA

    @Provides fun provideZoneOffset(): ZoneOffset = ZoneOffset.ofHours(3)
}



