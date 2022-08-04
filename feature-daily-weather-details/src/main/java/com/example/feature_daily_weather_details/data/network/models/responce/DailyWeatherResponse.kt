package com.example.feature_daily_weather_details.data.network.models.responce

import com.google.gson.annotations.SerializedName
import java.util.*

data class DailyWeatherResponse(
    @SerializedName("time")
    val date: List<String>,
)