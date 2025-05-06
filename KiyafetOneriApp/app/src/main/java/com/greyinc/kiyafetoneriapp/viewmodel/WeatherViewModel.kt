package com.greyinc.kiyafetoneriapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greyinc.kiyafetoneriapp.data.OpenAiRequest
import com.greyinc.kiyafetoneriapp.data.WeatherResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.greyinc.kiyafetoneriapp.service.OpenAiService
import com.greyinc.kiyafetoneriapp.service.WeatherServiceInstance

class WeatherViewModel : ViewModel() {

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _recommendation = MutableStateFlow<String?>(null)
    val recommendation: StateFlow<String?> = _recommendation

    fun loadWeatherByCity(cityName: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val weatherResponse = WeatherServiceInstance.api.getCurrentWeatherByCity(cityName, apiKey)
                _weather.value = weatherResponse
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getAiClothingAdvice(city: String, temp: Double, desc: String) {
        viewModelScope.launch {
            try {
                val prompt = "Bugün $city'de hava $temp derece, $desc. Ne giymeliyim? Detaylı kıyafet önerisi verir misin?"
                val request = OpenAiRequest(input = prompt)

                val response = OpenAiService.api.getClothingAdvice(request)
                val clothingAdvice = response.choices.firstOrNull()?.message?.content
                _recommendation.value = clothingAdvice ?: "Kıyafet önerisi alınamadı."
            } catch (e: Exception) {
                e.printStackTrace()
                _recommendation.value = "Kıyafet önerisi alınamadı."
            }
        }
    }
}
