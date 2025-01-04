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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucasspeer.addaglanceweather.ui.theme.AddaGlanceWeatherTheme
import com.lucasspeer.addaglanceweather.weatherCard.WeatherCard
import com.lucasspeer.addaglanceweather.weatherCard.WeatherDescription
import com.lucasspeer.addaglanceweather.weatherCard.WeatherState
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

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
                        CurrentWeatherCard()
                        Spacer(modifier = Modifier.height(12.dp))
                        WeatherCard(state = WeatherState(
                            "Tomorrow",
                            WeatherDescription.OVERCAST,
                            20,
                            LocalDate.now().plusDays(1)
                        ))
                        Spacer(modifier = Modifier.height(12.dp))
                        GenericWeatherCard(
                            LocalDate.now().plusDays(2),
                            WeatherDescription.PARTLY_CLOUDY,
                            16
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        GenericWeatherCard(
                            LocalDate.now().plusDays(3),
                            WeatherDescription.RAIN,
                            12
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        GenericWeatherCard(
                            LocalDate.now().plusDays(4),
                            WeatherDescription.SNOW,
                            -2
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GenericWeatherCard(date: LocalDate, description: WeatherDescription, temp: Int) {
    return WeatherCard(state = WeatherState(
        date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US),
        description,
        temp,
        date,
    ))
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherCard() {
    WeatherCard(state = WeatherState(
        "Today",
        WeatherDescription.SUNNY,
        25,
        LocalDate.now(),
    ))
}