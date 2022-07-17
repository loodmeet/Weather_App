package com.example.core.utils

import com.example.core.ui.DisplayableItem

/**
 * @param F map from this type
 * @param T map to this type
 * */
interface Mapper<in F : Any, out T : Any> {

    suspend fun map(from: F): T

}
data class WeatherCode(val id: Int)

interface WeatherCodeToDisplayableItemMapper<D : DisplayableItem> : Mapper<WeatherCode, D> {

    suspend fun map(from: WeatherCode, day: Int): D = map(from = from)

    suspend fun map(hour: Int, from: WeatherCode ): D = map(from = from)

    suspend fun map(hour: Int, day: Int, from: WeatherCode ): D = map(from = from)
}
