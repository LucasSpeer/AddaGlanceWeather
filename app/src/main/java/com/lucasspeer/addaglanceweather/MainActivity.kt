package com.lucasspeer.addaglanceweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.navigation.compose.rememberNavController
import com.lucasspeer.addaglanceweather.composables.AppNavigation
import com.lucasspeer.addaglanceweather.composables.BottomNavBar
import com.lucasspeer.addaglanceweather.ui.theme.AddaGlanceWeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStore = PreferenceDataStoreFactory.create(
            produceFile = { applicationContext.preferencesDataStoreFile("user_preferences") }
        )
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            AddaGlanceWeatherTheme {
                Scaffold(
                    bottomBar = { BottomNavBar(navController) }
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        AppNavigation(navController)
                    }
                }
            }
        }
    }

    companion object {
        lateinit var dataStore: DataStore<Preferences>
    }
}
