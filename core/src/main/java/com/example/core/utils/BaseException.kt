package com.example.core.utils

import android.util.Log

abstract class BaseException(
    isLogged: Boolean = false, final override val message: String? = null
) : Exception() {
    init {
        if (isLogged) Log.e(Config.MAIN_TAG, (message ?: " ") + "\n" + this.stackTraceToString())
    }
}
