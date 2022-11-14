package com.example.ssp.ui.home

import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssp.model.*
import com.example.ssp.repository.ApiInterface
import com.example.ssp.repository.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class HomeActivityViewModel : ViewModel() {

    // Patients properties
    private val _arrayPatients = MutableLiveData<ArrayList<Person>>()
    val arrayPatients: LiveData<ArrayList<Person>>
        get() = _arrayPatients

    private val listAllPatients = ArrayList<Person>()
    private val listAllPatientsFilter = ArrayList<Person>()

    // Physiotherapy properties
    private val _arrayPhysiotherapy = MutableLiveData<ArrayList<Person>>()
    val arrayPhysiotherapy: LiveData<ArrayList<Person>>
        get() = _arrayPhysiotherapy

    private val listAllPhysiotherapy = ArrayList<Person>()
    private val listAllPhysiotherapyFilter = ArrayList<Person>()

    // Reservations properties
    private val _arrayReservations = MutableLiveData<ArrayList<Reservation>>()
    val arrayReservations: LiveData<ArrayList<Reservation>>
        get() = _arrayReservations

    private val listAllReservations = ArrayList<Reservation>()

    private val _physiotherapy = MutableLiveData<Person>()
    val physiotherapy: LiveData<Person>
        get() = _physiotherapy

    private val _patient = MutableLiveData<Person>()
    val patient: LiveData<Person>
        get() = _patient

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String>
        get() = _startDate

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String>
        get() = _endDate

    init {
        // Load Patients
        loadPatients()

        // Load Physiotherapy
        loadPhysiotherapy()
    }

    fun loadPatients() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiInterface.getAllPatients()
                if (response.isSuccessful) {
                    //your code for handaling success response
                    listAllPatients.clear()
                    response.body()?.data?.let { listAllPatients.addAll(it) }

                    listAllPatientsFilter.clear()
                    listAllPatientsFilter.addAll(listAllPatients)

                    _arrayPatients.value = listAllPatients
                }
            } catch (Ex: Exception) {
                Ex.localizedMessage?.let { Log.e("Error", it) }
            }
        }
    }

    private fun loadPhysiotherapy() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiInterface.getAllPhysiotherapy()
                if (response.isSuccessful) {
                    //your code for handaling success response
                    listAllPhysiotherapy.clear()
                    response.body()?.data?.let { listAllPhysiotherapy.addAll(it) }

                    listAllPhysiotherapyFilter.clear()
                    listAllPhysiotherapyFilter.addAll(listAllPhysiotherapy)

                    _arrayPhysiotherapy.value = listAllPhysiotherapy
                }
            } catch (Ex: Exception) {
                Ex.localizedMessage?.let { Log.e("Error", it) }
            }
        }
    }

    fun getPatient(position: Int): Person {
        return this.listAllPatientsFilter[position]
    }

    fun getPhysiotherapy(position: Int): Person {
        return this.listAllPhysiotherapyFilter[position]
    }

    fun getReservation(position: Int): Reservation {
        return this.listAllReservations[position]
    }

    fun setPhysiotherapy(physiotherapy: Person, fragmentActivity: FragmentActivity) {
        filterReservations(
            physiotherapist = physiotherapy,
            patient = this.patient.value,
            startDate = this.startDate.value,
            endDate = this.endDate.value,
            fragmentActivity = fragmentActivity
        )
        this._physiotherapy.value = physiotherapy
    }

    fun setPatient(patient: Person, fragmentActivity: FragmentActivity) {
        filterReservations(
            physiotherapist = this.physiotherapy.value,
            patient = patient,
            startDate = this.startDate.value,
            endDate = this.endDate.value,
            fragmentActivity = fragmentActivity
        )
        this._patient.value = patient
    }

    fun setStartDate(startDate: String, fragmentActivity: FragmentActivity) {
        filterReservations(
            physiotherapist = this.physiotherapy.value,
            patient = this.patient.value,
            startDate = startDate,
            endDate = this.endDate.value,
            fragmentActivity = fragmentActivity
        )

        this._startDate.value = startDate
    }

    fun setEndDate(endDate: String, fragmentActivity: FragmentActivity) {
        filterReservations(
            physiotherapist = this._physiotherapy.value,
            patient = this.patient.value,
            startDate = this.startDate.value,
            endDate = endDate,
            fragmentActivity = fragmentActivity
        )

        this._endDate.value = endDate
    }

    fun onQueryTextChangePatients(newText: String): Boolean {
        with(this.listAllPatientsFilter) { clear() }
        val searchText = newText.lowercase()
        if (searchText.isNotEmpty()) {
            this.listAllPatients.forEach {
                if (it.cedula != null) {
                    val ci = it.cedula.lowercase()
                    val name = "${it.nombre} ${it.apellido}".lowercase()
                    if (ci.contains(searchText) || name.contains(searchText)) {
                        this.listAllPatientsFilter.add(it)
                    }
                }
            }
        } else {
            this.listAllPatientsFilter.clear()
            this.listAllPatientsFilter.addAll(this.listAllPatients)
        }
        this._arrayPatients.value = this.listAllPatientsFilter
        return false
    }

    fun updateReservation(reservation: Reservation): Boolean {
        return true
    }

    fun onQueryTextChangePhysiotherapy(newText: String): Boolean {
        with(this.listAllPhysiotherapyFilter) { clear() }
        val searchText = newText.lowercase()
        if (searchText.isNotEmpty()) {
            this.listAllPhysiotherapy.forEach {
                if (it.cedula != null) {
                    val ci = it.cedula.lowercase()
                    val name = "${it.nombre} ${it.apellido}".lowercase()
                    if (ci.contains(searchText) || name.contains(searchText)) {
                        this.listAllPhysiotherapyFilter.add(it)
                    }
                }
            }
        } else {
            this.listAllPhysiotherapyFilter.clear()
            this.listAllPhysiotherapyFilter.addAll(this.listAllPatients)
        }
        this._arrayPhysiotherapy.value = this.listAllPhysiotherapyFilter
        return false
    }

    fun cancelReservation(position: Int) {
        println("Deleted $position")
    }

    fun resetPatients() {
        _arrayPatients.value = listAllPatients
    }

    fun resetPhysiotherapy() {
        _arrayPhysiotherapy.value = listAllPhysiotherapy
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun resetReservations(account: Person, fragmentActivity: FragmentActivity) {
        val local = LocalDateTime.now()
        val date = local.format(DateTimeFormatter.ISO_DATE).replace("-", "")

        filterReservations(
            physiotherapist = account,
            startDate = date,
            fragmentActivity = fragmentActivity
        )

        this._physiotherapy.value = account
        this._patient.value = null
        this._startDate.value = date

        this._endDate.value = null
    }

    private fun filterReservations(
        physiotherapist: Person? = null,
        patient: Person? = null,
        startDate: String? = null,
        endDate: String? = null,
        fragmentActivity: FragmentActivity
    ) {
        var physiotherapistEncode = ""
        var patientEncode = ""
        var startDateEncode = ""
        var endDateEncode = ""

        physiotherapistEncode = if (physiotherapist != null) {
            Uri.encode("{\"idEmpleado\":{\"idPersona\":${physiotherapist.idPersona}},")
        } else {
            "%7B%22idEmpleado%22%3A%7B%22idPersona%22%3Anull%7D%2C"
        }

        patientEncode = if (patient != null) {
            Uri.encode("\"idCliente\":{\"idPersona\":${patient.idPersona}},")
        } else {
            "%22idCliente%22%3A%7B%22idPersona%22%3Anull%7D%2C"
        }

        startDateEncode = if (startDate != null) {
            Uri.encode("\"fechaDesdeCadena\":$startDate,")
        } else {
            "%22fechaDesdeCadena%22%3Anull%2C"
        }

        endDateEncode = if (endDate != null) {
            Uri.encode("\"fechaHastaCadena\":$endDate}")
        } else {
            "%22fechaHastaCadena%22%3Anull%7D"
        }

        val encodeReservation =
            physiotherapistEncode + patientEncode + startDateEncode + endDateEncode

        println(encodeReservation)

        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        val call = apiInterface.getReservations("reserva?ejemplo=$encodeReservation")

        call.enqueue(object : Callback<Reservations> {
            override fun onResponse(call: Call<Reservations>, response: Response<Reservations>) {
                if (response.isSuccessful) {
                    //your code for handaling success response
                    listAllReservations.clear()
                    response.body()?.let { listAllReservations.addAll(it.data) }

                    _arrayReservations.value = listAllReservations

                } else {
                    Toast.makeText(
                        fragmentActivity,
                        "No se pudo obtener las reservaciones \n Error: ${response.raw().code}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Reservations>, t: Throwable) {
                Toast.makeText(fragmentActivity, "Ocurrio un error", Toast.LENGTH_SHORT).show()
            }
        })

    }
}