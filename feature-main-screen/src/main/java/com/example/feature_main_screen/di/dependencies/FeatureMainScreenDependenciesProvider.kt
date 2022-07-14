package com.example.feature_main_screen.di.dependencies


interface FeatureMainScreenDependenciesProvider {


    val dependencies: FeatureMainScreenComponentDependencies

    companion object : FeatureMainScreenDependenciesProvider by FeatureStore
}