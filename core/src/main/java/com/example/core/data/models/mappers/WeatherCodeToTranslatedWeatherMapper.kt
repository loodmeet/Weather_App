package com.example.core.data.models.mappers

import com.example.core.domain.models.TranslatedWeather
import javax.inject.Inject
import com.example.core.domain.models.TranslatedWeather.*
import com.example.core.utils.Mapper

typealias CodeToTranslatedWeatherMapper =
        Mapper<@JvmSuppressWildcards Int, @JvmSuppressWildcards TranslatedWeather>

class WeatherCodeToTranslatedWeatherMapper @Inject constructor() :
    Mapper<Int, TranslatedWeather> {
    override suspend fun map(from: Int): TranslatedWeather {
        return when (from) {
            0 -> CLEAR_SKY
            1 -> MAINLY_CLEAR
            2 -> PARTLY_CLOUDY
            3 -> OVERCAST
            45 -> FOG
            48 -> DEPOSITING_RIME_FOG
            51 -> DRIZZLE
            53 -> DRIZZLE
            55 -> DRIZZLE
            56 -> DRIZZLE
            57 -> DRIZZLE
            61 -> SLIGHT_INTENSITY_RAIN
            63 -> MODERATE_INTENSITY_RAIN
            65 -> HEAVY_INTENSITY_RAIN
            66 -> LIGHT_INTENSITY_FREEZING_RAIN
            67 -> HEAVY_INTENSITY_FREEZING_RAIN
            71 -> SLIGHT_INTENSITY_SNOW
            73 -> MODERATE_INTENSITY_SNOW
            75 -> HEAVY_INTENSITY_SNOW
            77 -> SNOW_GRAINS
            80 -> RAIN_SHOWERS
            81 -> RAIN_SHOWERS
            82 -> RAIN_SHOWERS
            85 -> SNOW_SHOWERS
            86 -> SNOW_SHOWERS
            95 -> SLIGHT_THUNDERSTORM
            96 -> THUNDERSTORM_WITH_HAIL
            99 -> THUNDERSTORM_WITH_HAIL
            else -> UNKNOWN
        }
    }
}
