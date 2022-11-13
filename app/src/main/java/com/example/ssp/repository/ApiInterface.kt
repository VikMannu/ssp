package com.example.ssp.repository

import com.example.ssp.model.People
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("persona?ejemplo=%7B%22soloUsuariosDelSistema%22%3Atrue%7D")
    suspend fun getAllPhysiotherapy(): Response<People>

    @GET("persona?ejemplo=%7B%22soloUsuariosDelSistema%22%3Anull%7D")
    suspend fun getAllPatients(): Response<People>
}