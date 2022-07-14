package com.example.core.data.models

import javax.inject.Inject
import kotlin.math.roundToInt

//interface Temperature {
//
//    // todo: inject degree sign from res (R)
//    fun getValueAsString(value: Double, degreeSign: String = "°"): String
//
//    fun getValueAsString(firstValue: Double, secondValue: Double, divider: String): String
//
//    class Base @Inject constructor(private val degreeSign: String) : Temperature {
//
//        override fun getValueAsString(value: Double, degreeSign: String) =
//            if (value > 0) "+${value.roundToInt()}$degreeSign"
//            else value.roundToInt().toString() + degreeSign
//
//        override fun getValueAsString(
//            firstValue: Double,
//            secondValue: Double,
//            divider: String,
//        ) =
//            getValueAsString(firstValue) + divider + getValueAsString(secondValue)
//    }
//
//}

// todo: rewrite
class Temperature(
    private val value: Double,
    private var degreeSign: String = "°"
) {

    fun changeDegreeSign(degreeSign: String): Temperature {
        this.degreeSign = degreeSign
        return this
    }

    fun getValueAsString() =
        if (value > 0) "+${value.roundToInt()}$degreeSign"
        else value.roundToInt().toString() + degreeSign

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