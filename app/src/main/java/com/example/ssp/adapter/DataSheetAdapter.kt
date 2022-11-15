package com.example.ssp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ssp.databinding.ItemDataSheetBinding
import com.example.ssp.databinding.ItemPersonBinding
import com.example.ssp.model.DataSheet
import com.example.ssp.model.Person

class DataSheetAdapter(
    private val dataSheetList: ArrayList<DataSheet>,
    private val onItemClicked: (position: Int) -> Unit
) :
    RecyclerView.Adapter<DataSheetAdapter.PatientViewHolder>() {
    inner class PatientViewHolder(
        private val binding: ItemDataSheetBinding,
        private val onItemClicked: (position: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        @SuppressLint("SetTextI18n")
        fun bindItem(dataSheet: DataSheet) {
            this.binding.textViewReasonConsultation.text = "Motivo de Consulta: ${dataSheet.motivoConsulta}"
            this.binding.textViewDiagnosis.text = "Diagnostico: ${dataSheet.diagnostico}"
            this.binding.textViewObservation.text = "Observacion: ${dataSheet.observacion}"
            this.binding.textViewIDEmployee.text = "IDEmpleado: ${dataSheet.idEmpleado?.idPersona}"
            this.binding.textViewIDClient.text = "IDCliente: ${dataSheet.idCliente?.idPersona}"
            this.binding.textViewIDTypeProduct.text = "IDTProducto: ${dataSheet.idTipoProducto?.idTipoProducto}"
        }

        init {
            this.itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.onItemClicked(this.adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        return PatientViewHolder(
            ItemDataSheetBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onItemClicked
        )
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        holder.bindItem(dataSheetList[position])
    }

    override fun getItemCount(): Int {
        return dataSheetList.size
    }
}