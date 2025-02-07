package com.lucasspeer.addaglanceweather.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasspeer.addaglanceweather.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    private val repository = UserPreferencesRepository()

    private val _useCelsius = MutableStateFlow(true) // Default to Celsius
    val useCelsius: StateFlow<Boolean> = _useCelsius

    init {
        viewModelScope.launch {
            repository.useCelsius.collect { isCelsius ->
                _useCelsius.value = isCelsius
            }
        }
    }

    fun toggleTemperatureUnit() {
        viewModelScope.launch {
            repository.setUseCelsius(!_useCelsius.value)
        }
    }
}
