package com.example.ssp.repository

import com.example.ssp.model.Patients
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("")
    suspend fun getAllPatiens(): Response<Patients>
}