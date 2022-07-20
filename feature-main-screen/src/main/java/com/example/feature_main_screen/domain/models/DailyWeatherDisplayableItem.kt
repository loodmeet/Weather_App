package com.example.feature_main_screen.domain.models

import com.example.core.data.models.TemperatureRange
import com.example.core.ui.DisplayableItem
import java.util.*

internal data class DailyWeatherDisplayableItem(
    val weatherCode: Int,
    val temperature: TemperatureRange,
    val date: String,
    val imageResId: Int
) : DisplayableItem