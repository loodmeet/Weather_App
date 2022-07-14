package com.example.feature_daily_weather_details.ui.delegation_adapters

import com.example.core.ui.DisplayableItem
import com.example.core.ui.DisplayableItemDelegationAdapter
import com.example.feature_daily_weather_details.ui.adapter_delegates.dailyWeatherAdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import javax.inject.Inject


internal interface MainDelegationAdapter : DisplayableItemDelegationAdapter {

    class Base @Inject constructor() : MainDelegationAdapter {

        override val adapter: ListDelegationAdapter<List<DisplayableItem>> =
            ListDelegationAdapter(
                dailyWeatherAdapterDelegate()
            )

    }


}