package com.example.weatherapp.di.modules

import android.app.Application
import androidx.room.Room
import com.example.feature_daily_weather_details.data.storage.database.LocalDatabase
import com.example.weatherapp.R
import com.example.weatherapp.di.qualifiers.BaseUrl
import com.example.weatherapp.di.scopes.AppScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.open-meteo.com/v1/"

@Module class AppModule {

    @[Provides BaseUrl] fun provideBaseUrl(): String = BASE_URL

    @[AppScope Provides] fun provideGson(): Gson = GsonBuilder().create()

    @Provides fun provideDefaultRetrofitClient(@BaseUrl baseUrl: String, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides fun provideActionToDailyWeatherDetails(): Int =
        R.id.action_fragmentMainScreen_to_fragmentDailyWeatherDetails

    @[AppScope Provides] fun bindLocalDatabase(
        application: Application
    ): LocalDatabase = Room.databaseBuilder(
        application, LocalDatabase::class.java, "LocalDateBase"
    ).build()
}