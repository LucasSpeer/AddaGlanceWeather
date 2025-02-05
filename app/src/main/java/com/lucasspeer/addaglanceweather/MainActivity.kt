package com.lucasspeer.addaglanceweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lucasspeer.addaglanceweather.ui.theme.AddaGlanceWeatherTheme
import com.lucasspeer.addaglanceweather.weatherCard.WeatherCard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel

class WeatherViewModel(private val apiKey: String) : ViewModel() {

    private val weatherFetcher = WeatherFetcher(apiKey)

    private val _weatherData = MutableLiveData<WeatherResponse?>()
    val weatherData: LiveData<WeatherResponse?> = _weatherData

    fun loadWeather(location: String) {
        viewModelScope.launch {
            weatherFetcher.fetchWeather(location) { weather ->
                _weatherData.postValue(weather)
            }
        }
    }
}

var useCelcius: Boolean = false

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AddaGlanceWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)) {
                        WeatherScreen()
                    }
                }
            }
        }
    }
}


@Composable
fun WeatherScreen(viewModel: WeatherViewModel = viewModel(factory = WeatherViewModelFactory(
    stringResource(R.string.api_key)
))) {
    val weather by viewModel.weatherData.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWeather("Minneapolis") // Replace with dynamic location if needed
    }

    WeatherCard(weather)
}

class WeatherViewModelFactory(private val apiKey: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(apiKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}