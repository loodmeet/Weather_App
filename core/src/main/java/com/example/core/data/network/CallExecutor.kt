package com.example.core.data.network

import android.util.Log
import com.example.core.data.network.exceptions.ResponseIsNotSuccessfulException
import com.example.core.data.network.exceptions.ServerIsNotAvailableException
import com.example.core.utils.Config
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

interface CallExecutor {

    suspend fun <R> fetchResponse(call: Call<R>): R

    class Base @Inject constructor() : CallExecutor {

        /**
         * R - Response
         * @throws ServerIsNotAvailableException if there are any problems with connection to
         * the server.
         * @throws ResponseIsNotSuccessfulException if the response is not successful.
         * */
        override suspend fun <T> fetchResponse(call: Call<T>): T {
            val response: Response<T> = try {
                call.execute()
            } catch (e: Exception) {
                when (e) {
                    is IOException -> {
                        // do something
                    }
                    is RuntimeException -> {
                        // do something
                    }
                }
                throw ServerIsNotAvailableException(
                    isLogged = true, message = e.stackTraceToString()
                )
            }

            return if (response.isSuccessful) {
                Log.d(Config.NETWORK_TAG, "CallExecutor: " + response.body().toString())
                response.body()
            } else throw ResponseIsNotSuccessfulException(
                    isLogged = true, message = response.message()
                )
        }


    }
}

