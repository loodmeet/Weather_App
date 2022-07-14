package com.example.feature_daily_weather_details.data.network.retrofit

import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.data.network.config.Config.DEFAULT_DAILY_GET
import com.example.feature_daily_weather_details.data.network.config.Config.DEFAULT_HOURLY_GET
import com.example.feature_daily_weather_details.data.network.config.Config.DEFAULT_LAT_LON
import com.example.feature_daily_weather_details.data.network.config.Config.EUROPE_LONDON_TIMEZONE
import com.example.feature_daily_weather_details.data.network.config.Config.FORECAST
import retrofit2.Call
import retrofit2.http.GET


internal interface WeatherService {

    // todo: @Query
    // todo: change params

    @GET("$FORECAST$DEFAULT_LAT_LON" +
            "$DEFAULT_HOURLY_GET$DEFAULT_DAILY_GET$EUROPE_LONDON_TIMEZONE")
    fun executeByDefaultRequest(): Call<WeatherResponse>

}
