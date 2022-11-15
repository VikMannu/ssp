package com.example.ssp.ui.reservations.edit

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.ssp.model.Person
import com.example.ssp.model.Reservation
import com.example.ssp.repository.ApiInterface
import com.example.ssp.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditReservationViewModel : ViewModel() {
    fun updateReservation(reservation: Reservation, fragmentActivity: FragmentActivity) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        val call = apiInterface.putReservation(reservation)

        call.enqueue(object : Callback<Reservation> {
            override fun onResponse(call: Call<Reservation>, response: Response<Reservation>) {
                if (response.isSuccessful) {
                    //your code for handaling success response
                    Toast.makeText(
                        fragmentActivity,
                        "Los datos de la reservación se actualizaron correctament",
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

            override fun onFailure(call: Call<Reservation>, t: Throwable) {
                Toast.makeText(fragmentActivity, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}