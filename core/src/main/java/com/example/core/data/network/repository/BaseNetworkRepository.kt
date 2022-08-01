package com.example.core.data.network.repository

import com.example.core.data.network.CallExecutor
import kotlinx.coroutines.withContext
import retrofit2.Call
import kotlin.coroutines.CoroutineContext

/**
 * WR - Weather Response
 * */
abstract class BaseNetworkRepository<WR> {

    protected abstract val coroutineContext: CoroutineContext
    protected abstract val callExecutor: CallExecutor
    protected abstract val call: Call<WR>

    suspend fun fetchData(): WR = withContext(coroutineContext) {
        callExecutor.fetchResponse(call = call)
    }

}
