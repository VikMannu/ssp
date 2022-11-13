package com.example.ssp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ssp.databinding.FragmentLoginBinding
import com.example.ssp.ui.home.HomeActivity

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentLoginBinding.inflate(inflater, container, false)

        return this.binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.buttonLogin.setOnClickListener {
            if (checkAllFields()) {
                it.visibility = View.INVISIBLE
                binding.loading.visibility = View.VISIBLE
                val intent = Intent(activity, HomeActivity::class.java)
                requireActivity().startActivity(intent)
            }
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("Aceptar", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun clean() {
        this.binding.editTextUsername.setText("")
        this.binding.editTextTextPassword.setText("")
    }

    private fun checkAllFields(): Boolean {
        if(!checkEditTextUsername()) {
            return false
        }

        if (!checkEditTextTextPassword()) {
            return false
        }
        // after all validation return true.
        return true
    }

    private fun checkEditTextUsername(): Boolean {
        val email = binding.editTextUsername.text.toString().trim()
        if (email.isEmpty()) {
            binding.editTextUsername.error = "Este campo es requerido"
            binding.editTextUsername.requestFocus()
            return false
        }

        binding.editTextUsername.clearFocus()
        return true
    }

    private fun checkEditTextTextPassword(): Boolean {
        val password = binding.editTextTextPassword.text.toString().trim()
        if (password.isEmpty()) {
            binding.editTextTextPassword.error = "Este campo es requerido"
            binding.editTextTextPassword.requestFocus()
            return false
        }

        if (password.length < 6) {
            binding.editTextTextPassword.error = "Mínimo 6 caracteres"
            binding.editTextTextPassword.requestFocus()
            return false
        }
        binding.editTextTextPassword.clearFocus()
        return true
    }

}