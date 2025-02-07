package com.lucasspeer.addaglanceweather.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lucasspeer.addaglanceweather.getFormattedTemperature
import com.lucasspeer.addaglanceweather.models.ForecastResponse
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ForecastList(forecast: ForecastResponse?, useCelsius: Boolean) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // Input format
    val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault()) // Output format (Monday, Tuesday, etc.)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Next 2 Days", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.tertiary)

        forecast?.forecast?.days?.forEachIndexed { index, day ->
            // First day in forecast response is today, which is already shown
            if(index != 0) {
                val date = dateFormat.parse(day.date) // Convert string to Date
                val dayOfWeek =
                    date?.let { dayFormat.format(it) } ?: day.date // Convert Date to day name

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Column {
                            Text(
                                text = dayOfWeek,
                                style = MaterialTheme.typography.bodyLarge
                            ) // Show day name
                            Text(
                                text = "${
                                    getFormattedTemperature(
                                        day.day.maxTempC,
                                        useCelsius
                                    )
                                } / ${getFormattedTemperature(day.day.minTempC, useCelsius)}"
                            )
                            Text(text = day.day.condition.description)
                        }
                    }
                }
            }
        } ?: Text(text = "Loading forecast...", style = MaterialTheme.typography.bodyLarge)
    }
}