package com.example.feature_main_screen.data.network.models

import com.google.gson.annotations.SerializedName
import java.util.Date

internal data class DailyWeatherResponse(
    @SerializedName("time")
    val time: List<Date>,
    @SerializedName("temperature_2m_max")
    val temperature2mMax: List<Double>,
    @SerializedName("weathercode")
    val weatherCode: List<Int>,
    @SerializedName("temperature_2m_min")
    val temperature2mMin: List<Double>
)