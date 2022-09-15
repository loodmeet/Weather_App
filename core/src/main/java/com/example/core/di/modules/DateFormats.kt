package com.example.core.di.modules

import com.example.core.di.annotation.qualifiers.Base
import com.example.core.di.annotation.qualifiers.Daily
import com.example.core.di.annotation.qualifiers.DailyBase
import com.example.core.di.annotation.qualifiers.Hourly
import dagger.Module
import dagger.Provides
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*

@Module class DateFormats {

    @[Provides Hourly] fun provideHourlyWeatherDateFormat(locale: Locale): DateTimeFormatter =
        DateTimeFormatter.ofPattern("HH:mm", locale)

    @[Provides Daily] fun provideDailyWeatherDateFormat(locale: Locale): DateTimeFormatter =
        DateTimeFormatterBuilder()
            .appendPattern("EEE, d MMM")
            .parseDefaulting(ChronoField.YEAR, LocalDate.now().year.toLong())
            .toFormatter(locale)

    @[Provides Base] fun provideBaseDateFormat(
        locale: Locale,
        zoneOffset: ZoneOffset
    ): DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", locale)

    @[Provides DailyBase] fun provideDailyBaseDateFormat(locale: Locale): DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd", locale)
}