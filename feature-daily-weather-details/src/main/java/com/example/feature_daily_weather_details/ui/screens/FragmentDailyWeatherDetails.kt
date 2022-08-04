package com.example.feature_daily_weather_details.ui.screens

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.ui.BaseFragment
import com.example.core.utils.Config
import com.example.feature_daily_weather_details.databinding.FragmentDailyWeatherDetailsBinding
import com.example.feature_daily_weather_details.ui.delegation_adapters.MainDelegationAdapter
import com.example.feature_daily_weather_details.view_models.ComponentViewModel
import com.example.feature_daily_weather_details.view_models.MainViewModel
import dagger.Lazy
import javax.inject.Inject

class FragmentDailyWeatherDetails : BaseFragment<FragmentDailyWeatherDetailsBinding>() {

    private lateinit var componentViewModel: ComponentViewModel
    @Inject internal lateinit var mainViewModelFactory: Lazy<MainViewModel.Factory>
    private val mainViewModel: MainViewModel by viewModels { mainViewModelFactory.get() }
    @Inject internal lateinit var mainDelegationAdapter: MainDelegationAdapter

    override fun onAttach(context: Context) {
        val factory = ViewModelProvider.AndroidViewModelFactory
            .getInstance(context.applicationContext as Application)
        componentViewModel = ViewModelProvider(requireActivity(), factory)[ComponentViewModel::class.java]
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDailyWeatherDetailsBinding =
        FragmentDailyWeatherDetailsBinding.inflate(layoutInflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dailyDetailsRecycler.apply {
            adapter = mainDelegationAdapter.buildAdapter()
            layoutManager = LinearLayoutManager(context)
            overScrollMode = View.OVER_SCROLL_NEVER
        }

        mainViewModel.apply {
            observeDisplayableItems(owner = this@FragmentDailyWeatherDetails) { items ->
                mainDelegationAdapter.setItems(items = items)
            }

            observeDate(owner = this@FragmentDailyWeatherDetails) { item ->
                binding.selectedDate.text = item.date
            }
            fetchData(date = arguments?.getString(Config.DAY_DATE_KEY) ?: "")
        }
    }
}