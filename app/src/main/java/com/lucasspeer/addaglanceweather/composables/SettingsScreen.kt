package com.lucasspeer.addaglanceweather.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucasspeer.addaglanceweather.viewModels.SettingsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = SettingsViewModel()) {
    val useCelsius by viewModel.useCelsius.collectAsState()

    Scaffold {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Settings", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.tertiary)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Use Celsius")
                Switch(
                    checked = useCelsius,
                    onCheckedChange = { viewModel.toggleTemperatureUnit() }
                )
            }
        }
    }
}
