package com.example.feature_main_screen.ui.adapters_and_delegates.adapter_delegates

import android.view.View
import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.databinding.HourlyWeatherRecyclerBinding
import com.example.feature_main_screen.domain.models.HourlyWeatherRecyclerDisplayableItem
import com.example.feature_main_screen.ui.adapters_and_delegates.layout_managers.LayoutManagerProvider
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


internal fun hourlyWeatherRecyclerAdapterDelegate(
    adapter: ListDelegationAdapter<List<DisplayableItem>>,
    layoutManagerProvider: LayoutManagerProvider
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
            binding.apply {
                recycler.apply {
                    this.adapter = adapter
                    layoutManager = layoutManagerProvider.provideLayoutManager(recyclerView = this)
                    this.overScrollMode = View.OVER_SCROLL_NEVER
                }
            }
        }
    }