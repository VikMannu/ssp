package com.example.ssp.ui.patients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssp.model.Patient

class PatientsViewModel : ViewModel() {
    private val _arrayPatients = MutableLiveData<ArrayList<Patient>>()
    val arrayPatients: LiveData<ArrayList<Patient>>
        get() = _arrayPatients

    private val listAllPatients = ArrayList<Patient>()
    private val listAllPatientsFilter = ArrayList<Patient>()

    init {
        listAllPatients.addAll(auxLoadExample())
        listAllPatientsFilter.addAll(auxLoadExample())

        _arrayPatients.value = listAllPatients
    }

    private fun auxLoadExample(): ArrayList<Patient> {
        val patient1 = Patient("Victor Manuel", "Ayala Acosta", "victor.ayala2a@gmail.com", "0982485713", "4692858-0", "4692858", "FISICA", "1998-05-09 00:00:00")
        val patient2 = Patient("Manuel Victor", "Acosta Ayala", "ayala.victor2a@gmail.com", "0982485714", "4692859-1", "4692859", "FISICA", "1998-05-10 00:00:00")

        val list = ArrayList<Patient>()
        list.add(patient1)
        list.add(patient2)

        return list
    }

    fun getPatient(position: Int): Patient {
        return listAllPatients[position]
    }
}