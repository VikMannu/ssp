package com.example.ssp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ssp.R
import com.example.ssp.databinding.ActivityMainBinding
import com.example.ssp.ui.dataSheet.DataSheetFragment
import com.example.ssp.ui.patients.PatientsFragment
import com.example.ssp.ui.reservations.ReservationsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reservations = ReservationsFragment()
        val patients = PatientsFragment()
        val dataSheet = DataSheetFragment()

        setCurrentFragment(reservations)

        binding.contentMain.bottomNavigationView.menu.findItem(R.id.reservations).isChecked = true
        binding.contentMain.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.reservations -> setCurrentFragment(reservations)
                R.id.patients -> setCurrentFragment(patients)
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