package com.example.weatherapp.di.component

import android.app.Application
import com.example.feature_daily_weather_details.data.storage.models.daos.FeatureDailyWeatherDao
import com.example.feature_daily_weather_details.di.dependencies.FeatureDailyWeatherDetailsComponentDependencies
import com.example.feature_main_screen.data.storage.models.daos.FeatureMainScreenDao
import com.example.feature_main_screen.di.dependencies.FeatureMainScreenComponentDependencies
import com.example.weatherapp.di.modules.AppModule
import com.example.weatherapp.di.scopes.AppScope
import com.example.weatherapp.ui.screens.MainActivity
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@[AppScope Component(modules = [AppModule::class])]
internal interface AppComponent : FeatureMainScreenComponentDependencies,
    FeatureDailyWeatherDetailsComponentDependencies {

    fun inject(activity: MainActivity)

    @Component.Builder interface Builder {

        @BindsInstance fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override val mainScreenRetrofit: Retrofit
    override val dailyWeatherRetrofit: Retrofit
    override val featureDailyWeatherDao: FeatureDailyWeatherDao
    override val featureMainScreenDao: FeatureMainScreenDao
    override val actionToFragmentDailyWeatherDetails: Int // todo: need to use qualifiers
}