package com.example.core.domain.models

import com.example.api.R

enum class TranslatedWeather {

    CLEAR_SKY { override val stringResId = R.string.clear_sky },
    MAINLY_CLEAR {  override val stringResId = R.string.mainly_clear },
    PARTLY_CLOUDY { override val stringResId = R.string.partly_cloudy },
    OVERCAST { override val stringResId = R.string.overcast },
    FOG { override val stringResId = R.string.fog },
    DEPOSITING_RIME_FOG { override val stringResId = R.string.depositing_rime_fog },
    DRIZZLE { override val stringResId = R.string.drizzle },
    SLIGHT_INTENSITY_RAIN { override val stringResId = R.string.slight_intensity_rain },
    MODERATE_INTENSITY_RAIN { override val stringResId = R.string.moderate_intensity_rain },
    HEAVY_INTENSITY_RAIN { override val stringResId = R.string.heavy_intensity_rain },
    LIGHT_INTENSITY_FREEZING_RAIN { override val stringResId = R.string.light_intensity_freezing_rain },
    HEAVY_INTENSITY_FREEZING_RAIN { override val stringResId = R.string.heavy_intensity_freezing_rain },
    SLIGHT_INTENSITY_SNOW { override val stringResId = R.string.slight_intensity_snow },
    MODERATE_INTENSITY_SNOW { override val stringResId = R.string.moderate_intensity_snow },
    HEAVY_INTENSITY_SNOW { override val stringResId = R.string.heavy_intensity_snow },
    SNOW_GRAINS { override val stringResId = R.string.snow_grains },
    RAIN_SHOWERS { override val stringResId = R.string.rain_showers },
    SNOW_SHOWERS { override val stringResId = R.string.snow_showers },
    SLIGHT_THUNDERSTORM { override val stringResId = R.string.slight_thunderstorm },
    THUNDERSTORM_WITH_HAIL { override val stringResId = R.string.thunderstorm_with_hail },
    UNKNOWN { override val stringResId = R.string.unknown };

    abstract val stringResId: Int
}