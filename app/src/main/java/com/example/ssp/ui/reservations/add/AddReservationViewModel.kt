package com.example.ssp.ui.reservations.add

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.ssp.model.NewReservation
import com.example.ssp.model.PersonBody
import com.example.ssp.model.Reservation
import com.example.ssp.repository.ApiInterface
import com.example.ssp.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddReservationViewModel : ViewModel() {

    fun addReservation(reservation: Reservation, fragmentActivity: FragmentActivity) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        val body = NewReservation(
            PersonBody(reservation.idEmpleado.idPersona),
            PersonBody(reservation.idCliente.idPersona),
            reservation.fechaCadena,
            reservation.horaInicioCadena,
            reservation.horaFinCadena
        )

        val call = apiInterface.postReservation(body)

        call.enqueue(object : Callback<NewReservation> {
            override fun onResponse(call: Call<NewReservation>, response: Response<NewReservation>) {
                if (response.isSuccessful) {
                    //your code for handaling success response
                    Toast.makeText(fragmentActivity, "Se cargo correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(fragmentActivity, "No se cargo al nuevo paciente \n Error: ${response.raw().code}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewReservation>, t: Throwable) {
                Toast.makeText(fragmentActivity, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        })
    }

}