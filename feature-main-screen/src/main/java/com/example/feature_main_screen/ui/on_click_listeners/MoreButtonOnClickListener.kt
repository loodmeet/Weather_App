package com.example.feature_main_screen.ui.on_click_listeners

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import com.example.feature_main_screen.di.qualifiers.ActionToDailyWeatherDetails
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class MoreButtonOnClickListener @AssistedInject constructor( // todo: REWRITE
    @param: ActionToDailyWeatherDetails private val actionId: Int,
    @Assisted("navController") private val navController: NavController,
    @Assisted("args") private val args: Bundle
) : View.OnClickListener {

    override fun onClick(p0: View?) {
        navController.navigate(resId = actionId, args = args)
    }

    @AssistedFactory interface Factory {
        fun create(
            @Assisted("navController") navController: NavController,
            @Assisted("args") args: Bundle,
        ): MoreButtonOnClickListener
    }
}