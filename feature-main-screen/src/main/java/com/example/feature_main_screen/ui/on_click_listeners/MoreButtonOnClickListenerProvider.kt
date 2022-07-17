package com.example.feature_main_screen.ui.on_click_listeners

import android.os.Bundle
import androidx.navigation.NavController
import com.example.feature_main_screen.di.annotations.ToFragmentDailyWeatherDetails
import javax.inject.Inject

internal interface MoreButtonOnClickListenerProvider {

    fun get(args: Bundle, navController: NavController): MoreButtonOnClickListener

    class Base @Inject constructor(
        @ToFragmentDailyWeatherDetails private val resId: Int
    ) : MoreButtonOnClickListenerProvider {
        override fun get(args: Bundle, navController: NavController) =
            MoreButtonOnClickListener.Base(
                navController = navController, resId = resId, args = args)
    }
}