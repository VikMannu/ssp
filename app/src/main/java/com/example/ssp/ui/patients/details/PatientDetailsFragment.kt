package com.example.ssp.ui.patients.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.ssp.databinding.FragmentPatientDetailsBinding
import com.example.ssp.model.Person

class PatientDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PatientDetailsFragment()
    }

    private lateinit var viewModel: PatientDetailsViewModel
    private lateinit var binding: FragmentPatientDetailsBinding
    private lateinit var person: Person

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentPatientDetailsBinding.inflate(inflater, container, false)

        this.arguments?.let {
            val args = PatientDetailsFragmentArgs.fromBundle(it)
            this.person = args.person
        }

        this.binding.textViewName.text = this.person.nombre
        this.binding.textViewSurname.text = this.person.apellido
        this.binding.textViewNumberPhone.text = this.person.telefono
        this.binding.textViewEmail.text = this.person.email
        this.binding.textViewCI.text = this.person.cedula
        this.binding.textViewRuc.text = this.person.ruc
        this.binding.textViewTypePerson.text = this.person.tipoPersona
        this.binding.textViewBirthday.text = this.person.fechaNacimiento.split(" ")[0]

        this.binding.buttonEdit.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(PatientDetailsFragmentDirections.actionPatientDetailsFragmentToEditPatientFragment(this.person))
        }

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[PatientDetailsViewModel::class.java]

        this.binding.buttonDelete.setOnClickListener {
            viewModel.deletePatient(this.person, requireActivity())
            activity?.onBackPressed()
        }
    }

}