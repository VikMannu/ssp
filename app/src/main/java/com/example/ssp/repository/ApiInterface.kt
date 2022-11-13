package com.example.ssp.repository

import com.example.ssp.model.People
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/persona")
    suspend fun getAllPhysiotherapy(): Response<People>
}