package com.example.ssp.ui.login

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssp.model.Person
import com.example.ssp.repository.ApiInterface
import com.example.ssp.repository.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val allPhysiotherapy = ArrayList<Person>()

    init {
        getAllPhysiotherapy()
    }


    private fun getAllPhysiotherapy() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiInterface.getAllPhysiotherapy()
                if (response.isSuccessful) {
                    //your code for handaling success response
                    allPhysiotherapy.clear()
                    response.body()?.data?.let { allPhysiotherapy.addAll(it) }
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
    }

    fun checkEmail(email: String): Boolean {
        for (physiotherapy in allPhysiotherapy) {
            if (email == physiotherapy.email) {
                return true
            }
        }
        return false
    }
}