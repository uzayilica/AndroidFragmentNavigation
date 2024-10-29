package com.example.navigationcomponentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.navigationcomponentexample.databinding.FragmentProfilBinding
import com.example.navigationcomponentexample.databinding.FragmentWelcomeBinding


class ProfilFragment : Fragment() {
    private lateinit var binding:FragmentProfilBinding;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfilBinding.inflate(inflater,container,false)


        binding.btnYemek.setOnClickListener {
            it.findNavController().navigate(R.id.action_profilFragment_to_yemekFragment)
        }



        // Inflate the layout for this fragment
        return binding.root
    }

            }

