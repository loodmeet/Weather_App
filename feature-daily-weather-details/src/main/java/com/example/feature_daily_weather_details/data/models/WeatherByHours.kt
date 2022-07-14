package com.example.feature_daily_weather_details.data.models

import android.util.Log
import com.example.core.utils.Config.MAIN_TAG

class WeatherByHours(
    private val params: WeatherByHoursParams
) {

    fun getWeatherByHour(hour: Int): WeatherForHour =
        if (hour in 0..23) {
            WeatherForHour(
                weatherCode = params.weatherCode[hour],
                apparentTemperature = params.apparentTemperature[hour],
                windDirection10m = params.windDirection10m[hour],
                temperature2m = params.temperature2m[hour],
                precipitation = params.precipitation[hour],
                windSpeed10m = params.windSpeed10m[hour],
                relativeHumidity2m = params.relativeHumidity2m[hour],
                cloudCover = params.cloudCover[hour]
            )
        } else {
            Log.d(MAIN_TAG, "WeatherByHours.getWeatherByHour.err: out of range")
            WeatherForHour(
                weatherCode = 0,
                apparentTemperature = 0.0,
                windDirection10m = 0,
                temperature2m = 0.0,
                precipitation = 0.0,
                windSpeed10m = 0.0,
                relativeHumidity2m = 0,
                cloudCover = 0
            )
        }

}
