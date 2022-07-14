package com.example.feature_main_screen.ui.adapters_and_delegates.delegation_adapters

import com.example.core.ui.DisplayableItem
import com.example.core.ui.DisplayableItemDelegationAdapter
import com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates.hourlyWeatherRecyclerItemAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject

internal interface HourlyWeatherDelegationAdapter : DisplayableItemDelegationAdapter {

    class Base @Inject constructor() : HourlyWeatherDelegationAdapter {

        override val adapter: ListDelegationAdapter<List<DisplayableItem>> =
            ListDelegationAdapter(hourlyWeatherRecyclerItemAdapterDelegate())
    }

}
