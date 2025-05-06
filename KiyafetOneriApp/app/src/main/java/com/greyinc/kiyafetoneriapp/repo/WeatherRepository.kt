package com.greyinc.kiyafetoneriapp.repo

import com.greyinc.kiyafetoneriapp.data.WeatherResponse
import com.greyinc.kiyafetoneriapp.service.WeatherApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {
    private val api: WeatherApi = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    suspend fun fetchWeatherByCity(cityName: String, apiKey: String): WeatherResponse {
        return api.getCurrentWeatherByCity(cityName, apiKey)
    }

}
