package com.example.ssp.ui.patients.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
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

        this.binding.textViewName.text = this.patient.nombre
        this.binding.textViewSurname.text = this.patient.apellido
        this.binding.textViewNumberPhone.text = this.patient.telefono
        this.binding.textViewEmail.text = this.patient.email
        this.binding.textViewCI.text = this.patient.cedula
        this.binding.textViewRuc.text = this.patient.ruc
        this.binding.textViewTypePerson.text = this.patient.tipoPersona
        this.binding.textViewBirthday.text = this.patient.fechaNacimiento

        this.binding.buttonEdit.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(PatientDetailsFragmentDirections.actionPatientDetailsFragmentToEditPatientFragment(this.patient))
        }

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[PatientDetailsViewModel::class.java]
        // TODO: Use the ViewModel
    }

}