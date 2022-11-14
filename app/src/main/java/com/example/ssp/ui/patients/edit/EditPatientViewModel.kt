package com.example.ssp.ui.patients.edit

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.ssp.model.Person
import com.example.ssp.repository.ApiInterface
import com.example.ssp.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPatientViewModel : ViewModel() {

    fun updatePatient(patient: Person, fragmentActivity: FragmentActivity) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        val call = apiInterface.putPerson(patient)

        call.enqueue(object : Callback<Person> {
            override fun onResponse(call: Call<Person>, response: Response<Person>) {
                if (response.isSuccessful) {
                    //your code for handaling success response
                    Toast.makeText(
                        fragmentActivity,
                        "Los datos del paciente se actualizaron correctament",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    Toast.makeText(
                        fragmentActivity,
                        "No se actualizó los datos \n Error: ${response.raw().code}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Person>, t: Throwable) {
                Toast.makeText(fragmentActivity, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        })
    }

}