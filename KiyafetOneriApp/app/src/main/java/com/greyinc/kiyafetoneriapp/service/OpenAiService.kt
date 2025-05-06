package com.greyinc.kiyafetoneriapp.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object OpenAiService {
    private const val apiKey = "API_KEY"

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openai.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(
            OkHttpClient.Builder().addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $apiKey")
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(request)
            }.build()
        )
        .build()

    val api: OpenAiApi = retrofit.create(OpenAiApi::class.java)
}
