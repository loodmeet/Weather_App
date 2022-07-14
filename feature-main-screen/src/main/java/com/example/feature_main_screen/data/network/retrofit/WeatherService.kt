package com.example.feature_main_screen.data.network.retrofit

import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.core.data.network.utils.Config.DEFAULT_DAILY_GET
import com.example.core.data.network.utils.Config.DEFAULT_HOURLY_GET
import com.example.core.data.network.utils.Config.DEFAULT_LAT_LON
import com.example.core.data.network.utils.Config.EUROPE_LONDON_TIMEZONE
import com.example.core.data.network.utils.Config.FORECAST
import retrofit2.Call
import retrofit2.http.GET

internal interface WeatherService {

    // todo: @Query

    @GET(
        "$FORECAST$DEFAULT_LAT_LON" +
                "$DEFAULT_HOURLY_GET$DEFAULT_DAILY_GET$EUROPE_LONDON_TIMEZONE"
    )
    fun executeByDefaultRequest(): Call<WeatherResponse>

}

