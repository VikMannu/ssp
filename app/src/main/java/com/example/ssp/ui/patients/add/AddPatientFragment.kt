package com.example.ssp.ui.patients.add

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.ssp.databinding.FragmentAddPatientBinding
import com.example.ssp.utils.DatePicker

class AddPatientFragment : Fragment() {

    companion object {
        fun newInstance() = AddPatientFragment()
    }

    private lateinit var viewModel: AddPatientViewModel
    private lateinit var binding: FragmentAddPatientBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentAddPatientBinding.inflate(inflater, container, false)

        DatePicker.createDatePickerDialog(this.binding.editTextBirthday, requireActivity())

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddPatientViewModel::class.java]
        // TODO: Use the ViewModel
    }

}