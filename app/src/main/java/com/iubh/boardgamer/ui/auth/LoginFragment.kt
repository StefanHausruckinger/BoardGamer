package com.iubh.boardgamer.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.iubh.boardgamer.R
import com.iubh.boardgamer.core.Resource
import com.iubh.boardgamer.data.remote.LoginDataSource
import com.iubh.boardgamer.databinding.FragmentLoginBinding
import com.iubh.boardgamer.domain.auth.LoginRepositoryImplementation
import com.iubh.boardgamer.presentation.LoginScreenViewModel
import com.iubh.boardgamer.presentation.LoginScreenViewModelFactory
import com.iubh.boardgamer.utils.gone
import com.iubh.boardgamer.utils.hideKeyboard
import com.iubh.boardgamer.utils.show
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

private const val TAG = "LoginFragment"

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val viewModel: LoginScreenViewModel by viewModels {
        LoginScreenViewModelFactory(
            LoginRepositoryImplementation(
                LoginDataSource()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isUserLoggedIn()
        onSignIn()
        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun isUserLoggedIn() {
        firebaseAuth.currentUser?.let { user ->
            goToHome(user)
        }
    }

    private fun onSignIn() {
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            signInUser(email, password)
        }

    }

    private fun signInUser(email: String, password: String) {
        if (validateCredentials(email, password)) {
            viewModel.signIn(email, password).observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        binding.progressBar.show()
                    }
                    is Resource.Success -> {
                        binding.progressBar.gone()
                        it.data?.let { user ->
                            goToHome(user)
                        }
                    }
                    is Resource.Failure -> {
                        binding.progressBar.gone()
                        Toast.makeText(context, "${it.exception}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard()
    }

    private fun validateCredentials(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.etEmail.error = "email is empty"
            return false
        }
        if (password.isEmpty()) {
            binding.etPassword.error = "password is empty"
            return false
        }
        return true
    }

    private fun goToHome(user: FirebaseUser) {
        if (user.displayName.isNullOrEmpty()) {
            val action = R.id.action_loginFragment_to_setupProfileFragment
            findNavController().navigate(action)
        } else {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

}