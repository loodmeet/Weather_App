package com.example.weatherapp.di.modules

import com.example.weatherapp.R
import com.example.feature_main_screen.di.qualifiers.ActionToDailyWeatherDetails
import com.example.weatherapp.di.qualifiers.BaseUrl
import com.example.weatherapp.di.scopes.AppScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
object AppModule {

    private const val BASE_URL = "https://api.open-meteo.com/v1/"

    @[AppScope Provides BaseUrl] fun provideBaseUrl(): String = BASE_URL

    @[AppScope Provides] fun provideGson(): Gson = GsonBuilder()
        .create()

    @[AppScope Provides] fun provideDefaultRetrofitClient(@BaseUrl baseUrl: String, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @[AppScope Provides ] fun provideActionToDailyWeatherDetails(): Int =
        R.id.action_fragmentMainScreen_to_fragmentDailyWeatherDetails
}