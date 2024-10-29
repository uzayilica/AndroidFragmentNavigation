package com.example.navigationcomponentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentexample.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    private val viewModel: AuthViewModel by activityViewModels()

    lateinit var binding: FragmentSignupBinding;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        binding=FragmentSignupBinding.inflate(inflater,container,false)
        setupClickListeners()
        setupObservers()
        return binding.root // binding.root döndürülmeli
    }

    private fun setupObservers() {
        // Login durumunu gözlemle
        viewModel.isLoggedIn.observe(viewLifecycleOwner) { isLoggedIn ->
            if (isLoggedIn) {
                findNavController().navigate(R.id.action_loginFragment_to_profilFragment)
            }
        }

        // Hata mesajlarını gözlemle
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMsg ->
            if (errorMsg.isNotEmpty()) {
                Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun setupClickListeners() {
        //signup kayıt ol butonuna tıklandığı an çalışır
        binding.signupButton.setOnClickListener {
            val email = binding.edittextEmail.text.toString()
            val password = binding.edittextPassword.text.toString()
            val username = binding.edittextUsername.text.toString()
            // Email ve şifre kontrolü
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // ViewModel'deki login fonksiyonunu çağır
            viewModel.signup(email, password,username)

        }
        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }






    }




}