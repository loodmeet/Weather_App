package com.example.feature_daily_weather_details.data.network.models.responce

import com.google.gson.annotations.SerializedName
import java.util.*

data class HourlyWeatherResponse(
    @SerializedName("weathercode")
    val weatherCode: List<Int>,
    @SerializedName("apparent_temperature")
    val apparentTemperature: List<Double>,
    @SerializedName("winddirection_10m")
    val windDirection10m: List<Int>,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    @SerializedName("precipitation")
    val precipitation: List<Double>,
    @SerializedName("windspeed_10m")
    val windSpeed10m: List<Double>,
    @SerializedName("time")
    val time: List<Date>,
    @SerializedName("relativehumidity_2m")
    val relativeHumidity2m: List<Int>,
    @SerializedName("cloudcover")
    val cloudCover: List<Int>
)