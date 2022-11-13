package com.example.ssp.ui.reservations.list

import android.annotation.SuppressLint
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
import com.example.ssp.adapter.ReservationAdapter
import com.example.ssp.databinding.FragmentListReservationsBinding
import com.example.ssp.ui.home.HomeActivityViewModel
import com.example.ssp.utils.UDatePicker

class ListReservationsFragment : Fragment() {

    companion object {
        fun newInstance() = ListReservationsFragment()
    }

    private lateinit var viewModel: ListReservationsViewModel
    private val viewModelActivity: HomeActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentListReservationsBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentListReservationsBinding.inflate(inflater, container, false)

        this.binding.recyclerViewReservations.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        UDatePicker.createDatePickerDialog(this.binding.textViewSearchStartDate, this.binding.textViewStartDate,requireActivity())
        UDatePicker.createDatePickerDialog(this.binding.textViewSearchEndDate, this.binding.textViewEndDate,requireActivity())

        this.binding.buttonClear.setOnClickListener {
            this.clearSearchView()
            this.viewModelActivity.resetReservations()
        }

        this.binding.textViewSearchEmployee.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(ListReservationsFragmentDirections.actionReservationsFragmentToSearchPersonFragment(false))
        }

        this.binding.textViewSearchPatient.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(ListReservationsFragmentDirections.actionReservationsFragmentToSearchPersonFragment(true))

        }

        return this.binding.root
    }

    @SuppressLint("SetTextI18n")
    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListReservationsViewModel::class.java]

        viewModelActivity.arrayReservations.observe(viewLifecycleOwner) {
            val adapter = ReservationAdapter(it, onListItemClick, editReservation, cancelReservation)
            binding.recyclerViewReservations.adapter = adapter
        }

        viewModelActivity.physiotherapy.observe(viewLifecycleOwner) {
            this.binding.textViewEmployee.text = "${it.nombre} ${it.apellido}"
        }

        viewModelActivity.patient.observe(viewLifecycleOwner) {
            this.binding.textViewPatient.text = "${it.nombre} ${it.apellido}"
        }
    }

    private val onListItemClick = fun (position: Int) {
        println(position)
    }

    private val editReservation = fun (position: Int) {
        NavHostFragment.findNavController(this).navigate(ListReservationsFragmentDirections.actionReservationsFragmentToEditReservationFragment(viewModelActivity.getReservation(position)))
    }

    private val cancelReservation = fun (position: Int) {
        viewModelActivity.cancelReservation(position)
    }

    @SuppressLint("SetTextI18n")
    private fun clearSearchView() {
        this.binding.textViewEmployee.text = "Todos"
        this.binding.textViewPatient.text = "Todos"
        this.binding.textViewStartDate.text = "Todos"
        this.binding.textViewEndDate.text = "Todos"
    }

}