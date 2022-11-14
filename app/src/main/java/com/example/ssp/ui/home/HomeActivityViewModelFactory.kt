package com.example.ssp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ssp.model.Person

class HomeActivityViewModelFactory(
    private val account: Person
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeActivityViewModel::class.java)) {
            return HomeActivityViewModel(account) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}