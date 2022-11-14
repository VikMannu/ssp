package com.example.ssp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Reservations(
    @SerializedName("lista") val data: ArrayList<Reservation>,
    @SerializedName("totalDatos") val totalData: Int
) : Serializable

data class Reservation(
    val idReserva: Int,
    val fechaCadena: String,
    val horaInicioCadena: String,
    val horaFinCadena: String,
    val idEmpleado: Person,
    val idCliente: Person,
    val observacion: String,
    val flagAsistio: String
) : Serializable