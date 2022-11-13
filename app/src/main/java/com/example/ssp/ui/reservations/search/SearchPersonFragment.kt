package com.example.ssp.ui.reservations.search

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ssp.R
import com.example.ssp.adapter.PatientPerson
import com.example.ssp.databinding.FragmentSearchPersonBinding
import com.example.ssp.ui.home.HomeActivity
import com.example.ssp.ui.home.HomeActivityViewModel
import kotlin.properties.Delegates

class SearchPersonFragment : Fragment() {

    companion object {
        fun newInstance() = SearchPersonFragment()
    }

    private lateinit var viewModel: SearchPersonViewModel
    private val viewModelActivity: HomeActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentSearchPersonBinding
    private var isPatient = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentSearchPersonBinding.inflate(inflater, container, false)

        this.binding.recyclerViewPerson.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        this.arguments?.let {
            val args = SearchPersonFragmentArgs.fromBundle(it)
            this.isPatient = args.isPatient
        }

        setHasOptionsMenu(true)

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchPersonViewModel::class.java]

        viewModelActivity.resetPatients()
        viewModelActivity.resetPhysiotherapy()

        if(this.isPatient) {
            viewModelActivity.arrayPatients.observe(viewLifecycleOwner) {
                val adapter = PatientPerson(it) { position -> onListItemClickPatient(position) }
                binding.recyclerViewPerson.adapter = adapter
            }
        } else {
            viewModelActivity.arrayPhysiotherapy.observe(viewLifecycleOwner) {
                val adapter = PatientPerson(it) { position -> onListItemClickPhysiotherapy(position) }
                binding.recyclerViewPerson.adapter = adapter
            }
        }
    }

    private fun onListItemClickPatient(position: Int) {
        hide()
        viewModelActivity.setPatient(viewModelActivity.getPatient(position))
        activity?.onBackPressed()
    }

    private fun onListItemClickPhysiotherapy(position: Int) {
        hide()
        viewModelActivity.setPhysiotherapy(viewModelActivity.getPhysiotherapy(position))
        activity?.onBackPressed()
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

        if (this.isPatient) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return viewModelActivity.onQueryTextChangePatients(query)
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return viewModelActivity.onQueryTextChangePatients(newText)
                }
            })
        } else {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return viewModelActivity.onQueryTextChangePhysiotherapy(query)
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return viewModelActivity.onQueryTextChangePhysiotherapy(newText)
                }
            })
        }
        searchView.setOnClickListener { }
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