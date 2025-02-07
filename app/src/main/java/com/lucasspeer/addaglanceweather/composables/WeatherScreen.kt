package com.lucasspeer.addaglanceweather.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lucasspeer.addaglanceweather.R
import com.lucasspeer.addaglanceweather.viewModels.SettingsViewModel
import com.lucasspeer.addaglanceweather.viewModels.WeatherViewModel
import com.lucasspeer.addaglanceweather.viewModels.WeatherViewModelFactory

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(
            stringResource(R.string.api_key)
        )
    ),
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val weather by viewModel.weatherData.collectAsState()
    val forecast by viewModel.forecastData.collectAsState()
    val useCelsius by settingsViewModel.useCelsius.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWeather("Minneapolis") // Replace with dynamic location
    }

    Column {
        WeatherCard(weather, useCelsius)
        ForecastList(forecast, useCelsius)
    }
}