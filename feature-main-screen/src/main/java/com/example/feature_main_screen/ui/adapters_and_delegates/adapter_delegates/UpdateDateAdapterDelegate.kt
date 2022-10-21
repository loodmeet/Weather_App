package com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates

import android.annotation.SuppressLint
import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.DataUpdateInfoBinding
import com.example.feature_main_screen.domain.models.UpdateDateDisplayableItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

@SuppressLint("SetTextI18n")
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
            binding.updateDate.text = getString(R.string.updated) + " " + item.date
        }
    }