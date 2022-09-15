package com.example.core.data.storage.repository

import com.example.core.data.storage.exceptions.StorageException

abstract class BaseStorageRepository<T> { // todo: rename, rewrite

    protected var value: T? = null

    open fun updateData(data: T) {
        this.value = data
    }

    open fun getData(): T {
        return value ?: throw StorageException()
    }
}