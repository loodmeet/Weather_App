package com.example.feature_main_screen.data.network.retrofit

import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.feature_main_screen.data.network.utils.Config.DEFAULT_DAILY_GET
import com.example.feature_main_screen.data.network.utils.Config.DEFAULT_HOURLY_GET
import com.example.feature_main_screen.data.network.utils.Config.DEFAULT_LAT
import com.example.feature_main_screen.data.network.utils.Config.DEFAULT_LON
import com.example.feature_main_screen.data.network.utils.Config.VOLGOGRAD_TIMEZONE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherService {

    @GET("forecast")
    suspend fun executeByDefaultRequest(
        @Query("latitude") latitude: Double = DEFAULT_LAT,
        @Query("longitude") longitude: Double = DEFAULT_LON,
        @Query("hourly", encoded = true) hourly: String = DEFAULT_HOURLY_GET,
        @Query("daily", encoded = true) daily: String = DEFAULT_DAILY_GET,
        @Query("timezone", encoded = true) timezone: String = VOLGOGRAD_TIMEZONE
    ): Response<WeatherResponse>
}