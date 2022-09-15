package com.example.core.data.models

import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.inject.Inject

class DateTimeProvider @Inject constructor(
    private val zoneOffset: ZoneOffset
) : WhatTimeOfDay, CurrentDateTimeProvider {

    private val dayRange = 12..16
    private val nightRange = 0..3
    private val morningRange = 4..11
    private val eveningRange = 17..23

    override fun currentDateTime(): LocalDateTime =
        ZonedDateTime.now(zoneOffset).toLocalDateTime()

    override fun hourRangeByTimeOfDay(timeOfDay: TimeOfDay) = when (timeOfDay) {
        TimeOfDay.DAY -> dayRange
        TimeOfDay.NIGHT -> nightRange
        TimeOfDay.MORNING -> morningRange
        TimeOfDay.EVENING -> eveningRange
    }

    override fun timeOfDayByHour(hour: Int): TimeOfDay = when (hour) {
        in dayRange -> TimeOfDay.DAY
        in nightRange -> TimeOfDay.NIGHT
        in morningRange -> TimeOfDay.MORNING
        in eveningRange -> TimeOfDay.EVENING
        else -> throw RuntimeException()
    }

    override fun currentHour(): Int {
        return currentDateTime().hour
    }

    override fun currentTimeOfDay(): TimeOfDay {
        val currentHour = currentHour()
        return timeOfDayByHour(hour = currentHour)
    }
}