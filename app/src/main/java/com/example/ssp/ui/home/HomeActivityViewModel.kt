package com.example.ssp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssp.model.Person
import java.util.*
import kotlin.collections.ArrayList

class HomeActivityViewModel: ViewModel() {
    private val _arrayPatients = MutableLiveData<ArrayList<Person>>()
    val arrayPatients: LiveData<ArrayList<Person>>
        get() = _arrayPatients

    private val listAllPatients = ArrayList<Person>()
    private val listAllPatientsFilter = ArrayList<Person>()

    private val _arrayPhysiotherapy = MutableLiveData<ArrayList<Person>>()
    val arrayPhysiotherapy: LiveData<ArrayList<Person>>
        get() = _arrayPatients

    private val listAllPhysiotherapy = ArrayList<Person>()
    private val listAllPhysiotherapyFilter = ArrayList<Person>()

    init {
        listAllPatients.addAll(auxLoadExample())
        listAllPatientsFilter.addAll(auxLoadExample())

        _arrayPatients.value = listAllPatients
    }

    private fun auxLoadExample(): ArrayList<Person> {
        val patient1 = Person(1,"Victor Manuel", "Ayala Acosta", "victor.ayala2a@gmail.com", "0982485713", "4692858-0", "4692858", "FISICA", "1998-05-09 00:00:00", true)
        val patient2 = Person(2,"Manuel Victor", "Acosta Ayala", "ayala.victor2a@gmail.com", "0982485714", "4692859-1", "4692859", "FISICA", "1998-05-10 00:00:00", true)

        val list = ArrayList<Person>()
        list.add(patient1)
        list.add(patient2)

        return list
    }

    fun getPatient(position: Int): Person {
        return this.listAllPatientsFilter[position]
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

    fun reset() {
        _arrayPatients.value = listAllPatients
    }
}