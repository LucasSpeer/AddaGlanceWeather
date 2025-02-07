package com.lucasspeer.addaglanceweather

import android.util.Log
import com.lucasspeer.addaglanceweather.models.ForecastResponse
import com.lucasspeer.addaglanceweather.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.logging.Logger

interface WeatherApiService {
    @GET("current.json")
    fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String
    ): Call<WeatherResponse>

    @GET("forecast.json")
    fun getForecastWeather(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int = 3    //TODO Get weatherAPI to return more than 3 days
    ): Call<ForecastResponse>
}

object RetrofitClient {
    private const val BASE_URL = "https://api.weatherapi.com/v1/"

    val instance: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}

class WeatherFetcher(private val apiKey: String) {
    fun fetchWeather(location: String, callback: (WeatherResponse?) -> Unit) {
        val call = RetrofitClient.instance.getCurrentWeather(apiKey, location)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun fetchForecast(location: String, callback: (ForecastResponse?) -> Unit) {
        val call = RetrofitClient.instance.getForecastWeather(apiKey, location)

        call.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
                callback(response.body())
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                callback(null)
            }
        })
    }
}