package com.example.feature_main_screen.domain.models

import com.example.core.ui.DisplayableItem

internal data class HourlyWeatherRecyclerDisplayableItem(
    val items: List<HourlyWeatherDisplayableItem>
) : DisplayableItem
