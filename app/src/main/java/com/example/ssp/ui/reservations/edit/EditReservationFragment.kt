package com.example.ssp.ui.reservations.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ssp.databinding.FragmentEditReservationBinding
import com.example.ssp.model.Reservation

class EditReservationFragment : Fragment() {

    companion object {
        fun newInstance() = EditReservationFragment()
    }

    private lateinit var viewModel: EditReservationViewModel
    private lateinit var binding: FragmentEditReservationBinding

    private lateinit var reservation: Reservation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentEditReservationBinding.inflate(inflater, container, false)

        this.arguments?.let {
            val args = EditReservationFragmentArgs.fromBundle(it)
            this.reservation = args.reservation
        }

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[EditReservationViewModel::class.java]

        this.binding.buttonUpdate.setOnClickListener {
            this.reservation.observacion = this.binding.editTextObservation.text.toString()
            this.reservation.flagAsistio = if(this.binding.checkBoxAssisted.isChecked) {
                "S"
            } else {
                "N"
            }

            viewModel.updateReservation(reservation, requireActivity())

            activity?.onBackPressed()
        }

    }

}