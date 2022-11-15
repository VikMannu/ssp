package com.example.ssp.ui.dataSheet.list

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListDataViewModelFactory(
    private val fragmentActivity: FragmentActivity
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListDataSheetViewModel::class.java)) {
            return ListDataSheetViewModel(fragmentActivity) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}