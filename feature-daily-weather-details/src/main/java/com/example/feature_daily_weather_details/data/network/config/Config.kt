package com.example.feature_daily_weather_details.data.network.config

object Config {

   internal const val DEFAULT_HOURLY_GET = "&hourly=temperature_2m,apparent_temperature,precipitation," +
           "cloudcover,windspeed_10m,winddirection_10m,relativehumidity_2m,weathercode"
   internal const val DEFAULT_DAILY_GET = "&daily=weathercode,temperature_2m_max,temperature_2m_min" +
           ",sunrise,sunset,precipitation_sum,precipitation_hours,windspeed_10m_max"
   internal const val EUROPE_LONDON_TIMEZONE = "&timezone=Europe%2FLondon"
   internal const val DEFAULT_LAT_LON = "latitude=51.5002&longitude=-0.1262"
   internal const val FORECAST = "forecast?"
   internal const val SIMPLE_HOURLY = "&hourly=temperature_2m"
}