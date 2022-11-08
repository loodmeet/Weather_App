package com.example.core.data.repository

import com.example.core.utils.Mapper
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * @param DOM Data Model
 * @param DAM Data Model
 * */
abstract class Repository<DOM : Any, DAM : Any>(
    private val coroutineContext: CoroutineContext,
    private val mapper: Mapper<DAM, DOM>
) {
    protected var data: DAM? = null

    protected abstract suspend fun fetchDataFromInternet(): DAM
    protected abstract suspend fun fetchDataFromDatabase(): DAM

    open suspend fun fetchData(): DOM = withContext(context = coroutineContext) {

        if (data != null) return@withContext mapper.map(from = data as DAM)

        data = try {
            fetchDataFromInternet()
        } catch (e: Exception) {
            fetchDataFromDatabase()
        }
        return@withContext mapper.map(from = data as DAM)
    }
}