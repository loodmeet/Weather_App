package com.example.core.data.models

import kotlin.math.roundToInt

class Temperature(
    private val value: Double,
    private var degreeSign: String = "°"
) {
    fun changeDegreeSign(degreeSign: String): Temperature {
        this.degreeSign = degreeSign
        return this
    }

    fun getValueAsString() =
        if (value.roundToInt() > 0) "+${value.roundToInt()}$degreeSign"
        else value.roundToInt().toString() + degreeSign

    fun getValueAsDouble() = value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Temperature

        if (value != other.value) return false
        if (degreeSign != other.degreeSign) return false

        return true
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + degreeSign.hashCode()
        return result
    }
}