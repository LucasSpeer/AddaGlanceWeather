package com.lucasspeer.addaglanceweather

fun getFormattedTemperature(tempC: Float, useCelsius: Boolean): String {
    return if (useCelsius) {
        "${tempC}°C"
    } else {
        val tempF = (tempC * 9/5) + 32
        "${"%.1f".format(tempF)}°F"
    }
}