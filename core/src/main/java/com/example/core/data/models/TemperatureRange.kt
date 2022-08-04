package com.example.core.data.models

class TemperatureRange(
    private val firstValue: Temperature,
    private val secondValue: Temperature
) {
    fun changeDegreeSign(degreeSign: String): TemperatureRange {
        firstValue.changeDegreeSign(degreeSign = degreeSign)
        secondValue.changeDegreeSign(degreeSign = degreeSign)
        return this
    }

    fun getValuesAsString(divider: String) =
        firstValue.getValueAsString() + divider + secondValue.getValueAsString()

    fun getValues() = firstValue to secondValue

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TemperatureRange

        if (firstValue != other.firstValue) return false
        if (secondValue != other.secondValue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstValue.hashCode()
        result = 31 * result + secondValue.hashCode()
        return result
    }
}