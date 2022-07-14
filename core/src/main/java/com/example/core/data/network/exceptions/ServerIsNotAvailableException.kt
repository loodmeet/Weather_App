package com.example.core.data.network.exceptions

import com.example.core.utils.BaseException

class ServerIsNotAvailableException(
    isLogged: Boolean = false,
    message: String? = null
) : BaseException(isLogged = isLogged, message = message)