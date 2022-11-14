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
import com.example.ssp.model.Person
import com.example.ssp.ui.home.HomeActivityViewModel
import com.example.ssp.utils.UDatePicker
import com.example.ssp.utils.UFormatter
import com.example.ssp.utils.USharedPreferences

class ListReservationsFragment : Fragment() {

    companion object {
        fun newInstance() = ListReservationsFragment()
    }

    private lateinit var viewModel: ListReservationsViewModel
    private val viewModelActivity: HomeActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentListReservationsBinding

    private lateinit var account: Person

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentListReservationsBinding.inflate(inflater, container, false)

        this.binding.recyclerViewReservations.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        UDatePicker.createDatePickerDialog(
            this.binding.textViewSearchStartDate,
            this.binding.textViewStartDate,
            requireActivity(),
            viewModelActivity,
            true
        )

        UDatePicker.createDatePickerDialog(
            this.binding.textViewSearchEndDate,
            this.binding.textViewEndDate,
            requireActivity(),
            viewModelActivity,
            false
        )

        this.binding.buttonClear.setOnClickListener {
            this.viewModelActivity.resetReservations(this.account, requireActivity())
        }

        this.binding.textViewSearchPhysiotherapist.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                ListReservationsFragmentDirections.actionReservationsFragmentToSearchPersonFragment(
                    false
                )
            )
        }

        this.binding.textViewSearchPatient.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                ListReservationsFragmentDirections.actionReservationsFragmentToSearchPersonFragment(
                    true
                )
            )
        }

        this.binding.floatingButtonAddReservation.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                ListReservationsFragmentDirections.actionReservationsFragmentToAddReservationFragment()
            )
        }

        this.account = USharedPreferences.readAccount(activity)

        return this.binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListReservationsViewModel::class.java]

        viewModelActivity.arrayReservations.observe(viewLifecycleOwner) {
            val adapter =
                ReservationAdapter(it, onListItemClick, editReservation, cancelReservation)
            binding.recyclerViewReservations.adapter = adapter
        }

        viewModelActivity.physiotherapy.observe(viewLifecycleOwner) {
           if(it != null) {
               this.binding.textViewEmployee.text = "${it.nombre} ${it.apellido}"
           } else {
               this.binding.textViewEmployee.text = "Todos"
           }
        }

        viewModelActivity.patient.observe(viewLifecycleOwner) {
            if(it != null) {
                this.binding.textViewPatient.text = "${it.nombre} ${it.apellido}"
            } else {
                this.binding.textViewPatient.text = "Todos"
            }
        }

        viewModelActivity.startDate.observe(viewLifecycleOwner) {
            if(it != null) {
                this.binding.textViewStartDate.text = UFormatter.date(it)
            } else {
                this.binding.textViewStartDate.text = "Todos"
            }
        }


        viewModelActivity.endDate.observe(viewLifecycleOwner) {
            if(it != null) {
                this.binding.textViewEndDate.text = UFormatter.date(it)
            } else {
                this.binding.textViewEndDate.text = "Todos"
            }
        }
    }

    private val onListItemClick = fun(position: Int) {
        println(position)
    }

    private val editReservation = fun(position: Int) {
        NavHostFragment.findNavController(this).navigate(
            ListReservationsFragmentDirections.actionReservationsFragmentToEditReservationFragment(
                viewModelActivity.getReservation(position)
            )
        )
    }

    private val cancelReservation = fun(position: Int) {
        viewModel.deleteReservation(viewModelActivity.getReservation(position), requireActivity())
    }

}