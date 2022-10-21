package com.example.feature_daily_weather_details.view_models

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.core.di.dependensies.DisplayableItemsProvider
import com.example.core.ui.DisplayableItem
import com.example.core.utils.Config
import com.example.feature_daily_weather_details.data.storage.database.LocalDatabase
import com.example.feature_daily_weather_details.di.component.DaggerFeatureDailyWeatherDetailsComponent
import com.example.feature_daily_weather_details.di.dependencies.FeatureDailyWeatherDetailsDependenciesProvider
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

internal class ComponentViewModel(application: Application) : AndroidViewModel(application) {

//    private val database = LocalDatabase.getLocalDatabase(application = application)
//
//    init {
//        CoroutineScope(Dispatchers.IO).launch { database.clearAllTables() }
//    }

    val component = DaggerFeatureDailyWeatherDetailsComponent.builder()
        .dependencies(dependencies = FeatureDailyWeatherDetailsDependenciesProvider.dependencies)
        .displayableItems(items = DisplayableItemsProviderImpl)
        .application(application = application)
        .build()

    object DisplayableItemsProviderImpl : DisplayableItemsProvider {
        override val items: Array<KClass<out DisplayableItem>> = arrayOf(
            WeatherForTimeOfDayDisplayableItem::class,
            WeatherForTimeOfDayDisplayableItem::class,
            WeatherForTimeOfDayDisplayableItem::class,
            WeatherForTimeOfDayDisplayableItem::class
        )
    }
}