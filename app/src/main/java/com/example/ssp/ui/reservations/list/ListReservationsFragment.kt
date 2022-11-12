package com.example.ssp.ui.reservations.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ssp.R
import com.example.ssp.databinding.FragmentListPatientBinding
import com.example.ssp.databinding.FragmentListReservationsBinding

class ListReservationsFragment : Fragment() {

    companion object {
        fun newInstance() = ListReservationsFragment()
    }

    private lateinit var viewModel: ListReservationsViewModel
    private lateinit var binding: FragmentListReservationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentListReservationsBinding.inflate(inflater, container, false)

        return this.binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListReservationsViewModel::class.java]
        // TODO: Use the ViewModel
    }

}