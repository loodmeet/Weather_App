package com.example.feature_daily_weather_details.data.network.models.responce

import com.google.gson.annotations.SerializedName
import java.util.*

data class DailyWeatherResponse(
    @SerializedName("precipitation_hours")
    val precipitationHours: List<Int>,
    @SerializedName("time")
    val time: List<Date>,
    @SerializedName("windspeed_10m_max")
    val windSpeed10mMax: List<Double>,
    @SerializedName("temperature_2m_max")
    val temperature2mMax: List<Double>,
    @SerializedName("weathercode")
    val weatherCode: List<Int>,
    @SerializedName("sunset")
    val sunset: List<Date>,
    @SerializedName("temperature_2m_min")
    val temperature2mMin: List<Double>,
    @SerializedName("sunrise")
    val sunrise: List<Date>,
    @SerializedName("precipitation_sum")
    val precipitationSum: List<Double>
)