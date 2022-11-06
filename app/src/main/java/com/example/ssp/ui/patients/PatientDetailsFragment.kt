package com.example.ssp.ui.patients

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ssp.R
import com.example.ssp.databinding.FragmentPatiensBinding
import com.example.ssp.databinding.FragmentPatientDetailsBinding
import com.example.ssp.model.Patient

class PatientDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PatientDetailsFragment()
    }

    private lateinit var viewModel: PatientDetailsViewModel
    private lateinit var binding: FragmentPatientDetailsBinding
    private lateinit var patient: Patient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentPatientDetailsBinding.inflate(inflater, container, false)

        this.arguments?.let {
            val args = PatientDetailsFragmentArgs.fromBundle(it)
            this.patient = args.patient
        }

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}