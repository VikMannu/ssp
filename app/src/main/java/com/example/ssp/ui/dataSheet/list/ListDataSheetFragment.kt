package com.example.ssp.ui.dataSheet.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssp.adapter.DataSheetAdapter
import com.example.ssp.databinding.FragmentListDataSheetBinding

class ListDataSheetFragment : Fragment() {

    companion object {
        fun newInstance() = ListDataSheetFragment()
    }

    private lateinit var viewModel: ListDataSheetViewModel
    private lateinit var viewModelFactory: ListDataViewModelFactory
    private lateinit var binding: FragmentListDataSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentListDataSheetBinding.inflate(inflater, container, false)

        this.binding.recyclerViewDateSheets.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        this.binding.floatingButtonAddDataSheet.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                ListDataSheetFragmentDirections.actionDataSheetFragmentToAddDataSheetFragment()
            )
        }

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = ListDataViewModelFactory(requireActivity())

        viewModel = ViewModelProvider(this, viewModelFactory)[ListDataSheetViewModel::class.java]

        viewModel.arrayDataSheets.observe(viewLifecycleOwner) {
            val adapter = DataSheetAdapter(it, onListItemClick)
            binding.recyclerViewDateSheets.adapter = adapter
        }

        this.binding.buttonFilter.setOnClickListener {
            val idClient = if (this.binding.editTextIDClient.text.isEmpty()) {
                null
            } else {
                this.binding.editTextIDClient.text.toString()
            }

            val idEmployee = if (this.binding.editTextIDEmployee.text.isEmpty()) {
                null
            } else {
                this.binding.editTextIDEmployee.text.toString()
            }

            val idTypeProduct = if (this.binding.editTextIDTypeProduct.text.isEmpty()) {
                null
            } else {
                this.binding.editTextIDTypeProduct.text.toString()
            }

            val startDate = if (this.binding.editTextStartDate.text.isEmpty()) {
                null
            } else {
                this.binding.editTextStartDate.text.toString()
            }

            val endDate = if (this.binding.editTextEndDate.text.isEmpty()) {
                null
            } else {
                this.binding.editTextEndDate.text.toString()
            }

            viewModel.filterDataSheet(
                idClient = idClient,
                idEmployee = idEmployee,
                idTypeProduct = idTypeProduct,
                startDate = startDate,
                endDate = endDate,
                requireActivity()
            )

        }

        this.binding.buttonClear.setOnClickListener {
            this.binding.editTextIDEmployee.setText("")
            this.binding.editTextIDClient.setText("")
            this.binding.editTextIDTypeProduct.setText("")
            this.binding.editTextStartDate.setText("")
            this.binding.editTextEndDate.setText("")
            viewModel.filterDataSheet(fragmentActivity = requireActivity())
        }

    }

    private val onListItemClick = fun(position: Int) {
        println(position)
    }

}