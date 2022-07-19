package com.example.feature_main_screen.data.network.models

import com.google.gson.annotations.SerializedName

internal data class HourlyWeatherResponse(
    @SerializedName("weathercode")
    val weatherCode: List<Int>,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
//    @SerializedName("precipitation")
//    val precipitation: List<Double>,
    @SerializedName("time")
    val time: List<String>,
//    @SerializedName("cloudcover")
//    val cloudCover: List<Int>
)