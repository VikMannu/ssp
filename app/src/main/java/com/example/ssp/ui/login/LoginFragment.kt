package com.example.ssp.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ssp.databinding.FragmentLoginBinding
import com.example.ssp.repository.ApiInterface
import com.example.ssp.repository.RetrofitClient
import com.example.ssp.ui.home.HomeActivity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

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

    private fun getUserList() {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getAllPhysiotherapy()
                if (response.isSuccessful) {
                    //your code for handaling success response
                    println("Total: ${response.body()?.totalData}")
                } else {
                    Toast.makeText(
                        activity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
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
        this.binding.editTextEmail.setText("")
        this.binding.editTextTextPassword.setText("")
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