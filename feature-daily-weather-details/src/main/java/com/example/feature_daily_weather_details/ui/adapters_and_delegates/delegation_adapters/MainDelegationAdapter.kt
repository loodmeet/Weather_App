package com.example.feature_daily_weather_details.ui.adapters_and_delegates.delegation_adapters

import com.example.core.ui.DisplayableItem
import com.example.core.ui.DisplayableItemDelegationAdapter
import com.example.feature_daily_weather_details.ui.adapters_and_delegates.adapter_delegates.dailyWeatherAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject

internal class MainDelegationAdapter @Inject constructor() : DisplayableItemDelegationAdapter {

    override val adapter: ListDelegationAdapter<List<DisplayableItem>> =
        ListDelegationAdapter(
            dailyWeatherAdapterDelegate()
        )
}
