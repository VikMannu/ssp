package com.example.ssp.ui.patients.edit

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.ssp.databinding.FragmentEditPatientBinding
import com.example.ssp.model.Person
import com.example.ssp.ui.home.HomeActivityViewModel
import com.example.ssp.ui.patients.details.PatientDetailsFragmentArgs
import com.example.ssp.utils.UDatePicker

class EditPatientFragment : Fragment() {

    companion object {
        fun newInstance() = EditPatientFragment()
    }

    private lateinit var viewModel: EditPatientViewModel
    private lateinit var binding: FragmentEditPatientBinding
    private val viewModelActivity: HomeActivityViewModel by activityViewModels()
    private lateinit var person: Person

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentEditPatientBinding.inflate(inflater, container, false)

        this.arguments?.let {
            val args = PatientDetailsFragmentArgs.fromBundle(it)
            this.person = args.person
        }

        this.binding.editTextName.setText(this.person.nombre)
        this.binding.editTextSurname.setText(this.person.apellido)
        this.binding.editTextNumberPhone.setText(this.person.telefono)
        this.binding.editTextEmail.setText(this.person.email)
        this.binding.editTextCI.setText(this.person.cedula)
        this.binding.editTextRuc.setText(this.person.ruc)
        this.binding.editTextTypePerson.setText(this.person.tipoPersona)
        this.binding.editTextBirthday.setText(this.person.fechaNacimiento.split(" ")[0])

        UDatePicker.createDatePickerDialog(this.binding.editTextBirthday, requireActivity())


        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[EditPatientViewModel::class.java]

        this.binding.buttonConfirm.setOnClickListener {

            val patient = Person(
                this.person.idPersona,
                binding.editTextName.text.toString(),
                binding.editTextSurname.text.toString(),
                binding.editTextNumberPhone.text.toString(),
                binding.editTextEmail.text.toString(),
                binding.editTextRuc.text.toString(),
                binding.editTextCI.text.toString(),
                binding.editTextTypePerson.text.toString(),
                "${binding.editTextBirthday.text} 00:00:00",
                null
            )

            viewModel.updatePatient(patient, requireActivity())
            activity?.onBackPressed()
            activity?.onBackPressed()
        }
    }
}