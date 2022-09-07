package com.example.core.data.network.repository

import android.util.Log
import com.example.core.data.network.exceptions.ResponseIsNotSuccessfulException
import com.example.core.data.network.exceptions.ServerIsNotAvailableException
import com.example.core.utils.Config
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * WR - Weather Response
 * */
class NetworkRepository<WR> @Inject constructor(){

    /**
     * @throws ServerIsNotAvailableException if there are any problems with connection to
     * the server.
     * @throws ResponseIsNotSuccessfulException if the response is not successful.
     * */
    suspend fun fetchResponse(call: Call<WR>): WR {
        val response: Response<WR> = try {
            call.execute()
        } catch (e: Exception) {
            when (e) {
                is IOException -> {
                    // todo
                }
                is RuntimeException -> {
                    // todo
                }
            }
            throw ServerIsNotAvailableException(
                isLogged = true, message = e.stackTraceToString()
            )
        }

        return if (response.isSuccessful) {
            Log.d(Config.NETWORK_TAG, "NetworkRepository: " + response.body().toString())
            response.body()
        } else throw ResponseIsNotSuccessfulException(
            isLogged = true, message = response.message()
        )
    }
}