package com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates

import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.databinding.DividerBinding
import com.example.feature_main_screen.domain.models.DividerDisplayableItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun dividerAdapterDelegate() =
    adapterDelegateViewBinding<DividerDisplayableItem, DisplayableItem, DividerBinding>(
        viewBinding = { layoutInflater, root ->
            DividerBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }

    ) {}
