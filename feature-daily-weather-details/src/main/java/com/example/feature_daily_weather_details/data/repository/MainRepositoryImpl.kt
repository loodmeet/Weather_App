package com.example.feature_daily_weather_details.data.repository

import android.util.Log
import com.example.core.data.network.exceptions.ResponseIsNotSuccessfulException
import com.example.core.data.repository.Repository
import com.example.core.data.storage.exceptions.StorageException
import com.example.core.data.storage.repository.StorageRepository
import com.example.core.di.annotation.qualifiers.CoroutineContextIO
import com.example.core.utils.Config
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DataModel
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToDailyListMapper
import com.example.feature_daily_weather_details.data.models.mappers.ResponseToHourlyListMapper
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import com.example.feature_daily_weather_details.data.network.retrofit.WeatherService
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @see Repository
 * */
internal class MainRepositoryImpl<DM : Any> @Inject constructor(
    @param: CoroutineContextIO private val coroutineContext: CoroutineContext,
    private val service: WeatherService,
    private val responseMapper: Mapper<WeatherResponse, DataModel>,
    private val mapper: Mapper<DataModel, DM>,
    private val hourlyStorageRepository: StorageRepository<HourlyWeather>
) : Repository<DataModel, DM>(coroutineContext = coroutineContext, mapper = mapper) {

    override suspend fun fetchDataFromInternet(): DataModel =
        withContext(context = coroutineContext) {
            responseMapper.map(
                from = service.executeByDefaultRequest().also { retrofitResponse ->
                    if (!retrofitResponse.isSuccessful) {
                        throw ResponseIsNotSuccessfulException(
                            isLogged = true,
                            message = retrofitResponse.message()
                        )
                    }
                }.body()!!.also {
                    Log.d(Config.NETWORK_TAG, "Feature Daily Weather Details: $it")
                })
        }


    override suspend fun fetchDataFromDatabase(): DataModel =
        withContext(context = coroutineContext) {

            DataModel(hourlyWeather = hourlyStorageRepository.getData())
        }
//
//    suspend fun fetchWeatherByDate(
//        date: LocalDate
//    ): List<HourlyWeather> = withContext(context = coroutineContext) {
//        with(storageRepository) {
//
//            val response = try {
//                service.executeByDefaultRequest().also { retrofitResponse ->
//                    if (!retrofitResponse.isSuccessful) throw ResponseIsNotSuccessfulException(
//                        isLogged = true, message = retrofitResponse.message()
//                    )
//                }.body()!!.also {
//                    Log.d(Config.NETWORK_TAG, "Feature Daily Weather Details: $it")
//                }
//            } catch (e: Exception) {
//                val hourlyWeather = getHourlyWeatherForDay(dayDate = date)
//                if (hourlyWeather.isEmpty()) throw StorageException(
//                    isLogged = true, message = "Storage is empty"
//                )
//
//                return@withContext hourlyWeather
//            }
//
//            val hourlyWeatherList = responseToHourlyListMapper.map(from = response)
//            val dailyWeatherList = responseToDailyListMapper.map(from = response)
//
//            insertDailyWeather(dailyWeather = dailyWeatherList)
//            insertHourlyWeather(hourlyWeather = hourlyWeatherList.associateBy({ hourly ->
//                hourly
//            }, { hourly ->
//                val dailyIndex = (hourlyWeatherList.indexOf(hourly)) / 24
//                dailyWeatherList[dailyIndex].date
//            }))
//
//            return@withContext hourlyWeatherList
//        }
//    }
}