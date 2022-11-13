package com.example.ssp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ssp.databinding.ItemReservationBinding
import com.example.ssp.model.Reservation
import com.example.ssp.utils.UFormatter

class ReservationAdapter(
    private val reservationList: ArrayList<Reservation>,
    private val onItemClicked: (position: Int) -> Unit,
    private val editReservation: (position: Int) -> Unit,
    private val cancelReservation: (position: Int) -> Unit
) :
    RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {
    inner class ReservationViewHolder(
        private val binding: ItemReservationBinding,
        private val onItemClicked: (position: Int) -> Unit,
        private val editReservation: (position: Int) -> Unit,
        private val cancelReservation: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        @SuppressLint("SetTextI18n")
        fun bindItem(reservation: Reservation) {
            UFormatter.date(reservation.fechaCadena)
            this.binding.textViewCalendar.text = UFormatter.date(reservation.fechaCadena)
            this.binding.textViewTime.text = "${UFormatter.time(reservation.horaInicioCadena)} - ${UFormatter.time(reservation.horaFinCadena)}"
            this.binding.textViewPatient.text = "${reservation.idCliente.nombre} ${reservation.idCliente.apellido}"
            this.binding.textViewPhysiotherapist.text = "${reservation.idEmpleado.nombre} ${reservation.idEmpleado.apellido}"

            this.binding.imageViewEditReservation.setOnClickListener { editReservation(this.adapterPosition) }
            this.binding.imageViewCancelReservation.setOnClickListener{ cancelReservation(this.adapterPosition) }
        }

        init {
            this.itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.onItemClicked(this.adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        return ReservationViewHolder(
            ItemReservationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClicked, editReservation, cancelReservation
        )
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = reservationList[position]
        holder.bindItem(reservation)
    }

    override fun getItemCount(): Int {
        return reservationList.size
    }
}