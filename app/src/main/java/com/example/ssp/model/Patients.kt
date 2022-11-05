package com.example.ssp.model

import java.io.Serializable


data class Patients(
    val data: ArrayList<Patient>
): Serializable

data class Patient(
    val nombre: String,
    val apellido: String,
    val email: String,
    val telefono: String,
    val ruc: String,
    val cedula: String,
    val tipoPersona: String,
    val fechaNacimiento: String
): Serializable
