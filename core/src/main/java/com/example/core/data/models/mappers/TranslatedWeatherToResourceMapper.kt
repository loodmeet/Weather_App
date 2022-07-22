package com.example.core.data.models.mappers

import com.example.api.R.drawable.ic_sun_32
import com.example.api.R.drawable.ic_moon_32
import com.example.api.R.drawable.ic_weather_2_32
import com.example.api.R.drawable.ic_weather_3_32
import com.example.api.R.drawable.ic_sky_32
import com.example.api.R.drawable.ic_weather_6_32
import com.example.api.R.drawable.ic_weather_5_32
import com.example.api.R.drawable.ic_weather_11_32
import com.example.api.R.drawable.ic_weather_12_32
import com.example.api.R.drawable.ic_weather_9_32
import com.example.api.R.drawable.ic_weather_7_32
import com.example.api.R.drawable.ic_weather_14_32
import com.example.api.R.drawable.ic_weather_15_32
import com.example.api.R.drawable.ic_snow_32
import com.example.api.R.drawable.ic_weather_44_32
import com.example.api.R.drawable.ic_weather_45_32
import com.example.api.R.drawable.ic_weather_32_32
import com.example.api.R.drawable.ic_weather_17_32
import com.example.api.R.drawable.ic_weather_18_32
import com.example.api.R.drawable.ic_weather_33_32
import com.example.core.data.models.DateTimeProvider.TimeOfDay.*
import com.example.core.data.models.DateTimeProvider.TimeOfDay
import com.example.core.domain.models.TranslatedWeather
import com.example.core.domain.models.TranslatedWeather.*
import com.example.core.utils.Mapper
import javax.inject.Inject

typealias TranslatedWeatherToResMapper =
        Mapper<@JvmSuppressWildcards Pair<TranslatedWeather, TimeOfDay>, Int>

class TranslatedWeatherToResourceMapper @Inject constructor() :
    Mapper<@JvmSuppressWildcards Pair<TranslatedWeather, TimeOfDay>, Int> {

    override suspend fun map(from: Pair<TranslatedWeather, TimeOfDay>): Int {
        val timeOfDay = from.second

        return when (from.first) {
            CLEAR_SKY -> if (timeOfDay == NIGHT) ic_moon_32 else ic_sun_32
            MAINLY_CLEAR -> if (timeOfDay == NIGHT) ic_weather_3_32 else ic_weather_2_32
            PARTLY_CLOUDY -> if (timeOfDay == NIGHT) ic_weather_3_32 else ic_weather_2_32
            OVERCAST -> if (timeOfDay == NIGHT) ic_sky_32 else ic_sky_32
            FOG -> if (timeOfDay == NIGHT) ic_sky_32 else ic_sky_32
            DEPOSITING_RIME_FOG -> if (timeOfDay == NIGHT) ic_sky_32 else ic_sky_32
            DRIZZLE -> if (timeOfDay == NIGHT) ic_weather_6_32 else ic_weather_5_32
            SLIGHT_INTENSITY_RAIN -> if (timeOfDay == NIGHT) ic_weather_6_32 else ic_weather_5_32
            MODERATE_INTENSITY_RAIN -> if (timeOfDay == NIGHT) ic_weather_12_32 else ic_weather_11_32
            HEAVY_INTENSITY_RAIN -> if (timeOfDay == NIGHT) ic_weather_7_32 else ic_weather_9_32
            LIGHT_INTENSITY_FREEZING_RAIN -> if (timeOfDay == NIGHT) ic_weather_15_32 else ic_weather_14_32
            HEAVY_INTENSITY_FREEZING_RAIN -> if (timeOfDay == NIGHT) ic_weather_12_32 else ic_weather_11_32
            SLIGHT_INTENSITY_SNOW -> if (timeOfDay == NIGHT) ic_snow_32 else ic_snow_32
            MODERATE_INTENSITY_SNOW -> if (timeOfDay == NIGHT) ic_snow_32 else ic_snow_32
            HEAVY_INTENSITY_SNOW -> if (timeOfDay == NIGHT) ic_snow_32 else ic_snow_32
            SNOW_GRAINS -> if (timeOfDay == NIGHT) ic_weather_45_32 else ic_weather_44_32
            RAIN_SHOWERS -> if (timeOfDay == NIGHT) ic_weather_33_32 else ic_weather_32_32
            SNOW_SHOWERS -> if (timeOfDay == NIGHT) ic_weather_45_32 else ic_weather_44_32
            SLIGHT_THUNDERSTORM -> if (timeOfDay == NIGHT) ic_weather_18_32 else ic_weather_17_32
            THUNDERSTORM_WITH_HAIL -> if (timeOfDay == NIGHT) ic_weather_45_32 else ic_weather_44_32
            UNKNOWN -> if (timeOfDay == NIGHT) ic_moon_32 else ic_sun_32
        }
    }
}

