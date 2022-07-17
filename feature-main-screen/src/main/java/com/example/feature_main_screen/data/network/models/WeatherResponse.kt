package com.example.feature_main_screen.data.network.models

import com.example.core.utils.Mapper
import com.google.gson.annotations.SerializedName
import java.util.*

internal data class WeatherResponse(
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







