package com.example.ssp.repository

import com.example.ssp.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiInterface {
    //Patients
    @GET("persona?ejemplo=%7B%22soloUsuariosDelSistema%22%3Atrue%7D")
    suspend fun getAllPhysiotherapy(): Response<People>

    @GET("persona?ejemplo=%7B%22soloUsuariosDelSistema%22%3Anull%7D")
    suspend fun getAllPatients(): Response<People>

    @POST("persona")
    fun postPerson(@Body person: Person): Call<Person>

    @PUT("persona")
    fun putPerson(@Body person: Person): Call<Person>

    @Headers("Content-Type: application/json", "usuario: usuario1")
    @DELETE("persona/{id}")
    fun deletePerson(@Path("id") idPerson: Int): Call<Person>


    // Reservations
    @GET
    fun getReservations(@Url url:String): Call<Reservations>

    @GET
    fun getFreeReservations(@Url url:String): Call<ArrayList<Reservation>>

    @Headers("Content-Type: application/json", "usuario: usuario1")
    @POST("reserva")
    fun postReservation(@Body reservation: NewReservation): Call<NewReservation>

    @Headers("Content-Type: application/json", "usuario: usuario1")
    @PUT("reserva")
    fun putReservation(@Body reservation: Reservation): Call<Reservation>

    @Headers("Content-Type: application/json", "usuario: usuario1")
    @DELETE("reserva/{id}")
    fun deleteReservation(@Path("id") idReservation: Int): Call<NewReservation>


    // Data Sheets
    @GET
    fun getDataSheets(@Url url: String): Call<DataSheets>

    @POST("fichaClinica")
    fun postDataSheet(@Body dataSheet: DataSheet): Call<DataSheet>

}