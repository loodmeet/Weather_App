package com.example.core.data.storage.repository

import com.example.core.data.storage.models.Dao
import com.example.core.data.storage.models.Entity
import com.example.core.data.storage.models.StoredData
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * This Repository encapsulates the logic of storing StoredData in the Room database
 * @see StoredData
 * @see Dao
 * */
abstract class StorageRepository<T : StoredData>(
    private val dao: Dao<T>,
    private val coroutineContext: CoroutineContext
) {

    suspend fun getData(): List<T> = withContext(context = coroutineContext) {

        val entities = dao.getAllData()

        return@withContext List(size = entities.size) { index ->
            entities[index].toBaseType()
        }
    }

    suspend fun insertData(data: List<T>) = withContext(context = coroutineContext) {

        return@withContext dao.insertData(e = List(data.size) { index ->
            data[index].toEntity() as Entity<T>
        })
    }
}