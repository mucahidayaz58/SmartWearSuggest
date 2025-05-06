package com.greyinc.kiyafetoneriapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.greyinc.kiyafetoneriapp.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel, apiKey: String) {
    var cityName by remember { mutableStateOf("") }
    val weather by viewModel.weather.collectAsState()
    val recommendation by viewModel.recommendation.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFFB2EBF2), Color(0xFFE0F7FA))
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "👕 Kıyafet Öneri Sistemi",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF01579B)
            )

            OutlinedTextField(
                value = cityName,
                onValueChange = { cityName = it },
                label = { Text("İl / İlçe / Köy girin") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.85f)
            )

            Button(
                onClick = { viewModel.loadWeatherByCity(cityName, apiKey) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0288D1)),
                modifier = Modifier.fillMaxWidth(0.85f)
            ) {
                Text("🔍 Sorgula", color = Color.White)
            }

            weather?.let { weatherData ->
                LaunchedEffect(weatherData) {
                    viewModel.getAiClothingAdvice(
                        city = weatherData.name,
                        temp = weatherData.main.temp,
                        desc = weatherData.weather.firstOrNull()?.description ?: ""
                    )
                }

                Card(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("📍 ${weatherData.name}", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(8.dp))
                        Text("🌡 Sıcaklık: ${weatherData.main.temp}°C")
                        Text("🤔 Hissedilen: ${weatherData.main.feels_like}°C")
                        Text("🌤 Durum: ${weatherData.weather.firstOrNull()?.description?.replaceFirstChar { c -> c.uppercase() }}")

                        Spacer(Modifier.height(16.dp))
                        Text("👗 Kıyafet Önerisi", fontWeight = FontWeight.Bold, color = Color(0xFF0288D1))
                        Text(recommendation ?: "Yükleniyor...")
                    }
                }
            } ?: Text(
                "Henüz sorgu yapılmadı veya şehir bulunamadı.",
                color = Color(0xFF757575),
                fontSize = 14.sp
            )
        }
    }
}
