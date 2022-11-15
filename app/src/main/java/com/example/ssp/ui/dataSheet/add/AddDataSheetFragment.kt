package com.example.ssp.ui.dataSheet.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ssp.databinding.FragmentAddDataSheetBinding
import com.example.ssp.model.DataSheetBody
import com.example.ssp.model.PersonBodyA
import com.example.ssp.model.TypeProductA

class AddDataSheetFragment : Fragment() {

    companion object {
        fun newInstance() = AddDataSheetFragment()
    }

    private lateinit var viewModel: AddDataSheetViewModel
    private lateinit var binding: FragmentAddDataSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentAddDataSheetBinding.inflate(inflater, container, false)

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddDataSheetViewModel::class.java)

        this.binding.buttonAdd.setOnClickListener {
            val dataSheet = DataSheetBody(
                motivoConsulta = this.binding.editTextReasonConsultation.text.toString(),
                diagnostico = this.binding.editTextDiagnosis.text.toString(),
                observacion = this.binding.editTextReasonObservation.text.toString(),
                idEmpleado = PersonBodyA(Integer.parseInt(this.binding.editTextIDEmployee.text.toString())),
                idCliente = PersonBodyA(Integer.parseInt(this.binding.editTextIDClient.text.toString())),
                idTipoProducto = TypeProductA(Integer.parseInt(this.binding.editTextIDTypeProduct.text.toString()))
            )

            viewModel.addDataSheet(dataSheet, requireActivity())
            activity?.onBackPressed()
        }

    }

}