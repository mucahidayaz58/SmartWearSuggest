package com.greyinc.kiyafetoneriapp.service

import com.greyinc.kiyafetoneriapp.data.OpenAiRequest
import com.greyinc.kiyafetoneriapp.data.OpenAiResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {
    @Headers("Content-Type: application/json")
    @POST("responses")
    suspend fun getClothingAdvice(@Body request: OpenAiRequest): OpenAiResponse
}

