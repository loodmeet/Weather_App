package com.example.core.domain.exceptions

import com.example.core.utils.BaseException

class DatabaseException(
    isLogged: Boolean,
    message: String? = null
) : BaseException(isLogged = isLogged, message = message)