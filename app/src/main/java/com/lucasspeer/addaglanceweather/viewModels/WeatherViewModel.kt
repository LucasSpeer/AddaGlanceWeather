package com.lucasspeer.addaglanceweather.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lucasspeer.addaglanceweather.UserPreferencesRepository
import com.lucasspeer.addaglanceweather.WeatherFetcher
import com.lucasspeer.addaglanceweather.models.ForecastResponse
import com.lucasspeer.addaglanceweather.models.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val apiKey: String) : ViewModel() {

    private val weatherFetcher = WeatherFetcher(apiKey)

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val _forecastData = MutableStateFlow<ForecastResponse?>(null)
    val forecastData: StateFlow<ForecastResponse?> = _forecastData

    fun loadWeather(location: String) {
        viewModelScope.launch {
            weatherFetcher.fetchWeather(location) { weather ->
                _weatherData.value = weather
            }

            weatherFetcher.fetchForecast(location) { forecast ->
                _forecastData.value = forecast
            }
        }
    }
}

class WeatherViewModelFactory(private val apiKey: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(
                apiKey,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
