package com.example.ssp.ui.patients

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ssp.R

class PatientsFragment : Fragment() {

    companion object {
        fun newInstance() = PatientsFragment()
    }

    private lateinit var viewModel: PatientsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_patiens, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}