package com.example.ssp.ui.reservations.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ssp.R
import com.example.ssp.databinding.FragmentAddReservationBinding
import com.example.ssp.databinding.FragmentSearchPersonBinding

class AddReservationFragment : Fragment() {

    companion object {
        fun newInstance() = AddReservationFragment()
    }

    private lateinit var viewModel: AddReservationViewModel
    private lateinit var binding: FragmentAddReservationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentAddReservationBinding.inflate(inflater, container, false)


        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddReservationViewModel::class.java)

    }

}