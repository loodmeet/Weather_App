package com.example.feature_daily_weather_details.data.storage.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.data.storage.exceptions.StorageException
import com.example.feature_daily_weather_details.data.storage.models.entities.DailyWeatherEntity
import com.example.feature_daily_weather_details.data.storage.models.entities.HourlyWeatherEntity
import com.example.core.data.storage.type_converters.LocalDateTimeTypeConverter
import com.example.feature_daily_weather_details.data.storage.models.daos.DailyWeatherDao
import com.example.feature_daily_weather_details.data.storage.models.daos.HourlyWeatherDao

@Database(entities = [HourlyWeatherEntity::class, DailyWeatherEntity::class], version = 1)
@TypeConverters(LocalDateTimeTypeConverter::class)
internal abstract class LocalDatabase : RoomDatabase() {

    abstract fun hourlyWeatherDao(): HourlyWeatherDao
    abstract fun dailyWeatherDao(): DailyWeatherDao

    companion object {
        private var INSTANCE: LocalDatabase? = null

        fun getLocalDatabase(application: Application): LocalDatabase {
            if (INSTANCE == null) {
                synchronized(LocalDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        application, LocalDatabase::class.java, "LocalDateBase"
                    ).build()
                }
            }

            return INSTANCE ?: throw StorageException("database ref is null")
        }
    }
}