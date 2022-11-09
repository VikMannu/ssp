package com.example.ssp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ssp.R
import com.example.ssp.databinding.ActivityHomeBinding
import com.example.ssp.ui.dataSheet.DataSheetFragment
import com.example.ssp.ui.patients.PatientFragment
import com.example.ssp.ui.reservations.ReservationsFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reservations = ReservationsFragment()
        val patients = PatientFragment()
        val dataSheet = DataSheetFragment()

        setCurrentFragment(reservations)

        binding.contentMain.bottomNavigationView.menu.findItem(R.id.reservations).isChecked = true
        binding.contentMain.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.patients -> setCurrentFragment(patients)
                R.id.reservations -> setCurrentFragment(reservations)
                R.id.dataSheet -> setCurrentFragment(dataSheet)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}