package com.example.ssp.ui.patients.edit

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.ssp.databinding.FragmentEditPatientBinding
import com.example.ssp.model.Person
import com.example.ssp.ui.patients.details.PatientDetailsFragmentArgs
import com.example.ssp.utils.DatePicker

class EditPatientFragment : Fragment() {

    companion object {
        fun newInstance() = EditPatientFragment()
    }

    private lateinit var viewModel: EditPatientViewModel
    private lateinit var binding: FragmentEditPatientBinding
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
        DatePicker.createDatePickerDialog(this.binding.editTextBirthday, requireActivity())

//        this.editTextBirthDayConfig()

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[EditPatientViewModel::class.java]
        // TODO: Use the ViewModel
    }

    private fun editTextBirthDayConfig() {
        this.binding.editTextBirthday.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = activity?.let { fragmentActivity ->
                DatePickerDialog(
                    // on below line we are passing context.
                    fragmentActivity,
                    { _, year, monthOfYear, dayOfMonth ->
                        // on below line we are setting
                        // date to our edit text.
                        val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                        this.binding.editTextBirthday.setText(dat)
                    },
                    // on below line we are passing year, month
                    // and day for the selected date in our date picker.
                    year,
                    month,
                    day
                )
            }
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog?.show()
        }
    }
}