package com.example.feature_daily_weather_details.data.models.mappers

import com.example.core.di.annotation.qualifiers.CoroutineContextDefault
import com.example.core.utils.Mapper
import com.example.feature_daily_weather_details.data.models.DataModel
import com.example.feature_daily_weather_details.data.models.HourlyWeather
import com.example.feature_daily_weather_details.data.network.models.responce.WeatherResponse
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @see Mapper
 * */
internal class WeatherResponseToDataModelMapper @Inject constructor(
    @param: CoroutineContextDefault private val coroutineContext: CoroutineContext,
    private val formatter: DateTimeFormatter
) : Mapper<WeatherResponse, DataModel> {
    override suspend fun map(from: WeatherResponse): DataModel =
        withContext(context = coroutineContext) {
            with(from.hourly) {

                DataModel(
                    hourlyWeather = List(size = time.size) { index ->
                        val localDateTime = LocalDateTime.parse(time[index], formatter)
                        HourlyWeather(
                            time = localDateTime.toLocalTime(),
                            temperature = temperature2m[index],
                            weatherCode = weatherCode[index],
                            apparentTemperature = apparentTemperature[index],
                            precipitation = precipitation[index],
                            windSpeed = windSpeed10m[index],
                            relativeHumidity = relativeHumidity2m[index],
                            windDirection = 0,
                            date = localDateTime.toLocalDate()
                        )
                    }
                )
            }
        }
}