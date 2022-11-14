package com.example.ssp.ui.reservations.add

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssp.adapter.FreeReservationAdapter
import com.example.ssp.databinding.FragmentAddReservationBinding
import com.example.ssp.model.Person
import com.example.ssp.ui.home.HomeActivityViewModel
import com.example.ssp.utils.UDatePicker

class AddReservationFragment : Fragment() {

    companion object {
        fun newInstance() = AddReservationFragment()
    }

    private lateinit var viewModel: AddReservationViewModel
    private val viewModelActivity: HomeActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentAddReservationBinding

    private lateinit var physiotherapist : Person
    private lateinit var patient: Person

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentAddReservationBinding.inflate(inflater, container, false)

        this.binding.recyclerViewFreeReservations.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


        UDatePicker.createDatePickerDialog(
            this.binding.textViewSearchDate,
            this.binding.textViewDate,
            requireActivity()
        )

        this.binding.textViewSearchPhysiotherapist.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                AddReservationFragmentDirections.actionAddReservationFragmentToSearchPersonFragment(
                    false
                )
            )
        }

        this.binding.textViewSearchPatient.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                AddReservationFragmentDirections.actionAddReservationFragmentToSearchPersonFragment(
                    true
                )
            )
        }

        return this.binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddReservationViewModel::class.java]

        viewModelActivity.physiotherapy.observe(viewLifecycleOwner) {
            if (it != null) {
                this.binding.textViewEmployee.text = "${it.nombre} ${it.apellido}"
                this.physiotherapist = it
            } else {
                this.binding.textViewEmployee.text = "Todos"
            }
        }

        viewModelActivity.patient.observe(viewLifecycleOwner) {
            if (it != null) {
                this.binding.textViewPatient.text = "${it.nombre} ${it.apellido}"
                this.patient = it
            } else {
                this.binding.textViewPatient.text = "Todos"
            }
        }

        viewModelActivity.arrayReservationsFree.observe(viewLifecycleOwner) {
            val adapter = FreeReservationAdapter(it, onListItemClick, addReservation)
            binding.recyclerViewFreeReservations.adapter = adapter
        }

        this.binding.buttonSearch.setOnClickListener {
            viewModelActivity.freeReservations(this.physiotherapist, this.binding.textViewDate.text.toString().replace("-", ""), requireActivity())
        }

    }

    private val onListItemClick = fun(position: Int) {
        println(position)
    }

    private val addReservation = @RequiresApi(Build.VERSION_CODES.O)
    fun(position: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            viewModel.addReservation(viewModelActivity.getFreeReservation(position), requireActivity())
            viewModelActivity.freeReservations(this.physiotherapist, this.binding.textViewDate.text.toString().replace("-", ""), requireActivity())
        }
    }

}