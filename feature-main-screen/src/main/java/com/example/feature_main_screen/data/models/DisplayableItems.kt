package com.example.feature_main_screen.data.models

import com.example.feature_main_screen.domain.models.DailyWeatherDisplayableItem
import com.example.feature_main_screen.domain.models.HeaderDisplayableItem
import com.example.feature_main_screen.domain.models.HourlyWeatherRecyclerDisplayableItem
import java.util.*

internal data class DisplayableItems(
    val currentWeather: HeaderDisplayableItem,
    val hourlyWeatherRecycler: HourlyWeatherRecyclerDisplayableItem,
    val dailyWeatherList: List<DailyWeatherDisplayableItem>
)