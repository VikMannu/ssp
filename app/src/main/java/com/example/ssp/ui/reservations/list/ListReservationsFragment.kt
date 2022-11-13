package com.example.ssp.ui.reservations.list

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssp.adapter.ReservationAdapter
import com.example.ssp.databinding.FragmentListReservationsBinding
import com.example.ssp.utils.UDatePicker

class ListReservationsFragment : Fragment() {

    companion object {
        fun newInstance() = ListReservationsFragment()
    }

    private lateinit var viewModel: ListReservationsViewModel
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



        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListReservationsViewModel::class.java]
//        viewModel = ViewModelProvider(this, ListReservationsViewModelFactory(USharedPreferences.readAccount(activity)))[ListReservationsViewModel::class.java]

        viewModel.arrayPatients.observe(viewLifecycleOwner) {
            val adapter = ReservationAdapter(it, onListItemClick, editReservation, cancelReservation)
            binding.recyclerViewReservations.adapter = adapter
        }
    }

    private val onListItemClick = fun (position: Int) {
        println(position)
    }

    private val editReservation = fun (position: Int) {
        NavHostFragment.findNavController(this).navigate(ListReservationsFragmentDirections.actionReservationsFragmentToEditReservationFragment(viewModel.getReservation(position)))
    }

    private val cancelReservation = fun (position: Int) {
        viewModel.cancelReservation(position)
    }

}