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
import com.example.ssp.model.Person
import com.example.ssp.utils.UDatePicker

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

        UDatePicker.createDatePickerDialog(this.binding.editTextBirthday, requireActivity())

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddPatientViewModel::class.java]

        this.binding.buttonConfirm.setOnClickListener {
            val patient = Person(
                null,
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

            val patient1 = Person(
                null,
                "Victor Manuel",
                "Ayala Acosta",
                "a@abc.com",
                "0981 234 567",
                "1234567-1",
                "1234567",
                "FISICA",
                "1999-09-21 00:00:00",
                null
            )
            activity?.let { fragmentActivity -> viewModel.addPatient(patient1, fragmentActivity) }
            activity?.onBackPressed()
        }
    }

}