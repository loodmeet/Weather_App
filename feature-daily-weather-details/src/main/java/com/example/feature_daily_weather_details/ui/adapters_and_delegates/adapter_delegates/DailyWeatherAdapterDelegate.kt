package com.example.feature_daily_weather_details.ui.adapters_and_delegates.adapter_delegates

import android.annotation.SuppressLint
import com.example.core.ui.DisplayableItem
import com.example.feature_daily_weather_details.R
import com.example.feature_daily_weather_details.databinding.DailyWeatherRecyclerItemBinding
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

@SuppressLint("SetTextI18n") fun dailyWeatherAdapterDelegate() =
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
                relativeHumidityTv.text = item.relativeHumidity.toString() +
                        context.resources.getString(R.string.percentage_sign)
                temperatureTv.text = item.temperature.getValuesAsString(divider = "..")
                windSpeedTv.text = item.windSpeed.toString() + " " +
                        context.resources.getString(R.string.meters_per_second)
                weatherIv.setImageResource(item.imageResId)
                timeOfDayTv.text = context.resources.getString(item.timeOfDay.resId)
            }
        }
    }