package com.example.feature_main_screen.data.network.repository

import com.example.core.data.network.CallExecutor
import com.example.core.data.network.repository.BaseNetworkRepository
import com.example.core.di.annotation.CoroutineContextIO
import com.example.feature_main_screen.data.network.models.WeatherResponse
import com.example.feature_main_screen.data.network.retrofit.WeatherService
import retrofit2.Call
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class NetworkRepository @Inject constructor(
    @param: CoroutineContextIO override val coroutineContext: CoroutineContext,
    override val callExecutor: CallExecutor,
    private val service: WeatherService
) : BaseNetworkRepository<WeatherResponse>() {

    override val call: Call<WeatherResponse>
        get() = service.executeByDefaultRequest()
}
