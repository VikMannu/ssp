package com.example.ssp.ui.patients

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssp.adapter.PatientAdapter
import com.example.ssp.databinding.FragmentPatiensBinding

class PatientsFragment : Fragment() {

    companion object {
        fun newInstance() = PatientsFragment()
    }

    private lateinit var viewModel: PatientsViewModel
    private lateinit var binding: FragmentPatiensBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        this.binding = FragmentPatiensBinding.inflate(inflater, container, false)

        this.binding.recyclerViewPatients.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        return this.binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientsViewModel::class.java)

        viewModel.arrayPatients.observe(viewLifecycleOwner) {
            val adapter = PatientAdapter(it) {position -> onListItemClick(position) }
            binding.recyclerViewPatients.adapter = adapter
        }
    }

    private fun onListItemClick(position: Int) {
        println(position)
//        hide()
//        activity?.onBackPressed()
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