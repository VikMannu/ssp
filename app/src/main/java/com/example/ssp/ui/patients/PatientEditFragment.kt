package com.example.ssp.ui.patients

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ssp.R

class PatientEditFragment : Fragment() {

    companion object {
        fun newInstance() = PatientEditFragment()
    }

    private lateinit var viewModel: PatientEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_patient_edit, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}