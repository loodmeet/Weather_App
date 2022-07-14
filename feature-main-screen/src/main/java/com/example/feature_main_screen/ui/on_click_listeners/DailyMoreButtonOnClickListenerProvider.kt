package com.example.feature_main_screen.ui.on_click_listeners

import android.os.Bundle
import androidx.navigation.NavController
import com.example.feature_main_screen.di.annotations.ToFragmentDailyWeatherDetails
import javax.inject.Inject
import javax.inject.Provider

internal interface DailyMoreButtonOnClickListenerProvider :
    Provider<DailyMoreButtonOnClickListener> {

    fun get(args: Bundle, navController: NavController): DailyMoreButtonOnClickListener

    override fun get(): DailyMoreButtonOnClickListener {
        throw RuntimeException()
    }

    class Base @Inject constructor(
        @ToFragmentDailyWeatherDetails private val resId: Int
    ) : DailyMoreButtonOnClickListenerProvider {
        override fun get(args: Bundle, navController: NavController) =
            DailyMoreButtonOnClickListener.Base(
                navController = navController, resId = resId, args = args)
    }
}