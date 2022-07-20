package com.example.feature_daily_weather_details.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.annotation.CoroutineContextIO
import com.example.core.di.dependensies.DisplayableItemsProvider
import com.example.core.ui.DisplayableItem
import com.example.core.utils.Config
import com.example.core.utils.ItemsSortExecutor
import com.example.feature_daily_weather_details.domain.models.SelectedDateDisplayableItem
import com.example.feature_daily_weather_details.domain.usecases.FetchSelectedDateUseCase
import com.example.feature_daily_weather_details.domain.usecases.FetchWeatherByDayUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainViewModel(
    private val coroutineContext: CoroutineContext,
    private val itemsSortExecutor: ItemsSortExecutor,
    private val displayableItemsArray: DisplayableItemsProvider,
    private val fetchWeatherByDayUseCase: FetchWeatherByDayUseCase,
    private val fetchSelectedDateUseCase: FetchSelectedDateUseCase,
) : ViewModel() {

    private val date = MutableLiveData<SelectedDateDisplayableItem>()
    private val displayableItems = MutableLiveData<List<DisplayableItem>>()

    fun observeDisplayableItems(owner: LifecycleOwner, observer: Observer<List<DisplayableItem>>) {
        displayableItems.observe(owner, observer)
    }

    fun observeDate(owner: LifecycleOwner, observer: Observer<SelectedDateDisplayableItem>) {
        date.observe(owner, observer)
    }

    fun fetchData(date: String) {
        CoroutineScope(coroutineContext).launch {
            this@MainViewModel.date.postValue(SelectedDateDisplayableItem(date = date))
            val dateResult = fetchSelectedDateUseCase.execute(date = date)

            dateResult.fold(
                onFailure = { exception -> Log.d(Config.MAIN_TAG, exception.stackTraceToString()) },
                onSuccess = { value ->
                    fetchWeatherByDayUseCase.execute(date = value).fold(
                        onFailure = { exception ->
                            Log.d(
                                Config.MAIN_TAG,
                                exception.stackTraceToString()
                            )
                        },
                        onSuccess = { items ->
                            val sortedItems = itemsSortExecutor.sortByRule(
                                items = items.toMutableList(),
                                rule = displayableItemsArray.items
                            )
                            sortedItems.fold(
                                onSuccess = { value -> displayableItems.postValue(value) },
                                onFailure = { exception ->
                                    Log.d(
                                        Config.MAIN_TAG,
                                        exception.stackTraceToString()
                                    )
                                }
                            )
                        })
                }
            )
        }
    }

    internal class Factory @Inject constructor(
        @param: CoroutineContextIO private val coroutineContext: CoroutineContext,
        private val itemsSortExecutor: ItemsSortExecutor,
        private val displayableItemsArray: DisplayableItemsProvider,
        private val fetchWeatherByDayUseCase: FetchWeatherByDayUseCase,
        private val fetchSelectedDateUseCase: FetchSelectedDateUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return MainViewModel(
                coroutineContext = coroutineContext,
                displayableItemsArray = displayableItemsArray,
                itemsSortExecutor = itemsSortExecutor,
                fetchWeatherByDayUseCase = fetchWeatherByDayUseCase,
                fetchSelectedDateUseCase = fetchSelectedDateUseCase
            ) as T
        }
    }

}



