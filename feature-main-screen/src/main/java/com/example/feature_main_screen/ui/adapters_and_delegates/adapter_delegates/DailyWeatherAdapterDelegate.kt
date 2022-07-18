package com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates

import android.os.Bundle
import androidx.navigation.NavController
import com.example.core.ui.DisplayableItem
import com.example.core.utils.Config
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.ItemMainScreenLayoutBinding
import com.example.feature_main_screen.domain.models.DailyWeatherDisplayableItem
import com.example.feature_main_screen.ui.on_click_listeners.MoreButtonOnClickListenerProvider
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import java.lang.RuntimeException


internal fun dailyWeatherAdapterDelegate(
    moreButtonOnClickerProvider: MoreButtonOnClickListenerProvider,
    navController: NavController
) = adapterDelegateViewBinding<DailyWeatherDisplayableItem, DisplayableItem, ItemMainScreenLayoutBinding>(
        viewBinding = { layoutInflater, root ->
            ItemMainScreenLayoutBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            binding.apply {
                val degreeSign = context.getText(R.string.celsius)
                val divider = "/"
                val bundle = Bundle().also { it.putString(Config.DAY_DATE_KEY, item.date) }
                date.text = item.date
                dailyTemp.text = item.temperature
                    .changeDegreeSign(degreeSign = degreeSign.toString())
                    .getValuesAsString(divider = divider)
                weatherIv.setImageResource(item.imageResId)

//                moreArrow.tag = item.dayNumber
                moreArrow.setOnClickListener(moreButtonOnClickerProvider
                    .get(args = bundle, navController = navController))
            }

        }
    }