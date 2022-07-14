package com.example.core.data.network.utils

object Config {

    const val DEFAULT_HOURLY_GET = "&hourly=temperature_2m,weathercode"
    const val DEFAULT_DAILY_GET = "&daily=weathercode,temperature_2m_max,temperature_2m_min"
    const val EUROPE_LONDON_TIMEZONE = "&timezone=Europe%2FLondon"
    const val DEFAULT_LAT_LON = "latitude=51.5002&longitude=-0.1262"
    const val FORECAST = "forecast?"
    const val SIMPLE_HOURLY = "&hourly=temperature_2m"
}