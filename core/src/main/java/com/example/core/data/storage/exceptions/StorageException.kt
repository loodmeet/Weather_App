package com.example.core.data.storage.exceptions

import com.example.core.utils.BaseException

class StorageException(message: String = "") : BaseException(isLogged = true, message = message)