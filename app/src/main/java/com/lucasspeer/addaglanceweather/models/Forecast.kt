package com.lucasspeer.addaglanceweather.models

import com.google.gson.annotations.SerializedName



data class ForecastResponse(
    @SerializedName("location") val location: Location,
    @SerializedName("forecast") val forecast: ForecastData
)

data class ForecastData(
    @SerializedName("forecastday") val days: List<ForecastDay>
)

data class ForecastDay(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: DayWeather
)

data class DayWeather(
    @SerializedName("maxtemp_c") val maxTempC: Float,
    @SerializedName("mintemp_c") val minTempC: Float,
    @SerializedName("condition") val condition: WeatherCondition
)