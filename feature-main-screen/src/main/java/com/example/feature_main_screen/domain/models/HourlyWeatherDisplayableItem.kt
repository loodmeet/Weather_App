package com.example.feature_main_screen.domain.models

import com.example.core.data.models.Temperature
import com.example.core.ui.DisplayableItem

internal data class HourlyWeatherDisplayableItem(
    val weatherCode: Int,
    val time: String,
    val temperature: Temperature,
    val imageResId: Int
) : DisplayableItem