package com.example.ssp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Reservations(
    @SerializedName("lista") val data: ArrayList<Reservation>,
    @SerializedName("totalDatos") val totalData: Int
) : Serializable

data class Reservation(
    var idReserva: Int,
    var fechaCadena: String,
    var horaInicioCadena: String,
    var horaFinCadena: String,
    var idEmpleado: Person,
    var idCliente: Person,
    var observacion: String,
    var flagAsistio: String
) : Serializable {

    override fun hashCode(): Int {
        val result = idReserva.hashCode()
        if (fechaCadena.isNullOrEmpty()) {
            fechaCadena = ""
        }
        if (horaInicioCadena.isNullOrEmpty()) {
            horaInicioCadena = ""
        }
        if (horaFinCadena.isNullOrEmpty()) {
            horaFinCadena = ""
        }
        if (idEmpleado == null) {
            idEmpleado = Person(null, "", "", "", "", "", "", "", "", false)
        }
        if (idCliente == null) {
            idCliente = Person(null, "", "", "", "", "", "", "", "", false)
        }
        if (observacion.isNullOrEmpty()) {
            observacion = ""
        }
        if (flagAsistio.isNullOrEmpty()) {
            flagAsistio = ""
        }
        return result
    }
}