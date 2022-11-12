package com.example.ssp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ssp.databinding.ItemPatientBinding
import com.example.ssp.model.Person

class PatientAdapter(
    private val patientList: ArrayList<Person>,
    private val onItemClicked: (position: Int) -> Unit
) :
    RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {
    inner class PatientViewHolder(
        private val binding: ItemPatientBinding,
        private val onItemClicked: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        @SuppressLint("SetTextI18n")
        fun bindItem(patient: Person) {
            this.binding.textViewName.text = "${patient.nombre} ${patient.apellido}"
            this.binding.textViewCI.text = patient.cedula
            this.binding.textViewNumberPhone.text = patient.telefono
        }

        init {
            this.itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = this.adapterPosition
            this.onItemClicked(position)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return PatientViewHolder(
            ItemPatientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClicked
        )
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = patientList[position]
        holder.bindItem(patient)
    }

    override fun getItemCount(): Int {
        return patientList.size
    }
}