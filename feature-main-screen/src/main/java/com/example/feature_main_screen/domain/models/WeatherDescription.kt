package com.example.feature_main_screen.domain.models

enum class WeatherDescription {

    SUNNY { override fun toString(): String = this.name.lowercase() },
    RAINY { override fun toString(): String = this.name.lowercase() },
    ClOUD { override fun toString(): String = this.name.lowercase() };


}