package com.example.core.data.storage.models

/**
 *
 * @param SD Stored Data that will be inserted and received as an list of Entity<SD>
 * @see Entity
 * */
interface Dao<SD : StoredData> { // do suspend?

    fun insertData(e: List<Entity<SD>>)

    fun getAllData(): List<Entity<SD>>
}