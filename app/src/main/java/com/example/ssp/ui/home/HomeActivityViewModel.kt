package com.example.ssp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssp.model.Person
import com.example.ssp.model.Reservation
import java.util.*
import kotlin.collections.ArrayList

class HomeActivityViewModel: ViewModel() {

    // Patients properties
    private val _arrayPatients = MutableLiveData<ArrayList<Person>>()
    val arrayPatients: LiveData<ArrayList<Person>>
        get() = _arrayPatients

    private val listAllPatients = ArrayList<Person>()
    private val listAllPatientsFilter = ArrayList<Person>()

    // Physiotherapy properties
    private val _arrayPhysiotherapy = MutableLiveData<ArrayList<Person>>()
    val arrayPhysiotherapy: LiveData<ArrayList<Person>>
        get() = _arrayPatients

    private val listAllPhysiotherapy = ArrayList<Person>()
    private val listAllPhysiotherapyFilter = ArrayList<Person>()

    // Reservations properties
    private val _arrayReservations = MutableLiveData<ArrayList<Reservation>>()
    val arrayReservations: LiveData<ArrayList<Reservation>>
        get() = _arrayReservations

    private val _employee = MutableLiveData<Person>()
    val employee: LiveData<Person>
        get() = _employee

    private val _patient = MutableLiveData<Person>()
    val patient: LiveData<Person>
        get() = _patient

    private val _startDate = MutableLiveData<String>()
    val startDate: LiveData<String>
        get() = _startDate

    private val _endDate = MutableLiveData<String>()
    val endDate: LiveData<String>
        get() = _endDate

    private val listAllReservations = ArrayList<Reservation>()
    private val listAllReservationsFilter = ArrayList<Reservation>()

    init {
        // Load Patients
        listAllPatients.addAll(loadPatients())
        listAllPatientsFilter.addAll(loadPatients())
        _arrayPatients.value = listAllPatients

        // Load Physiotherapy
        listAllPhysiotherapy.addAll(loadPhysiotherapy())
        listAllPhysiotherapyFilter.addAll(loadPhysiotherapy())
        _arrayPhysiotherapy.value = listAllPhysiotherapy

        // Load Reservations
        listAllReservations.addAll(loadReservation())
        listAllReservationsFilter.addAll(loadReservation())
        _arrayReservations.value = listAllReservations
    }

    private fun loadPatients(): ArrayList<Person> {
        val patient1 = Person(1,"Victor Manuel", "Ayala Acosta", "victor.ayala2a@gmail.com", "0982485713", "4692858-0", "4692858", "FISICA", "1998-05-09 00:00:00", true)
        val patient2 = Person(2,"Manuel Victor", "Acosta Ayala", "ayala.victor2a@gmail.com", "0982485714", "4692859-1", "4692859", "FISICA", "1998-05-10 00:00:00", true)

        val list = ArrayList<Person>()
        list.add(patient1)
        list.add(patient2)

        return list
    }

    private fun loadPhysiotherapy(): ArrayList<Person> {
        val patient1 = Person(1,"Victor Manuel", "Ayala Acosta", "victor.ayala2a@gmail.com", "0982485713", "4692858-0", "4692858", "FISICA", "1998-05-09 00:00:00", true)
        val patient2 = Person(2,"Manuel Victor", "Acosta Ayala", "ayala.victor2a@gmail.com", "0982485714", "4692859-1", "4692859", "FISICA", "1998-05-10 00:00:00", true)

        val list = ArrayList<Person>()
        list.add(patient1)
        list.add(patient2)

        return list
    }

    private fun loadReservation(): ArrayList<Reservation> {
        val patient1 = Person(1,"Victor Manuel", "Ayala Acosta", "victor.ayala2a@gmail.com", "0982485713", "4692858-0", "4692858", "FISICA", "1998-05-09 00:00:00", true)
        val patient2 = Person(2,"Manuel Victor", "Acosta Ayala", "ayala.victor2a@gmail.com", "0982485714", "4692859-1", "4692859", "FISICA", "1998-05-10 00:00:00", true)

        val reservation1 = Reservation(1,"20190903", "1000", "1015", patient1, patient2, "Ninguna", "true")
        val reservation2 = Reservation(2,"20201004", "1000", "1015", patient1, patient2, "Ninguna", "true")

        val list = ArrayList<Reservation>()
        list.add(reservation1)
        list.add(reservation2)

        return list
    }

    fun getPatient(position: Int): Person {
        return this.listAllPatientsFilter[position]
    }

    fun getPhysiotherapy(position: Int): Person {
        return this.listAllPhysiotherapyFilter[position]
    }

    fun getReservation(position: Int): Reservation {
        return this.listAllReservationsFilter[position]
    }

    fun editReservation(reservation: Reservation): Boolean {
        return true
    }

    fun onQueryTextChange(newText: String): Boolean {
        with(this.listAllPatientsFilter) { clear() }
        val searchText = newText.lowercase(Locale.getDefault())
        if (searchText.isNotEmpty()) {
            this.listAllPatients.forEach {
                val ci = it.cedula.lowercase(Locale.getDefault())
                val name =
                    "${it.nombre} ${it.apellido}".lowercase(
                        Locale.getDefault()
                    )
                if (ci.contains(searchText) || name.contains(searchText)) {
                    this.listAllPatientsFilter.add(it)
                }
            }
        } else {
            this.listAllPatientsFilter.clear()
            this.listAllPatientsFilter.addAll(this.listAllPatients)
        }
        this._arrayPatients.value = this.listAllPatientsFilter
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

    fun resetReservations() {
        _arrayReservations.value = listAllReservations
    }
}