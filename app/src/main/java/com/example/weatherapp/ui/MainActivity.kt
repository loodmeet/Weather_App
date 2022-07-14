package com.example.weatherapp.ui

import android.animation.LayoutTransition
import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.app.appComponent
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.view_models.MainActivityViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

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

                observeIsMenuButtonClicked(this@MainActivity) {  isClicked ->

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

}

