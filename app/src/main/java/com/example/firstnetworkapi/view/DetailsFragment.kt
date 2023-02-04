package com.example.firstnetworkapi.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.firstnetworkapi.databinding.FragmentDetailsBinding
import com.example.firstnetworkapi.viewmodel.SchoolViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {


    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }


    private val schoolsViewModel: SchoolViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        schoolsViewModel.getAllSat()

        schoolsViewModel.satModel.observe(viewLifecycleOwner) {
           binding.textViewName.text = it?.schoolName.toString()
           binding.tvWritingScore.text = it?.satWritingAvgScore.toString()
           binding.tvReadingScore.text = it?.satCriticalReadingAvgScore.toString()
           binding.tvMathScore.text = it?.satMathAvgScore.toString()
            binding.textViewDescription.text = schoolsViewModel.description
            binding.textViewEmail.text = schoolsViewModel.email
            binding.textViewWeb.text = schoolsViewModel.web
        }

        schoolsViewModel.isLoading.observe(viewLifecycleOwner){
            binding.progress.isVisible = it

        }


        // Inflate the layout for this fragment
        return binding.root
    }

}