package com.example.navigationcomponentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.navigationcomponentexample.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels()

    private lateinit var binding:FragmentWelcomeBinding;

override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    binding=FragmentWelcomeBinding.inflate(inflater,container,false);
    binding.buttonLogin.setOnClickListener {
        it.findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
    }
    binding.buttonRegister.setOnClickListener {
        viewModel.login("hatali", "sifirlama")
        it.findNavController().navigate(R.id.action_welcomeFragment_to_signupFragment)
    }

        // Inflate the layout for this fragment
        return binding.root
    }

}