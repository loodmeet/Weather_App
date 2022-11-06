package com.example.core.data.repository

import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * @param DM Domain Model
 * */
abstract class Repository<DM : Any>(
    @param: CoroutineContextIO private val coroutineContext: CoroutineContext
) {
    protected var data: DM? = null

    protected abstract suspend fun fetchDataFromInternet(): DM
    protected abstract suspend fun fetchDataFromDatabase(): DM

    open suspend fun fetchData(): DM = withContext(context = coroutineContext) {

        if (data != null) return@withContext data as DM

        data = try {
            fetchDataFromInternet()
        } catch (e: Exception) {
            fetchDataFromDatabase()
        }
        return@withContext data as DM
    }
}