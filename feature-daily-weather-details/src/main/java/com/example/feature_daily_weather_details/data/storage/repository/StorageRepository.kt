package com.example.feature_daily_weather_details.data.storage.repository

import com.example.core.data.storage.repository.BaseStorageRepository
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import javax.inject.Inject

internal class StorageRepository @Inject constructor() : BaseStorageRepository<WeatherResponse>() {
    // todo
}