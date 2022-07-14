package com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates

import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.R
import com.example.feature_main_screen.domain.models.HeaderDisplayableItem
import com.example.feature_main_screen.databinding.MainRecyclerItemHeaderBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun headerAdapterDelegate() =
    adapterDelegateViewBinding<HeaderDisplayableItem, DisplayableItem, MainRecyclerItemHeaderBinding>(
        viewBinding = { layoutInflater, root ->
            MainRecyclerItemHeaderBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            val degreeSign = context.getText(R.string.celsius)
            val divider = "/"
            val currentTemperature =  item.currentTemperature
                .changeDegreeSign(degreeSign = degreeSign.toString()).getValueAsString()
            val dailyTemperature = item.dailyTemperature
                .changeDegreeSign(degreeSign = degreeSign.toString())
                .getValuesAsString(divider = divider)

            binding.apply {
                currentTemp.text = currentTemperature
                dailyTemp.text = dailyTemperature
                weatherDescription.text = context.getText(item.weatherDescriptionResId)
            }
        }
    }