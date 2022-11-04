package com.example.weatherapp.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.data.storage.type_converters.LocalDateTimeTypeConverter
import com.example.feature_daily_weather_details.data.storage.database.FeatureDailyWeatherDao
import com.example.feature_daily_weather_details.data.storage.models.daos.DailyWeatherDao
import com.example.feature_daily_weather_details.data.storage.models.daos.HourlyWeatherDao
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyWeatherEntity
import com.example.feature_daily_weather_details.data.storage.models.entities.HourlyWeatherEntity

@[Database(
    entities = [HourlyWeatherEntity::class, DailyWeatherEntity::class], version = 1
) TypeConverters(LocalDateTimeTypeConverter::class)]
abstract class LocalDatabase : RoomDatabase(), FeatureDailyWeatherDao {

    abstract override fun hourlyWeatherDao(): HourlyWeatherDao
    abstract override fun dailyWeatherDao(): DailyWeatherDao
}