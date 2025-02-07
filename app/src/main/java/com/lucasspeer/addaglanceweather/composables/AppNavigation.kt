package com.lucasspeer.addaglanceweather.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "weather") {
        composable("weather") { WeatherScreen() }
        composable("settings") { SettingsScreen() }
    }
}
