package com.example.core.domain.exceptions

import com.example.core.utils.BaseException

class NetworkException(
    isLogged: Boolean = false,
    message: String? = null
) : BaseException(isLogged = isLogged, message = message)