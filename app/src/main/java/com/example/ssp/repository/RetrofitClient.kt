package com.example.ssp.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getInstance(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl("https://equipoyosh.com/stock-nutrinatalia/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}