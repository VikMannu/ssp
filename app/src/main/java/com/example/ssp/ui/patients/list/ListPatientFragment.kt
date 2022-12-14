package com.example.ssp.ui.patients.list

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssp.R
import com.example.ssp.adapter.PersonAdapter
import com.example.ssp.databinding.FragmentListPatientBinding
import com.example.ssp.model.Person
import com.example.ssp.ui.home.HomeActivity
import com.example.ssp.ui.home.HomeActivityViewModel

class ListPatientFragment : Fragment() {

    companion object {
        fun newInstance() = ListPatientFragment()
    }

    private lateinit var viewModel: ListPatientViewModel
    private val viewModelActivity: HomeActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentListPatientBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentListPatientBinding.inflate(inflater, container, false)

        this.binding.recyclerViewPatients.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        this.binding.floatingButtonAddPatient.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(ListPatientFragmentDirections.actionListPatientFragmentToAddPatientFragment())
        }
        setHasOptionsMenu(true)

        return this.binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListPatientViewModel::class.java]

        viewModelActivity.resetPatients()

        viewModelActivity.arrayPatients.observe(viewLifecycleOwner) {
            val adapter = PersonAdapter(it) { position -> onListItemClick(position) }
            binding.recyclerViewPatients.adapter = adapter
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onListItemClick(position: Int) {
        val patient = viewModelActivity.getPatient(position)
        verifyData(patient)
        NavHostFragment.findNavController(this).navigate(ListPatientFragmentDirections.actionListPatientFragmentToPatientDetailsFragment(patient))
        viewModelActivity.resetPatients()
    }

    private fun verifyData(patient: Person) {
        if (patient.nombre == null) {

        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_item_menu, menu)
        val searchView =
            SearchView((context as HomeActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.search_action).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }
        searchView.isIconified = false
        searchView.isFocusable = true
        searchView.requestFocusFromTouch()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextSubmit(query: String): Boolean {
                return viewModelActivity.onQueryTextChangePatients(query)
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onQueryTextChange(newText: String): Boolean {
                return viewModelActivity.onQueryTextChangePatients(newText)
            }
        })
        searchView.setOnClickListener { }
    }
}