package com.example.ssp.ui.dataSheet.add

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ssp.R
import com.example.ssp.databinding.FragmentAddDataSheetBinding
import com.example.ssp.databinding.FragmentListDataSheetBinding

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
        // TODO: Use the ViewModel
    }

}