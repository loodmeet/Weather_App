package com.example.feature_main_screen.di.component

import com.example.core.di.dependensies.DisplayableItemsProvider
import com.example.feature_main_screen.di.annotations.FeatureMainScreen
import com.example.feature_main_screen.di.dependencies.FeatureMainScreenComponentDependencies
import com.example.feature_main_screen.di.modules.*
import com.example.feature_main_screen.ui.screens.FragmentMainScreen
import dagger.*

@[FeatureMainScreen Component(
    dependencies = [
        FeatureMainScreenComponentDependencies::class,
    ],
    modules = [FeatureMainModule::class]
)]
internal interface FeatureMainScreenComponent {

    fun inject(fragment: FragmentMainScreen)

    @Component.Builder interface Builder {

        fun dependencies(dependencies: FeatureMainScreenComponentDependencies): Builder

        @BindsInstance fun displayableItems(items: DisplayableItemsProvider): Builder

        fun build(): FeatureMainScreenComponent
    }

}



















