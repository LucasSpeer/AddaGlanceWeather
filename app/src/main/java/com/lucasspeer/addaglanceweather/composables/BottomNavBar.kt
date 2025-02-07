package com.lucasspeer.addaglanceweather.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Settings", "settings", Icons.Default.Settings),
        BottomNavItem("Minneapolis", "weather", null, ),
        BottomNavItem("Add", "add_location", Icons.Default.Add)
    )

    NavigationBar {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { item.icon?.let { Icon(it, contentDescription = item.label) } },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    if (item.route != "add_location") {
                        navController.navigate(item.route)
                    } // TODO: Implement adding locations later
                }
            )
        }
    }
}

data class BottomNavItem(val label: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector?)
