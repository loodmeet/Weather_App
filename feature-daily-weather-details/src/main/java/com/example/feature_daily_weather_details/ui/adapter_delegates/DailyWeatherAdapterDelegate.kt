package com.example.feature_daily_weather_details.ui.adapter_delegates

import com.example.core.ui.DisplayableItem
import com.example.feature_daily_weather_details.databinding.DailyWeatherRecyclerItemBinding
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun dailyWeatherAdapterDelegate() =
    adapterDelegateViewBinding<WeatherForTimeOfDayDisplayableItem, DisplayableItem, DailyWeatherRecyclerItemBinding>(
        viewBinding = { layoutInflater, root ->
            DailyWeatherRecyclerItemBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            binding.apply {
                apparentTemperature.text = item.apparentTemperature.getValueAsString()
                precipitationTv.text = item.precipitation.toString()
                temperatureTv.text = item.temperature.getValuesAsString(divider = "..")
                windSpeedTv.text = item.windSpeed.toString()
                weatherIv.setImageResource(item.imageResId)
                timeOfDayTv.text = item.timeOfDay.toString()
            }
        }
    }