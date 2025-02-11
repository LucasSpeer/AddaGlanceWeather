package com.lucasspeer.addaglanceweather.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lucasspeer.addaglanceweather.R
import com.lucasspeer.addaglanceweather.getFormattedTemperature
import com.lucasspeer.addaglanceweather.models.WeatherResponse
import com.lucasspeer.addaglanceweather.ui.theme.Aero
import com.lucasspeer.addaglanceweather.ui.theme.DavysGrey
import com.lucasspeer.addaglanceweather.ui.theme.OxfordBlue
import com.lucasspeer.addaglanceweather.ui.theme.PayneGrey
import com.lucasspeer.addaglanceweather.ui.theme.RaisinBlack


enum class WeatherDescription {
    SUNNY {
        override fun description(): String {
            return "Sunny"
        }

        override fun bgColor(): Color {
            return Aero
        }

        @Composable
        override fun Icon() {
            return BuildIcon(R.drawable.outline_wb_sunny_100, description())
        }
    },

    OVERCAST {
        override fun description(): String {
            return "Overcast"
        }

        override fun bgColor(): Color {
            return RaisinBlack
        }

        @Composable
        override fun Icon() {
            return BuildIcon(R.drawable.outline_wb_sunny_100, description())
        }
    },

    PARTLY_CLOUDY {
        override fun description(): String {
            return "Partly Cloudy"
        }

        override fun bgColor(): Color {
            return DavysGrey
        }

        @Composable
        override fun Icon() {
            return BuildIcon(R.drawable.outline_cloud_100, description())
        }
    },

    RAIN {
        override fun description(): String {
            return "Rain"
        }

        override fun bgColor(): Color {
            return OxfordBlue
        }

        @Composable
        override fun Icon() {
            return BuildIcon(R.drawable.outline_rainy_100, description())
        }
    },

    SNOW {
        override fun description(): String {
            return "Snow"
        }

        override fun bgColor(): Color {
            return PayneGrey
        }

        @Composable
        override fun Icon() {
            return BuildIcon(R.drawable.outline_cloud_100, description())   //Todo Snow icon
        }
    };

    @Composable
    fun BuildIcon(id: Int, description: String) {
        return Icon(
            painter = painterResource(id),
            contentDescription = "Current Weather Icon: $description", //TODO add weather
            Modifier.size(42.dp)
        )
    }

    abstract fun description(): String

    abstract fun bgColor(): Color

    @Composable
    abstract fun Icon()
}

@Composable
fun WeatherCard(weather: WeatherResponse?, useCelsius: Boolean) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            if (weather != null) {
                Text(text = weather.location.name, style = MaterialTheme.typography.headlineSmall)
                Text(text = getFormattedTemperature(weather.current.tempC, useCelsius), style = MaterialTheme.typography.headlineMedium)
                Text(text = weather.current.condition.description, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Cloud Cover: ${weather.current.cloud}%", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Humidity: ${weather.current.humidity}%", style = MaterialTheme.typography.bodyMedium)
            } else {
                Text(text = "Loading weather...", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

//@Composable
//fun WeatherCardOld(state: WeatherState) {
//    Column( modifier =  Modifier.height(height = 120.dp)
//    ) {
//        Text(
//            text = state.title,
//            color = MaterialTheme.colorScheme.tertiary,
//            fontWeight = FontWeight.Bold)
//        Spacer(modifier = Modifier.height(4.dp))
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier.fillMaxWidth()
//                .fillMaxHeight()
//                .background(
//                    color = state.weatherDescription.bgColor(),
//                    shape = RoundedCornerShape(8.dp))
//        ) {
//            Spacer(modifier = Modifier.width(8.dp))
//            state.weatherDescription.Icon()
//            // Add a horizontal space between the image and the column
//            Spacer(modifier = Modifier.width(8.dp))
//            Column (modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center){
//                Text(
//                    text = state.weatherDescription.description(),
//                    color = MaterialTheme.colorScheme.primary,
//                    fontSize = 24.sp,
//                    fontFamily = FontFamily.Default,
//                    fontWeight = FontWeight.Bold
//                )
//                // Add a vertical space between the author and message texts
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = state.tempString(),
//                    color = MaterialTheme.colorScheme.primary
//                )
//            }
//        }
//    }
//}