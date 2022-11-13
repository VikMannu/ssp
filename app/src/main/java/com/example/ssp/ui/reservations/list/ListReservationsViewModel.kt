package com.example.ssp.ui.reservations.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ssp.model.Person
import com.example.ssp.model.Reservation


//class ListReservationsViewModelFactory(
//    private val account: Person
//): ViewModelProvider.NewInstanceFactory() {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return ListReservationsViewModel(account) as T
//    }
//}

class ListReservationsViewModel : ViewModel() {
    private val _arrayReservations = MutableLiveData<ArrayList<Reservation>>()
    val arrayPatients: LiveData<ArrayList<Reservation>>
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
        listAllReservations.addAll(auxLoadExample())
        listAllReservationsFilter.addAll(auxLoadExample())

        _arrayReservations.value = listAllReservations
    }

    private fun auxLoadExample(): ArrayList<Reservation> {
        val patient1 = Person(1,"Victor Manuel", "Ayala Acosta", "victor.ayala2a@gmail.com", "0982485713", "4692858-0", "4692858", "FISICA", "1998-05-09 00:00:00", true)
        val patient2 = Person(2,"Manuel Victor", "Acosta Ayala", "ayala.victor2a@gmail.com", "0982485714", "4692859-1", "4692859", "FISICA", "1998-05-10 00:00:00", true)

        val reservation1 = Reservation(1,"20190903", "1000", "1015", patient1, patient2, "Ninguna", "true")
        val reservation2 = Reservation(2,"20201004", "1000", "1015", patient1, patient2, "Ninguna", "true")

        val list = ArrayList<Reservation>()
        list.add(reservation1)
        list.add(reservation2)

        return list
    }

    fun cancelReservation(position: Int) {
        println("Deleted $position")
    }

    fun getReservation(position: Int): Reservation {
        return this.listAllReservationsFilter[position]
    }

    fun reset() {
        _arrayReservations.value = listAllReservations
    }
}