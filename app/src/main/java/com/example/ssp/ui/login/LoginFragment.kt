package com.example.ssp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ssp.databinding.FragmentLoginBinding
import com.example.ssp.ui.home.HomeActivity
import com.example.ssp.utils.USharedPreferences

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
                USharedPreferences.writeAccount(viewModel.getAccount(), activity)

                it.visibility = View.INVISIBLE
                binding.loading.visibility = View.VISIBLE
                val intent = Intent(activity, HomeActivity::class.java)
                requireActivity().startActivity(intent)
            }
        }

    }

    private fun checkAllFields(): Boolean {
        if (!checkEditTextEmail()) {
            return false
        }

        if (!checkEditTextTextPassword()) {
            return false
        }
        // after all validation return true.
        return true
    }

    private fun checkEditTextEmail(): Boolean {
        val email = binding.editTextEmail.text.toString().trim()
        if (email.isEmpty()) {
            binding.editTextEmail.error = "Este campo es requerido"
            binding.editTextEmail.requestFocus()
            return false
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (!(email.trim { it <= ' ' }.matches(emailPattern.toRegex()))) {
            binding.editTextEmail.error = "Tiene que ser un correo válido"
            return false
        }

        if (!viewModel.checkEmail(email)) {
            binding.editTextEmail.error = "Usuario no encontrado"
            return false
        }

        binding.editTextEmail.clearFocus()
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