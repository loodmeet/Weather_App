package com.example.core.di.modules

import com.example.core.di.annotation.CoroutineContextDefault
import com.example.core.di.annotation.CoroutineContextIO
import com.example.core.di.annotation.CoroutineContextMain
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Module
object CoroutineContextModule {
    @[Provides CoroutineContextIO]
    fun provideIOContext(): CoroutineContext = Dispatchers.IO

    @[Provides CoroutineContextDefault]
    fun provideDefaultContext(): CoroutineContext = Dispatchers.Default

    @[Provides CoroutineContextMain]
    fun provideMainContext(): CoroutineContext = Dispatchers.Main
}