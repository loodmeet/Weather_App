package com.example.core.data.storage.type_converters

import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.LocalTime

class LocalDateTimeTypeConverter {

    @TypeConverter fun fromLocalDate(date: LocalDate): Long = date.toEpochDay()

    @TypeConverter fun toLocalDate(epochDay: Long): LocalDate = LocalDate.ofEpochDay(epochDay)

    @TypeConverter fun fromLocalTime(time: LocalTime): Long = time.toNanoOfDay()

    @TypeConverter fun toLocalTime(nanoOfDay: Long): LocalTime = LocalTime.ofNanoOfDay(nanoOfDay)
}