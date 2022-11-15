package com.example.ssp.model

import java.io.Serializable


data class DataSheetBody(
    var motivoConsulta: String?,
    var diagnostico: String?,
    var observacion: String?,
    var idEmpleado: PersonBodyA?,
    var idCliente: PersonBodyA?,
    var idTipoProducto: TypeProductA?
) : Serializable

data class PersonBodyA(val idPersona: Int?) : Serializable
data class TypeProductA(val idTipoProducto: Int?) : Serializable