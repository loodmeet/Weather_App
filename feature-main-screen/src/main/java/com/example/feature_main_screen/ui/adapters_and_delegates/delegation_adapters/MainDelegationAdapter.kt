package com.example.feature_main_screen.ui.adapters_and_delegates.delegation_adapters

import androidx.navigation.NavController
import com.example.core.di.annotation.Horizontal
import com.example.core.ui.DisplayableItem
import com.example.core.ui.DisplayableItemDelegationAdapter
import com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates.*
import com.example.feature_main_screen.ui.adapters_and_delegates.layout_managers.LayoutManagerProvider
import com.example.feature_main_screen.ui.on_click_listeners.MoreButtonOnClickListenerProvider
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal interface MainDelegationAdapter : DisplayableItemDelegationAdapter {

    class Base @AssistedInject constructor(
        private val moreButtonOnClickListenerProvider: MoreButtonOnClickListenerProvider,
        @Horizontal private val layoutManagerProvider: LayoutManagerProvider,
        private val hourlyDelegationAdapter: HourlyWeatherDelegationAdapter,
        @Assisted("navController") private val navController: NavController
    ) : MainDelegationAdapter {

        override val adapter: ListDelegationAdapter<List<DisplayableItem>> = ListDelegationAdapter(
            headerAdapterDelegate(),
            dailyWeatherAdapterDelegate(
                moreButtonOnClickerProvider = moreButtonOnClickListenerProvider,
                navController = navController
            ),
            hourlyWeatherRecyclerAdapterDelegate(
                adapter = hourlyDelegationAdapter.buildAdapter(),
                layoutManagerProvider = layoutManagerProvider,
                moreButtonOnClickListenerProvider = moreButtonOnClickListenerProvider,
                navController = navController,
                dayNumber = 0
            ),
            dividerAdapterDelegate(),
            updateDateAdapterDelegate()
        )


    }

    @AssistedFactory interface Factory {
        fun create(@Assisted("navController") navController: NavController): Base
    }


}
