package com.example.filament.core.api

import com.example.filament.core.constants.Config
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiInstance {

    companion object{

        private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder().baseUrl(Config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())


        private val retrofit:Retrofit = retrofitBuilder.build()

        private val httpClient: OkHttpClient.Builder
                = OkHttpClient.Builder()

        fun <T> createService(serviceClass: Class<T>): T {
            return retrofit.create(serviceClass)
        }

    }




}