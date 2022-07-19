package com.example.feature_daily_weather_details.ui.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

    private val componentViewModel: ComponentViewModel by viewModels()
    @Inject internal lateinit var mainViewModelFactory: Lazy<MainViewModel.Factory>
    private val mainViewModel: MainViewModel by viewModels { mainViewModelFactory.get() }
    @Inject internal lateinit var mainDelegationAdapter: MainDelegationAdapter

    override fun onAttach(context: Context) {
        componentViewModel.component.inject(this)
        super.onAttach(context)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDailyWeatherDetailsBinding =
        FragmentDailyWeatherDetailsBinding.inflate(layoutInflater, container, false)

    // todo: delete
    override fun initLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(Config.MAIN_TAG, "DailyWeatherDetails screen onViewCreated")

        binding.dailyDetailsRecycler.apply {
            adapter = mainDelegationAdapter.buildAdapter()
            layoutManager = this@FragmentDailyWeatherDetails.layoutManager
            overScrollMode = View.OVER_SCROLL_NEVER
        }

        mainViewModel.apply {
            observeDisplayableItems(owner = this@FragmentDailyWeatherDetails) { items ->
                mainDelegationAdapter.setItems(items = items)
            }

            observeDate(owner = this@FragmentDailyWeatherDetails) { item ->
                binding.selectedDate.text = item.date
            }
            // todo
            fetchData(dayNumber = arguments?.getInt(Config.DAY_NUMBER_KEY) ?: 1)

        }
    }

}