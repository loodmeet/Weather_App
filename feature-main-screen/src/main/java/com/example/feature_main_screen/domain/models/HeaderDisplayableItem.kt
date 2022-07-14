package com.example.feature_main_screen.domain.models

import com.example.core.data.models.Temperature
import com.example.core.data.models.TemperatureRange
import com.example.core.ui.DisplayableItem

internal data class HeaderDisplayableItem(
    val currentTemperature: Temperature,
    val dailyTemperature: TemperatureRange,
    val weatherDescriptionResId: Int,
    val imageResId: Int
) : DisplayableItem

