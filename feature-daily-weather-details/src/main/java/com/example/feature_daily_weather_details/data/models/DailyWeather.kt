package com.example.feature_daily_weather_details.data.models

import com.example.core.data.storage.models.DataEntity
import com.example.core.data.storage.models.StoredData
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyDetailsDailyWeatherDataEntity
import java.time.LocalDate

//data class DailyWeather (
//    val date: LocalDate
//) : StoredData {
//
//    override fun toEntity(): DataEntity<StoredData> = DailyDetailsDailyWeatherDataEntity(
//        id = 0,
//        date = date
//    ) as DataEntity<StoredData>
//}