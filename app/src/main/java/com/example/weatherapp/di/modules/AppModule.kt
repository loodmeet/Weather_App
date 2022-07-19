package com.example.weatherapp.di.modules

import com.example.feature_main_screen.navigation.FeatureMainScreenNavCommandProvider
import com.example.weatherapp.di.qualifiers.BaseUrl
import com.example.weatherapp.di.scopes.AppScope
import com.example.weatherapp.navigation.FeatureMainScreenNavCommandProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
object AppModule {

    @[AppScope Provides] fun provideFeatureMainScreenNavCommandProvider(): FeatureMainScreenNavCommandProvider =
        FeatureMainScreenNavCommandProviderImpl()

    @[AppScope Provides] fun provideGson(): Gson = GsonBuilder()
//        .setDateFormat("yyyy-MM-dd'T'HH:mm")
        .create()

    @[AppScope Provides] fun provideDefaultRetrofitClient(@BaseUrl baseUrl: String, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()


}