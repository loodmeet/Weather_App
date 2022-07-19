package com.example.feature_main_screen.domain.repository

import com.example.core.ui.DisplayableItem
import java.text.DateFormat
import java.time.LocalDateTime
import java.util.*

internal interface MainRepository {

    suspend fun fetchData(): List<DisplayableItem>

    suspend fun currentDate(): LocalDateTime
}
