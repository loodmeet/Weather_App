package com.example.feature_main_screen.ui.adapters_and_delegates.delegation_adapters

import androidx.navigation.NavController
import com.example.core.di.annotation.qualifiers.Horizontal
import com.example.core.di.annotation.qualifiers.Hourly
import com.example.core.ui.DisplayableItem
import com.example.core.ui.DisplayableItemDelegationAdapter
import com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates.*
import com.example.feature_main_screen.ui.adapters_and_delegates.layout_managers.LayoutManagerProvider
import com.example.feature_main_screen.ui.on_click_listeners.MoreButtonOnClickListener
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class MainDelegationAdapter @AssistedInject constructor(
    private val moreButtonOnClickListenerFactory: MoreButtonOnClickListener.Factory,
    @Horizontal private val layoutManagerProvider: LayoutManagerProvider,
    @Hourly private val hourlyDelegationAdapter: DisplayableItemDelegationAdapter,
    @Assisted("navController") private val navController: NavController
) : DisplayableItemDelegationAdapter {

    override val adapter: ListDelegationAdapter<List<DisplayableItem>> = ListDelegationAdapter(
        headerAdapterDelegate(),
        dailyWeatherAdapterDelegate(
            moreButtonOnClickListenerFactory = moreButtonOnClickListenerFactory,
            navController = navController
        ),
        hourlyWeatherRecyclerAdapterDelegate(
            adapter = hourlyDelegationAdapter.buildAdapter(),
            layoutManagerProvider = layoutManagerProvider
        ),
        moreButtonAdapterDelegate(
            moreButtonOnClickListenerFactory = moreButtonOnClickListenerFactory,
            navController = navController
        ),
        dividerAdapterDelegate(),
        updateDateAdapterDelegate()
    )

    @AssistedFactory interface Factory {
        fun create(@Assisted("navController") navController: NavController): MainDelegationAdapter
    }
}