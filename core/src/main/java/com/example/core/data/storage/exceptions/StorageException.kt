package com.example.core.data.storage.exceptions

import com.example.core.utils.BaseException

class StorageException(isLogged: Boolean = false, message: String = "") :
    BaseException(isLogged = isLogged, message = message)