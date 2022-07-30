package com.example.core.data.storage.repository

import android.util.Log
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.utils.Config

abstract class BaseStorageRepository<T> {

    init { Log.d(Config.MAIN_TAG, "BaseStorageRepository created") }

    protected var value: T? = null

    open fun updateData(data: T) {
        this.value = data
    }

    open fun getData(): T {
        return value ?: throw StorageException()
    }
}