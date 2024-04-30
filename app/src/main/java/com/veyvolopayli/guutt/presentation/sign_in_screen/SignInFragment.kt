package com.veyvolopayli.guutt.presentation.sign_in_screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.veyvolopayli.guutt.R
import com.veyvolopayli.guutt.common.Resource
import com.veyvolopayli.guutt.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private var binding: FragmentSignInBinding? = null
    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSignInBinding.bind(view)
        this.binding = binding

        viewModel.signUpState.observe(viewLifecycleOwner) { resource ->
            when(resource) {
                is Resource.Success -> {
                    findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), resource.error, Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.signInBtn.apply {
            isActive = true
            setOnClickListener {
                val login = binding.loginTv.text.toString().trim()
                val password = binding.passwordTv.text.toString().trim()
                if (login.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.signUp(login, password)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}