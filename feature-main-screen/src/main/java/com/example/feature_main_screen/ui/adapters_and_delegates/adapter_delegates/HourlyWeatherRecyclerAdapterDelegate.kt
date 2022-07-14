package com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.core.ui.DisplayableItem
import com.example.core.utils.Config
import com.example.feature_main_screen.databinding.HourlyWeatherRecyclerBinding
import com.example.feature_main_screen.domain.models.HourlyWeatherRecyclerDisplayableItem
import com.example.feature_main_screen.ui.adapters_and_delegates.layout_managers.LayoutManagerProvider
import com.example.feature_main_screen.ui.on_click_listeners.DailyMoreButtonOnClickListenerProvider
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


internal fun hourlyWeatherRecyclerAdapterDelegate(
    adapter: ListDelegationAdapter<List<DisplayableItem>>,
    layoutManagerProvider: LayoutManagerProvider,
    moreButtonOnClickListenerProvider: DailyMoreButtonOnClickListenerProvider,
    navController: NavController,
    dayNumber: Int
) =
    adapterDelegateViewBinding<HourlyWeatherRecyclerDisplayableItem,
            DisplayableItem, HourlyWeatherRecyclerBinding>(
        viewBinding = { layoutInflater, root ->
            HourlyWeatherRecyclerBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {
        bind {
            adapter.items = item.items
            val bundle = Bundle().also { it.putInt(Config.DAY_NUMBER_KEY, dayNumber) }

            binding.apply {
                recycler.apply {
                    this.adapter = adapter
                    layoutManager = layoutManagerProvider.provideLayoutManager(recyclerView = this)
                    this.overScrollMode = View.OVER_SCROLL_NEVER
                }
                moreButton.setOnClickListener(moreButtonOnClickListenerProvider
                    .get(args = bundle, navController = navController))
            }
        }
    }