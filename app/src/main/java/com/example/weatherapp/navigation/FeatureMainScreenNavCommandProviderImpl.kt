package com.example.weatherapp.navigation

import com.example.feature_main_screen.navigation.FeatureMainScreenNavCommandProvider
import com.example.weatherapp.R
import javax.inject.Inject

class FeatureMainScreenNavCommandProviderImpl @Inject constructor() :
    FeatureMainScreenNavCommandProvider {

    //    override fun toFragmentDailyWeatherDetails(
//        args: Bundle?, navOptions: NavOptions?
//    ): NavCommand = NavCommand(
//        action = R.id.action_fragmentMainScreen_to_fragmentDailyWeatherDetails,
//        args = args,
//        navOptions = navOptions
//    )
    override fun toFragmentDailyWeatherDetails(): Int =
        R.id.action_fragmentMainScreen_to_fragmentDailyWeatherDetails

}