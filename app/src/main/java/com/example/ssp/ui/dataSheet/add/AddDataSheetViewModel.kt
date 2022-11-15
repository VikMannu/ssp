package com.example.ssp.ui.dataSheet.add

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.ssp.model.DataSheet
import com.example.ssp.model.DataSheetBody
import com.example.ssp.repository.ApiInterface
import com.example.ssp.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDataSheetViewModel : ViewModel() {
    fun addDataSheet(dataSheet: DataSheetBody, fragmentActivity: FragmentActivity) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        val call = apiInterface.postDataSheet(dataSheet)

        call.enqueue(object : Callback<DataSheet> {
            override fun onResponse(call: Call<DataSheet>, response: Response<DataSheet>) {
                if (response.isSuccessful) {
                    //your code for handaling success response
                    Toast.makeText(fragmentActivity, "Se cargó correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(fragmentActivity, "No se cargo al nuevo paciente \n Error: ${response.raw().code}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DataSheet>, t: Throwable) {
                Toast.makeText(fragmentActivity, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}