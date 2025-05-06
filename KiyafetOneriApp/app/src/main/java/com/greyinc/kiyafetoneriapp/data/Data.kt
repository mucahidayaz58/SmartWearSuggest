package com.greyinc.kiyafetoneriapp.data

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val name: String
)

data class Main(
    val temp: Double,
    val feels_like: Double
)

data class Weather(
    val description: String,
    val icon: String
)

data class OpenAiRequest(
    val model: String = "gpt-4.1",
    val input: String
)

data class OpenAiResponse(
    val choices: List<Choice>
)

data class Choice(val message: Message)

data class Message(val role: String, val content: String)
