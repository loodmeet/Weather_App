package com.example.feature_daily_weather_details.data.network.models.responce

import android.util.Log
import com.example.core.utils.Config
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("hourly")
    val hourly: HourlyWeatherResponse,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("daily")
    val daily: DailyWeatherResponse
)
