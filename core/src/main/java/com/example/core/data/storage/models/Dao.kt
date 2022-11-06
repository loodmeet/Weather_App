package com.example.core.data.storage.models

/**
 *
 * @param SD Stored Data that will be inserted and received as an list of Entity<SD>
 * @see DataEntity
 * */
interface Dao<SD : StoredData> { // do suspend?

    fun insertData(e: List<DataEntity<SD>>)

    fun getAllData(): List<DataEntity<SD>>
}