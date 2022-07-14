package com.example.core.data.network.exceptions

import com.example.core.utils.BaseException

class ResponseIsNotSuccessfulException(
    isLogged: Boolean = false,
    message: String? = null
) : BaseException(isLogged = isLogged, message = message)