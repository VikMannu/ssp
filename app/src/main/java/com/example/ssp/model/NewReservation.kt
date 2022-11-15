package com.example.ssp.model

import java.io.Serializable


data class NewReservation(
    val idEmpleado: PersonReservation,
    val idCliente: PersonReservation,
    val fechaCadena: String,
    val horaInicioCadena: String,
    val horaFinCadena: String
)

data class PersonReservation (val idPersona: Int?): Serializable
