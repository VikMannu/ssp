package com.example.ssp.model

import java.io.Serializable

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