package com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates

import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.databinding.DataUpdateInfoBinding
import com.example.feature_main_screen.domain.models.UpdateDateDisplayableItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun updateDateAdapterDelegate() =
    adapterDelegateViewBinding<UpdateDateDisplayableItem, DisplayableItem, DataUpdateInfoBinding>(
        viewBinding = { layoutInflater, root ->
            DataUpdateInfoBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }

    ) {
        bind {
            binding.updateDate.text = "Updated: ${item.date}"
        }
    }