package com.lucasspeer.addaglanceweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lucasspeer.addaglanceweather.ui.theme.AddaGlanceWeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AddaGlanceWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CurrentWeatherCard(modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Composable
fun CurrentWeatherCard(modifier: Modifier = Modifier) {
    // Add padding around our message
    Column( modifier =  modifier.background(color = MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(8.dp))
        .height(height = 150.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth().fillMaxHeight()
        ) {
            Icon(
                painter = painterResource(R.drawable.outline_wb_sunny_100),
                contentDescription = "Current Weather Icon", //TODO add weather
                Modifier.size(42.dp)
            )
            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(8.dp))
            Column (modifier = Modifier.fillMaxHeight()){
                Text(text = "Sunny", color = MaterialTheme.colorScheme.primary, fontSize = 24.sp, fontFamily = FontFamily.Default, fontWeight = FontWeight.Bold)
                // Add a vertical space between the author and message texts
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "84°F", color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}