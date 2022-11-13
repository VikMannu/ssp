package com.example.ssp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class People(
    @SerializedName("lista")
    val data: ArrayList<Person>,

    @SerializedName("totalDatos")
    val totalData: Int
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