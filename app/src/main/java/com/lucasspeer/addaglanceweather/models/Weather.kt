package com.lucasspeer.addaglanceweather.models

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: CurrentWeather
)

data class Location(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String
)

data class CurrentWeather(
    @SerializedName("temp_c") val tempC: Float,
    @SerializedName("condition") val condition: WeatherCondition,
    @SerializedName("cloud") val cloud: Int,
    @SerializedName("humidity") val humidity: Int
)

data class WeatherCondition(
    @SerializedName("text") val description: String
)