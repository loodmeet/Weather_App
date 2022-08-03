package com.example.weatherapp.di.component

import com.example.feature_daily_weather_details.di.dependencies.FeatureDailyWeatherDetailsComponentDependencies
import com.example.feature_main_screen.di.dependencies.FeatureMainScreenComponentDependencies
import com.example.feature_main_screen.navigation.FeatureMainScreenNavCommandProvider
import com.example.weatherapp.di.modules.AppModule
import com.example.weatherapp.di.qualifiers.BaseUrl
import com.example.weatherapp.di.scopes.AppScope
import com.example.weatherapp.ui.screens.MainActivity
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : FeatureMainScreenComponentDependencies,
    FeatureDailyWeatherDetailsComponentDependencies {

    override val featureMainScreenNavCommandProvider: FeatureMainScreenNavCommandProvider

//    override val featureDailyWeatherDetailsNavCommandProvider: FeatureDailyWeatherDetailsNavCommandProvider

    override val retrofit: Retrofit

    fun inject(activity: MainActivity)

    @Component.Builder interface Builder {

        @BindsInstance fun baseUrl(@BaseUrl baseUrl: String): Builder

        fun build(): AppComponent
    }
}