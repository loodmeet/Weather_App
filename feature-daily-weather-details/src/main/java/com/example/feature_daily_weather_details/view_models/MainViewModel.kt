package com.example.feature_daily_weather_details.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.core.di.annotation.qualifiers.CoroutineContextDefault
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.core.di.dependensies.DisplayableItemsProvider
import com.example.core.domain.use_case.UseCase
import com.example.core.ui.DisplayableItem
import com.example.core.utils.Config
import com.example.core.utils.ItemsSortExecutor
import com.example.feature_daily_weather_details.domain.models.DomainModel
import com.example.feature_daily_weather_details.ui.models.SelectedDateDisplayableItem
import com.example.feature_daily_weather_details.domain.usecases.FetchSelectedDateUseCase
import com.example.feature_daily_weather_details.domain.usecases.FetchWeatherByDateUseCase
import com.example.feature_daily_weather_details.ui.models.WeatherForTimeOfDayRecycler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class MainViewModel(
    private val coroutineContext: CoroutineContext,
    private val fetchWeatherByDateUseCase: UseCase<@JvmSuppressWildcards  DomainModel, WeatherForTimeOfDayRecycler>,
    private val fetchSelectedDateUseCase: FetchSelectedDateUseCase,
) : ViewModel() { // todo: MVI

    private val _date = MutableLiveData<SelectedDateDisplayableItem>()
    private val _displayableItems = MutableLiveData<List<DisplayableItem>>()
    val dateLiveData: MutableLiveData<SelectedDateDisplayableItem> get() = _date
    val displayableItemsLiveData: MutableLiveData<List<DisplayableItem>> get() = _displayableItems

    fun fetchData(date: String) {
        CoroutineScope(coroutineContext).launch {
            _date.postValue(SelectedDateDisplayableItem(date = date))
            val dateResult = fetchSelectedDateUseCase.execute(date = date)

            dateResult.fold(
                onFailure = { exception -> Log.d(Config.MAIN_TAG, exception.stackTraceToString()) },
                onSuccess = { value ->
                    fetchWeatherByDateUseCase().fold(
                        onFailure = { exception ->
                            Log.d(
                                Config.MAIN_TAG,
                                exception.stackTraceToString()
                            )
                        },
                        onSuccess = { item ->
                            _displayableItems.postValue(item.items)
                        })
                }
            )
        }
    }

    internal class Factory @Inject constructor(
        @param: CoroutineContextDefault private val coroutineContext: CoroutineContext,
        private val fetchWeatherByDateUseCase: UseCase<@JvmSuppressWildcards DomainModel, WeatherForTimeOfDayRecycler>,
        private val fetchSelectedDateUseCase: FetchSelectedDateUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return MainViewModel(
                coroutineContext = coroutineContext,
                fetchWeatherByDateUseCase = fetchWeatherByDateUseCase,
                fetchSelectedDateUseCase = fetchSelectedDateUseCase
            ) as T
        }
    }

}