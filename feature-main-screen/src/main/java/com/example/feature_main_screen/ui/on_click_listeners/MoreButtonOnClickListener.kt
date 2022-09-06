package com.example.feature_main_screen.ui.on_click_listeners

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController

internal interface MoreButtonOnClickListener : View.OnClickListener {

    class Base constructor(
        private val navController: NavController,
        private val actionId: Int,
        private val args: Bundle
    ) : MoreButtonOnClickListener {

        override fun onClick(p0: View?) {
            navController.navigate(resId = actionId, args = args)
        }
    }
}


