package com.example.weatherapp.ui.screens

import android.animation.LayoutTransition
import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherapp.R
import com.example.weatherapp.app.appComponent
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.ui.alert_dialogs.ExitAlertDialogProvider
import com.example.weatherapp.view_models.MainActivityViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = checkNotNull(_binding)
    private val viewModel: MainActivityViewModel by viewModels()
    @Inject internal lateinit var exitAlertDialogProvider: ExitAlertDialogProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(activity = this)
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        initNavController()

        binding.apply {
            root.layoutTransition.apply {
                enableTransitionType(LayoutTransition.CHANGING)
                setDuration(500)
            }
            val appBarLayoutTransition = appBarLayout.layoutTransition.apply {
                enableTransitionType(LayoutTransition.CHANGE_APPEARING)
                setDuration(500)
            }

            initViewModel(appBarLayoutTransition)

            mainSettingsButton.setOnClickListener { viewModel.changeIsSettingsButtonClickedState() }
            mainMenuButton.setOnClickListener { viewModel.changeIsMenuButtonClickedState() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViewModel(appBarLayoutTransition: LayoutTransition) = with(binding) {
        viewModel.apply {
            observeIsMenuButtonClicked(this@MainActivity) { isClicked ->
                appBarLayoutTransition.enableTransitionType(
                    if (isClicked) {
                        LayoutTransition.CHANGE_DISAPPEARING
                    } else {
                        LayoutTransition.CHANGE_APPEARING
                    }
                )
                mainMenuButton.setImageResource(
                    if (isClicked) {
                        R.drawable.avd_close_menu
                    } else {
                        R.drawable.avd_close_menu_reverse
                    }
                )

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
    }

    private fun initNavController() {
        (supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment)
            .navController.also { navController ->
                navController.setOnBackPressedDispatcher(onBackPressedDispatcher.also { dispatcher ->
                    dispatcher.addCallback(
                        this,
                        object : OnBackPressedCallback(true) {
                            override fun handleOnBackPressed() {
                                when (navController.currentDestination?.id) {
                                    R.id.fragmentMainScreen -> {
                                        exitAlertDialogProvider.provide(this@MainActivity) {
                                            finish()
                                        }.show()
                                    }
                                    R.id.fragmentDailyWeatherDetails -> {
                                        navController.popBackStack()
                                    }
                                }
                            }
                        }
                    )
                })
            }
    }
}