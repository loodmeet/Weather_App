package com.example.weatherapp.ui.screens

import android.animation.LayoutTransition
import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.example.core.utils.Config
import com.example.weatherapp.R
import com.example.weatherapp.app.appComponent
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.alert_dialogs.ExitAlertDialogProvider
import com.example.weatherapp.view_models.MainActivityViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = checkNotNull(_binding)
    private val viewModel by viewModels<MainActivityViewModel>()

    @Inject lateinit var exitAlertDialogProvider: ExitAlertDialogProvider

    override fun onBackPressed() {
        when (findNavController(binding.navHostFragment.id).currentDestination?.id) {
            R.id.fragmentMainScreen -> {
                exitAlertDialogProvider.provide(this) {
                    super.onBackPressed()
                }.show()
            }
            R.id.fragmentDailyWeatherDetails -> {
                super.onBackPressed()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        with(binding) {

            root.layoutTransition.apply {
                enableTransitionType(LayoutTransition.CHANGING)
                setDuration(500)
            }
            val appBarLayoutTransition = appBarLayout.layoutTransition.apply {
                enableTransitionType(LayoutTransition.CHANGE_APPEARING)
                setDuration(500)
            }

            viewModel.apply {

                observeIsMenuButtonClicked(this@MainActivity) { isClicked ->

                    if (isClicked) {
                        appBarLayoutTransition
                            .enableTransitionType(LayoutTransition.CHANGE_DISAPPEARING)
                        mainMenuButton.setImageResource(R.drawable.avd_close_menu)
                    } else {
                        appBarLayoutTransition
                            .enableTransitionType(LayoutTransition.CHANGE_APPEARING)
                        mainMenuButton.setImageResource(R.drawable.avd_close_menu_reverse)
                    }

                    (mainMenuButton.drawable as AnimatedVectorDrawable).start()

                }

                observeIsMenuVisible(this@MainActivity) { isVisible ->
                    layoutMenu.visibility = if (isVisible) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }

                observeIsSettingsButtonClicked(this@MainActivity) { isClicked ->
                    mainSettingsButton.apply {
                        visibility = if (isClicked) {
                            View.INVISIBLE
                        } else {
                            View.VISIBLE
                        }
                        isClickable = !isClicked
                    }
                }

                observeIsSettingsVisible(this@MainActivity) { isVisible ->
                    layoutSettings.visibility = if (isVisible) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                }
            }

            mainSettingsButton.setOnClickListener { viewModel.changeIsSettingsButtonClickedState() }
            mainMenuButton.setOnClickListener { viewModel.changeIsMenuButtonClickedState() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}

