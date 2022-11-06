package com.example.weatherapp.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.data.storage.type_converters.LocalDateTimeTypeConverter
import com.example.feature_daily_weather_details.data.storage.models.daos.FeatureDailyWeatherDao
import com.example.feature_daily_weather_details.data.storage.models.daos.HourlyWeatherDao as DailyDetailsHourlyWeatherDao
import com.example.feature_daily_weather_details.data.storage.models.daos.DailyWeatherDao as DailyDetailsDailyWeatherDao
import com.example.feature_main_screen.data.storage.models.daos.HourlyWeatherDao as MainScreensHourlyWeatherDao
import com.example.feature_main_screen.data.storage.models.daos.DailyWeatherDao as MainScreensDailyWeatherDao
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyDetailsDailyWeatherDataEntity
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyDetailsHourlyWeatherEntity
import com.example.feature_main_screen.data.storage.models.entities.MainScreenDailyWeatherEntity
import com.example.feature_main_screen.data.storage.models.entities.MainScreenHourlyWeatherEntity
import com.example.feature_main_screen.data.storage.models.daos.FeatureMainScreenDao

@[Database(
    entities = [
        DailyDetailsDailyWeatherDataEntity::class,
        DailyDetailsHourlyWeatherEntity::class,
        MainScreenDailyWeatherEntity::class,
        MainScreenHourlyWeatherEntity::class
    ], version = 1
) TypeConverters(LocalDateTimeTypeConverter::class)]
abstract class LocalDatabase : RoomDatabase(), FeatureDailyWeatherDao, FeatureMainScreenDao {

    abstract override fun dailyDetailsHourlyWeatherDao(): DailyDetailsHourlyWeatherDao

    abstract override fun dailyDetailsDailyWeatherDao(): DailyDetailsDailyWeatherDao

    abstract override fun mainScreenHourlyWeatherDao(): MainScreensHourlyWeatherDao

    abstract override fun mainScreenDailyWeatherDao():MainScreensDailyWeatherDao
}