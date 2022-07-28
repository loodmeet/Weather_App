package com.example.core.data.models

import com.example.api.R
import java.time.LocalDateTime
import java.util.*
import javax.inject.Inject

interface DateTimeProvider {

    fun timeOfDayByHour(hour: Int): TimeOfDay

    fun isDayOrNightByHour(hour: Int): TimeOfDay

    fun hourRangeByTimeOfDay(timeOfDay: TimeOfDay): IntRange

    fun currentTimeOfDay(): TimeOfDay

    fun currentDateTime(): LocalDateTime

    fun currentHour(): Int

    enum class TimeOfDay {
        DAY { override val resId = R.string.day },
        NIGHT { override val resId = R.string.night },
        MORNING { override val resId = R.string.morning },
        EVENING { override val resId = R.string.evening};

        abstract val resId: Int
    }

    class Base @Inject constructor(
        private var calendar: Calendar
    ) : DateTimeProvider {

        private val dayRange = 12..16
        private val nightRange = 0..3
        private val morningRange = 4..11
        private val eveningRange = 17..23

        override fun currentDateTime(): LocalDateTime = LocalDateTime.now()

        override fun hourRangeByTimeOfDay(timeOfDay: TimeOfDay) =
            when(timeOfDay) {
                TimeOfDay.DAY -> dayRange
                TimeOfDay.NIGHT -> nightRange
                TimeOfDay.MORNING -> morningRange
                TimeOfDay.EVENING -> eveningRange
            }

        override fun timeOfDayByHour(hour: Int): TimeOfDay =
            when(hour) {
                in dayRange -> TimeOfDay.DAY
                in nightRange -> TimeOfDay.NIGHT
                in morningRange -> TimeOfDay.MORNING
                in eveningRange -> TimeOfDay.EVENING
                else -> throw RuntimeException()
            }


        override fun isDayOrNightByHour(hour: Int): TimeOfDay {
            return if ( hour in 7..21 ) TimeOfDay.DAY else TimeOfDay.NIGHT
        }

        override fun currentHour(): Int {
            return calendar.get(Calendar.HOUR_OF_DAY)
        }

        // todo
        override fun currentTimeOfDay(): TimeOfDay {

            val currentHour = currentHour()
            return isDayOrNightByHour(hour = currentHour)
        }
    }
}
