package com.example.feature_main_screen.di.dependencies

import kotlin.properties.Delegates.notNull

object FeatureStore : FeatureMainScreenDependenciesProvider {

    override var dependencies: FeatureMainScreenComponentDependencies by notNull()
}