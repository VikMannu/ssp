package com.example.ssp.ui.dataSheet.list

import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssp.model.*
import com.example.ssp.repository.ApiInterface
import com.example.ssp.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListDataSheetViewModel(
    private val fragmentActivity: FragmentActivity
) : ViewModel() {
    private val _arrayDataSheets = MutableLiveData<ArrayList<DataSheet>>()
    val arrayDataSheets: LiveData<ArrayList<DataSheet>>
        get() = _arrayDataSheets

    init {
        filterDataSheet(fragmentActivity = fragmentActivity)
    }


    fun filterDataSheet(
        idClient: String? = null,
        idEmployee: String? = null,
        idTypeProduct: String? = null,
        startDate: String? = null,
        endDate: String? = null,
        fragmentActivity: FragmentActivity
    ) {
        val baseUrl = "fichaClinica?ejemplo="

        val idClientEncode = if (idClient != null) {
            Uri.encode("\"idCliente\":{\"idPersona\":$idClient},")
        } else {
            "%22idEmpleado%22%3A%7B%22idPersona%22%3Anull%7D%2C"
        }

        val idEmployeeEncode = if (idEmployee != null) {
            Uri.encode("\"idEmpleado\":{\"idPersona\":$idEmployee},")
        } else {
            "%22idCliente%22%3A%7B%22idPersona%22%3Anull%7D%2C"
        }

        val idTypeProductEncode = if (idTypeProduct != null) {
            Uri.encode("\"idTipoProducto\":{\"idTipoProducto\":$idTypeProduct},")
        } else {
            "%22idTipoProducto%22%3A%7B%22idTipoProducto%22%3Anull%7D%2C"
        }

        val startDateEncode = if (startDate != null) {
            Uri.encode("\"fechaDesdeCadena\":$startDate,")
        } else {
            "%22fechaDesdeCadena%22%3Anull%2C"
        }

        val endDateEncode = if(endDate != null) {
            Uri.encode("\"fechaHastaCadena\":$endDate")
        } else {
            "%22fechaHastaCadena%22%3Anull"
        }

        val encodeDataSheet = "$baseUrl%7B$idClientEncode$idEmployeeEncode$idTypeProductEncode$startDateEncode$endDateEncode%7D"

        println(encodeDataSheet)

        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        val call = apiInterface.getDataSheets(encodeDataSheet)

        call.enqueue(object : Callback<DataSheets> {
            override fun onResponse(call: Call<DataSheets>, response: Response<DataSheets>) {
                if (response.isSuccessful) {
                    //your code for handaling success response
                    _arrayDataSheets.value = response.body()?.data

                } else {
                    Toast.makeText(
                        fragmentActivity,
                        "No se pudo obtener las FICHAS \n Error: ${response.raw().code}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<DataSheets>, t: Throwable) {
                Toast.makeText(fragmentActivity, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        })

    }

}