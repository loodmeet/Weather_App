package com.example.feature_daily_weather_details.view_models

import androidx.lifecycle.ViewModel
import com.example.core.di.dependensies.DisplayableItemsProvider
import com.example.core.ui.DisplayableItem
import com.example.feature_daily_weather_details.di.component.DaggerFeatureDailyWeatherDetailsComponent
import com.example.feature_daily_weather_details.di.dependencies.FeatureDailyWeatherDetailsDependenciesProvider
import com.example.feature_daily_weather_details.domain.models.WeatherForTimeOfDayDisplayableItem
import kotlin.reflect.KClass

internal class ComponentViewModel : ViewModel() {

    val component = DaggerFeatureDailyWeatherDetailsComponent.builder()
        .dependencies(dependencies = FeatureDailyWeatherDetailsDependenciesProvider.dependencies)
        .displayableItems(items = DisplayableItemsProviderImpl)
        .build()

    object DisplayableItemsProviderImpl: DisplayableItemsProvider {
        override val items: Array<KClass<out DisplayableItem>> = arrayOf(
            WeatherForTimeOfDayDisplayableItem::class,
            WeatherForTimeOfDayDisplayableItem::class,
            WeatherForTimeOfDayDisplayableItem::class,
            WeatherForTimeOfDayDisplayableItem::class
        )
    }
}


