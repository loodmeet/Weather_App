package com.example.feature_daily_weather_details.data.storage.models.mappers

//import com.example.core.utils.Mapper
//import com.example.feature_daily_weather_details.data.models.DailyWeather
//import com.example.feature_daily_weather_details.data.storage.models.entities.DailyDetailsDailyWeatherDataEntity
//import javax.inject.Inject
//
//internal typealias  DailyToEntityMapper =
//        Mapper<@JvmSuppressWildcards DailyWeather, DailyDetailsDailyWeatherDataEntity>
//
//internal class DailyWeatherToEntityMapper @Inject constructor() :
//    Mapper<@JvmSuppressWildcards DailyWeather, DailyDetailsDailyWeatherDataEntity> {
//
//    override suspend fun map(from: DailyWeather): DailyDetailsDailyWeatherDataEntity =
//        DailyDetailsDailyWeatherDataEntity(
//            id = 0,
//            date = from.date
//        )
//}