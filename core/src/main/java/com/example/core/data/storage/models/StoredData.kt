package com.example.core.data.storage.models

/**
 * The class that can be converted into an Entity and provided to a local database
 * @see DataEntity
 * */
interface StoredData {

    fun toEntity(): DataEntity<StoredData>
}