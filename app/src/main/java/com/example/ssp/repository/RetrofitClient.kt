package com.example.ssp.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://equipoyosh.com/stock-nutrinatalia/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}