package com.example.feature_main_screen.view_models

import androidx.lifecycle.ViewModel
import com.example.core.di.dependensies.DisplayableItemsProvider
import com.example.core.ui.DisplayableItem
import com.example.feature_main_screen.di.component.DaggerFeatureMainScreenComponent
import com.example.feature_main_screen.di.dependencies.FeatureMainScreenDependenciesProvider
import com.example.feature_main_screen.domain.models.*
import com.example.feature_main_screen.domain.models.DailyWeatherDisplayableItem
import com.example.feature_main_screen.domain.models.DividerDisplayableItem
import com.example.feature_main_screen.domain.models.HeaderDisplayableItem
import com.example.feature_main_screen.domain.models.HourlyWeatherRecyclerDisplayableItem
import com.example.feature_main_screen.domain.models.MoreButtonDisplayableItem
import com.example.feature_main_screen.domain.models.UpdateDateDisplayableItem
import kotlin.reflect.KClass

internal class ComponentViewModel : ViewModel() {

    val component = DaggerFeatureMainScreenComponent.builder()
        .dependencies(dependencies = FeatureMainScreenDependenciesProvider.dependencies)
        .displayableItems(items = DisplayableItemsProviderImpl)
        .build()

    object DisplayableItemsProviderImpl: DisplayableItemsProvider {
        override val items: Array<KClass<out DisplayableItem>> = arrayOf(
            HeaderDisplayableItem::class,
            UpdateDateDisplayableItem::class,
            DividerDisplayableItem::class,
            HourlyWeatherRecyclerDisplayableItem::class,
            MoreButtonDisplayableItem::class,
            DailyWeatherDisplayableItem::class,
            DailyWeatherDisplayableItem::class,
            DailyWeatherDisplayableItem::class,
            DailyWeatherDisplayableItem::class,
            DailyWeatherDisplayableItem::class,
            DailyWeatherDisplayableItem::class
        )
    }
}

