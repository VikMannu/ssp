package com.example.ssp.repository

import com.example.ssp.model.People
import com.example.ssp.model.Person
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("persona?ejemplo=%7B%22soloUsuariosDelSistema%22%3Atrue%7D")
    suspend fun getAllPhysiotherapy(): Response<People>

    @GET("persona?ejemplo=%7B%22soloUsuariosDelSistema%22%3Anull%7D")
    suspend fun getAllPatients(): Response<People>

    @POST("persona")
    fun postPatient(@Body person: Person): Call<Person>

}