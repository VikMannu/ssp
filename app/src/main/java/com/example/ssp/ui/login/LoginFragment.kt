package com.example.ssp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ssp.databinding.FragmentLoginBinding

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
        this.binding.editTextTextEmailAddress.setText("")
        this.binding.editTextTextPassword.setText("")
    }

    private fun checkAllFields(): Boolean {
        if(!checkEditTextTextEmailAddress()) {
            return false
        }

        if (!checkEditTextTextPassword()) {
            return false
        }
        // after all validation return true.
        return true
    }

    private fun checkEditTextTextEmailAddress(): Boolean {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        if (email.isEmpty()) {
            binding.editTextTextEmailAddress.error = "Este campo es requerido"
            binding.editTextTextEmailAddress.requestFocus()
            return false
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!(email.trim { it <= ' ' }.matches(emailPattern.toRegex()))) {
            binding.editTextTextEmailAddress.error = "Tiene que ser un correo válido"
            binding.editTextTextEmailAddress.requestFocus()
            return false
        }
        binding.editTextTextEmailAddress.clearFocus()
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