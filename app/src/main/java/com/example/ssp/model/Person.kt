package com.example.ssp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class People(
    @SerializedName("lista") val data: ArrayList<Person>,
    @SerializedName("totalDatos") val totalData: Int
) : Serializable

data class Person(
    val idPersona: Int?,
    var nombre: String,
    var apellido: String,
    var email: String,
    var telefono: String,
    var ruc: String,
    var cedula: String,
    var tipoPersona: String,
    var fechaNacimiento: String,
    var soloUsuariosDelSistema: Boolean?
) : Serializable {
    override fun hashCode(): Int {
        val result = idPersona.hashCode()
        if (nombre.isNullOrEmpty()) {
            nombre = ""
        }
        if (apellido.isNullOrEmpty()) {
            apellido = ""
        }
        if (email.isNullOrEmpty()) {
            email = ""
        }
        if (telefono.isNullOrEmpty()) {
            telefono = ""
        }
        if (ruc.isNullOrEmpty()) {
            ruc = ""
        }
        if (cedula.isNullOrEmpty()) {
            cedula = ""
        }
        if (tipoPersona.isNullOrEmpty()) {
            tipoPersona = ""
        }
        if (fechaNacimiento.isNullOrEmpty()) {
            fechaNacimiento = ""
        }
        if (soloUsuariosDelSistema == null) {
            soloUsuariosDelSistema = false
        }
        return result
    }
}