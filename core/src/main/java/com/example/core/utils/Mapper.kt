package com.example.core.utils

/**
 * @param F map from this type
 * @param T map to this type
 * */
interface Mapper<in F : Any, out T : Any> {

    suspend fun map(from: F): T
}
