package com.example.feature_daily_weather_details.navigation

import android.os.Bundle
import androidx.navigation.NavOptions
import com.example.core.navigation.NavCommand


interface FeatureDailyWeatherDetailsNavCommandProvider {

    fun toFragmentDailyWeatherDetails(
        args: Bundle? = null, navOptions: NavOptions? = null
    ): NavCommand

}