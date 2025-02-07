package com.lucasspeer.addaglanceweather

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val settingsDataStore = MainActivity.dataStore

class UserPreferencesRepository {
    private val tempUnitKey = booleanPreferencesKey("use_celsius")

    val useCelsius: Flow<Boolean> = settingsDataStore.data
        .map { preferences -> preferences[tempUnitKey] ?: true } // Default to Celsius

    suspend fun setUseCelsius(isCelsius: Boolean) {
        settingsDataStore.edit { preferences ->
            preferences[tempUnitKey] = isCelsius
        }
    }
}
