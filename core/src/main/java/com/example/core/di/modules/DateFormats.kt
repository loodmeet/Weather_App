package com.example.core.di.modules

import com.example.core.di.annotation.DailyWeatherDateFormat
import com.example.core.di.annotation.HourlyWeatherDateFormat
import dagger.Module
import dagger.Provides
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Module
object DateFormats {

    @[Provides HourlyWeatherDateFormat]
    fun provideHourlyWeatherDateFormat(locale: Locale): DateFormat =
        SimpleDateFormat("HH:mm", locale)

    @[Provides DailyWeatherDateFormat]
    fun provideDailyWeatherDateFormat(locale: Locale): DateFormat =
        SimpleDateFormat("EE, d MMM", locale)
}
