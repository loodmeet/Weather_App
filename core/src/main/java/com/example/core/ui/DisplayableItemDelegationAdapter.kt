package com.example.core.ui

import android.annotation.SuppressLint
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

interface DisplayableItemDelegationAdapter : DelegationAdapter<DisplayableItem> {

    val adapter: ListDelegationAdapter<List<DisplayableItem>>

    override fun buildAdapter(): ListDelegationAdapter<List<DisplayableItem>> = adapter

    @SuppressLint("NotifyDataSetChanged") fun setItems(items: List<DisplayableItem>) {
        adapter.apply {
            this.items = items
            notifyDataSetChanged()
        }
    }
}