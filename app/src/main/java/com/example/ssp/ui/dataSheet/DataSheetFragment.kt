package com.example.ssp.ui.dataSheet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ssp.R

class DataSheetFragment : Fragment() {

    companion object {
        fun newInstance() = DataSheetFragment()
    }

    private lateinit var viewModel: DataSheetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_data_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DataSheetViewModel::class.java)
        // TODO: Use the ViewModel
    }

}