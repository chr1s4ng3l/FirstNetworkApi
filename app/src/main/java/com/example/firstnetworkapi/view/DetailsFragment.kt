package com.example.firstnetworkapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firstnetworkapi.databinding.FragmentDetailsBinding
import com.example.firstnetworkapi.viewmodel.SchoolViewModel


class DetailsFragment : Fragment() {


    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        return binding.root
    }


}