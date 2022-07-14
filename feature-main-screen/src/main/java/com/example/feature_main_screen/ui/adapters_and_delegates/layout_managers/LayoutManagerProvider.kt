package com.example.feature_main_screen.ui.adapters_and_delegates.layout_managers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

interface LayoutManagerProvider {

    fun provideLayoutManager(recyclerView: RecyclerView): RecyclerView.LayoutManager

    class Vertical @Inject constructor() : LayoutManagerProvider {

        override fun provideLayoutManager(recyclerView: RecyclerView): RecyclerView.LayoutManager {
            return LinearLayoutManager(
                recyclerView.context, LinearLayoutManager.VERTICAL, false
            )
        }

    }

    class Horizontal @Inject constructor() : LayoutManagerProvider {

        override fun provideLayoutManager(recyclerView: RecyclerView): RecyclerView.LayoutManager {
            return LinearLayoutManager(
                recyclerView.context, LinearLayoutManager.HORIZONTAL, false
            )
        }

    }

}


