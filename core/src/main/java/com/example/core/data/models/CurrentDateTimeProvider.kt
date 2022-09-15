package com.example.core.data.models

import java.time.LocalDateTime

interface CurrentDateTimeProvider {

    fun currentTimeOfDay(): TimeOfDay

    fun currentDateTime(): LocalDateTime

    fun currentHour(): Int
}