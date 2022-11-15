package com.example.ssp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataSheets(
    @SerializedName("lista") val data: ArrayList<DataSheet>,
    @SerializedName("totalDatos") val totalData: Int
)

data class DataSheet(
    var idFichaClinica: Int?,
    var fechaHora: String?,
    var fechaHoraCadena: String?,
    var motivoConsulta: String?,
    var diagnostico: String?,
    var observacion: String?,
    var idTipoProducto: TypeProduct?,
    var idCliente: Person?,
    var idEmpleado: Person?
) : Serializable

data class TypeProduct(
    var idTipoProducto: Int?,
    var descripcion: String?,
    var idCategoria: Category?
) : Serializable

data class Category(
    var idCategoria: Int?,
    var descripcion: String?
) : Serializable