package com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates

import android.os.Bundle
import androidx.navigation.NavController
import com.example.core.ui.DisplayableItem
import com.example.core.utils.Config
import com.example.feature_main_screen.databinding.MoreButtonBinding
import com.example.feature_main_screen.domain.models.MoreButtonDisplayableItem
import com.example.feature_main_screen.ui.on_click_listeners.MoreButtonOnClickListenerProvider
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun moreButtonAdapterDelegate(
    moreButtonOnClickListenerProvider: MoreButtonOnClickListenerProvider,
    navController: NavController
) =
    adapterDelegateViewBinding<MoreButtonDisplayableItem, DisplayableItem, MoreButtonBinding>(
        viewBinding = { layoutInflater, root ->
            MoreButtonBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            val args = Bundle().also { it.putString(Config.DAY_DATE_KEY, item.date) }
            binding.apply {
                moreButton.setOnClickListener(
                    moreButtonOnClickListenerProvider.get(
                        args = args,
                        navController = navController
                    )
                )
            }
        }
    }