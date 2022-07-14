package com.example.core.data.exceptions

import com.example.core.utils.BaseException

class ParamNotUsedException(
    isLogged: Boolean = false,
    message: String? = null
) : BaseException(isLogged = isLogged, message = message)


