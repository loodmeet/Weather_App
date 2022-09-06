package com.example.feature_main_screen.ui.on_click_listeners

import android.os.Bundle
import androidx.navigation.NavController
import com.example.feature_main_screen.di.qualifiers.ActionToDailyWeatherDetails
import javax.inject.Inject

internal interface MoreButtonOnClickListenerProvider {

    fun get(args: Bundle, navController: NavController): MoreButtonOnClickListener

    class Base @Inject constructor(
        @param: ActionToDailyWeatherDetails private val actionId: Int
    ) : MoreButtonOnClickListenerProvider {
        override fun get(args: Bundle, navController: NavController) =
            MoreButtonOnClickListener.Base(
                navController = navController, actionId = actionId, args = args)
    }
}