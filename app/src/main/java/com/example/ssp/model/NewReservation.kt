package com.example.ssp.model

import java.io.Serializable


data class NewReservation(
    val idEmpleado: PersonBody,
    val idCliente: PersonBody,
    val fechaCadena: String,
    val horaInicioCadena: String,
    val horaFinCadena: String
) : Serializable

data class PersonBody(val idPersona: Int?) : Serializable
