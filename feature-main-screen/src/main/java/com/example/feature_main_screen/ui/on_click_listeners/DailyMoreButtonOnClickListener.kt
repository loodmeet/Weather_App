package com.example.feature_main_screen.ui.on_click_listeners

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.core.utils.Config
import dagger.Binds
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Qualifier


// todo: rename
internal interface DailyMoreButtonOnClickListener : View.OnClickListener {

    class Base constructor(
        private val navController: NavController,
        private val resId: Int,
        private val args: Bundle
    ) : DailyMoreButtonOnClickListener {

        override fun onClick(p0: View?) {
            navController.navigate(resId = resId, args = args)
        }

    }
}


