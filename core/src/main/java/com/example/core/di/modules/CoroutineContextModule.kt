package com.example.core.di.modules

import com.example.core.di.annotation.qualifiers.CoroutineContextDefault
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.core.di.annotation.qualifiers.CoroutineContextMain
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module class CoroutineContextModule {
    @[Provides CoroutineContextIO]
    fun provideIOContext(): CoroutineContext = Dispatchers.IO

    @[Provides CoroutineContextDefault]
    fun provideDefaultContext(): CoroutineContext = Dispatchers.Default

    @[Provides CoroutineContextMain]
    fun provideMainContext(): CoroutineContext = Dispatchers.Main
}