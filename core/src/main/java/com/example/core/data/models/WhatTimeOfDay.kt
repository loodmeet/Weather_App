package com.example.core.data.models

interface WhatTimeOfDay {

    fun timeOfDayByHour(hour: Int): TimeOfDay

    fun hourRangeByTimeOfDay(timeOfDay: TimeOfDay): IntRange
}