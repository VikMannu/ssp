package com.example.ssp.model

import java.io.Serializable

data class People(
    val data: ArrayList<Person>
) : Serializable

data class Person(
    val idPersona: Int,
    val nombre: String,
    val apellido: String,
    val email: String,
    val telefono: String,
    val ruc: String,
    val cedula: String,
    val tipoPersona: String,
    val fechaNacimiento: String,
    val soloUsuariosDelSistema: Boolean
) : Serializable