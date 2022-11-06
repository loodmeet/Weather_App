package com.example.core.data.storage.models

/**
 * The class that can be provided to a local database and converted back into an StoredData
 * @see StoredData
 * */
interface Entity<T : StoredData> {

    fun toBaseType(): T
}