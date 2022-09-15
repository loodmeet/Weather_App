package com.example.feature_daily_weather_details.data.network.utils

internal object Config {

    const val DEFAULT_HOURLY_GET = "temperature_2m,apparent_temperature,precipitation," +
            "cloudcover,windspeed_10m,winddirection_10m,relativehumidity_2m,weathercode"
    const val DEFAULT_DAILY_GET = "weathercode,temperature_2m_max,temperature_2m_min" +
            ",sunrise,sunset,precipitation_sum,precipitation_hours,windspeed_10m_max"
    const val VOLGOGRAD_TIMEZONE = "Europe%2FVolgograd"
    const val DEFAULT_LAT = 48.7194
    const val DEFAULT_LON = 44.5018
}
