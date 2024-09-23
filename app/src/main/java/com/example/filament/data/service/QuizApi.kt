package com.example.filament.data.service

import com.example.filament.data.models.response.QuizResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

    @GET("api.php")
    fun getQuizCollection(@Query("amount") amount: Int): Call<QuizResponse>

}