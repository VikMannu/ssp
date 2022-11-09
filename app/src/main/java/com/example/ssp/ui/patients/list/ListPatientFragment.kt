package com.example.ssp.ui.patients.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssp.adapter.PatientAdapter
import com.example.ssp.databinding.FragmentListPatientBinding

class ListPatientFragment : Fragment() {

    companion object {
        fun newInstance() = ListPatientFragment()
    }

    private lateinit var viewModel: ListPatientViewModel
    private lateinit var binding: FragmentListPatientBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.binding = FragmentListPatientBinding.inflate(inflater, container, false)

        this.binding.recyclerViewPatients.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val searchView = this.binding.searchViewPatients

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return viewModel.onQueryTextChange(query)
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return viewModel.onQueryTextChange(newText)
            }
        })

        this.binding.floatingButtonAddPatient.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(ListPatientFragmentDirections.actionListPatientFragmentToAddPatientFragment())
        }
        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListPatientViewModel::class.java]

        viewModel.arrayPatients.observe(viewLifecycleOwner) {
            val adapter = PatientAdapter(it) {position -> onListItemClick(position) }
            binding.recyclerViewPatients.adapter = adapter
        }
    }

    private fun onListItemClick(position: Int) {
        NavHostFragment.findNavController(this).navigate(ListPatientFragmentDirections.actionListPatientFragmentToPatientDetailsFragment(viewModel.getPatient(position)))
    }

    private fun hide() {
        val view = activity?.currentFocus
        if (view != null) {
            val input: InputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            input.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}