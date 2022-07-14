package com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates

import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.R
import com.example.feature_main_screen.domain.models.HourlyWeatherDisplayableItem
import com.example.feature_main_screen.databinding.HourlyWeatherRecyclerItemBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun hourlyWeatherRecyclerItemAdapterDelegate() =
    adapterDelegateViewBinding<HourlyWeatherDisplayableItem, DisplayableItem, HourlyWeatherRecyclerItemBinding>(
        viewBinding = { layoutInflater, root ->
            HourlyWeatherRecyclerItemBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            binding.apply {
                val degreeSign = context.getText(R.string.celsius).toString()
                time.text = item.time
                temperature.text = item.temperature.changeDegreeSign(degreeSign = degreeSign)
                    .getValueAsString()
                weatherIv.setImageResource(item.imageResId)
            }
        }
    }