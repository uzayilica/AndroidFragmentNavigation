package com.example.navigationcomponentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigationcomponentexample.databinding.FragmentYemekBinding

class YemekFragment : Fragment() {


    lateinit var binding : FragmentYemekBinding;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentYemekBinding.inflate(inflater,container,false)


        binding.btnWelcomeDon.setOnClickListener {
            findNavController().navigate(R.id.action_yemekFragment_to_welcomeFragment)

        }



        return binding.root
    }



    }
