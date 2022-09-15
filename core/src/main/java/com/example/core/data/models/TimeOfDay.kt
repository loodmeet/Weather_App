package com.example.core.data.models

import com.example.api.R

enum class TimeOfDay {
    DAY {
        override val resId = R.string.day
    },
    NIGHT {
        override val resId = R.string.night
    },
    MORNING {
        override val resId = R.string.morning
    },
    EVENING {
        override val resId = R.string.evening
    };

    abstract val resId: Int
}