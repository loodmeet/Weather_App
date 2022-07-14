package com.example.feature_daily_weather_details.di.modules

import com.example.feature_daily_weather_details.data.network.models.responce.DailyWeatherResponse
import com.example.feature_daily_weather_details.data.network.models.responce.HourlyWeatherResponse
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import dagger.Module
import dagger.Provides
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Module object TestMainModule {
    @Provides fun provideWeatherResponse(): WeatherResponse {
        val dayNumber = 1
        val dayListSize = 7
        val hourListSize = 24 * dayListSize
        val intHourlyList = List(size = hourListSize) { 0 + it }
        val doubleHourlyList = List(size = hourListSize) { 0.1 + it }
        val intDailyList = List(size = dayListSize) { 0 + it }
        val doubleDailyList = List(size = dayListSize) { 0.1 + it }
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'mm:ss", Locale.CANADA)
        val hourlyWeatherCodeForDay = listOf(
            0, 1, 2, 3, 45, 51, 53, 55, 56, 57, 61, 63,
            66, 67, 71, 73, 75, 80, 81, 82, 85, 86, 96, 65
        )
        val hourlyWeatherCode = mutableListOf<Int>().apply {
            for (i in 0..dayListSize) addAll(hourlyWeatherCodeForDay)
        }
        val dailyWeatherCode = hourlyWeatherCode.subList(fromIndex = 0, toIndex = 7)

        val hourlyTime =
            List(size = hourListSize, init = { dateFormat.parse("2022-07-05T00:00")!! })
        val dailyTime = List(size = dayListSize, init = { dateFormat.parse("2022-07-05T00:00")!! })

        val hourlyWeather = HourlyWeatherResponse(
            weatherCode = hourlyWeatherCode,
            apparentTemperature = doubleHourlyList,
            windDirection10m = intHourlyList,
            temperature2m = doubleHourlyList,
            precipitation = doubleHourlyList,
            windSpeed10m = doubleHourlyList,
            time = hourlyTime,
            relativeHumidity2m = intHourlyList,
            cloudCover = intHourlyList
        )
        val dailyWeather = DailyWeatherResponse(
            precipitationHours = intDailyList,
            time = dailyTime,
            windSpeed10mMax = doubleDailyList,
            temperature2mMax = doubleDailyList,
            temperature2mMin = doubleDailyList,
            weatherCode = dailyWeatherCode,
            sunrise = dailyTime,
            sunset = dailyTime,
            precipitationSum = doubleDailyList
        )
        val utcOffsetSeconds = 1
        val latitude = 1.0
        val longitude = 1.0
        return WeatherResponse(
            hourly = hourlyWeather,
            utcOffsetSeconds = utcOffsetSeconds,
            latitude = latitude,
            longitude = longitude,
            daily = dailyWeather
        )
    }
//
//    @Provides fun provideLocale(): Locale = Locale.CANADA
//
//    @Provides fun provideDailyWeatherDateFormat(locale: Locale): DateFormat =
//        SimpleDateFormat("EE, d MMM", locale)

}