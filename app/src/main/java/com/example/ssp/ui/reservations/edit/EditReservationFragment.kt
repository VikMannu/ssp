package com.example.ssp.ui.reservations.edit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ssp.R

class EditReservationFragment : Fragment() {

    companion object {
        fun newInstance() = EditReservationFragment()
    }

    private lateinit var viewModel: EditReservationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_reservation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EditReservationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}