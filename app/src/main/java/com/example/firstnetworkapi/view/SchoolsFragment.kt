package com.example.firstnetworkapi.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstnetworkapi.R
import com.example.firstnetworkapi.adapter.SchoolAdapter
import com.example.firstnetworkapi.databinding.FragmentSchoolsBinding
import com.example.firstnetworkapi.di.NetworkModule
import com.example.firstnetworkapi.viewmodel.SchoolViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolsFragment : Fragment() {

    private val binding by lazy {
        FragmentSchoolsBinding.inflate(layoutInflater)
    }

    private val schoolAdapter by lazy {
        SchoolAdapter {
            findNavController().navigate(R.id.action_SchoolsFragment_to_DetailsFragment)
            val bundle = Bundle()
            bundle.putString("schoolName", it.schoolName.toString())
            bundle.putString("schoolEmail", it.schoolEmail.toString())
            bundle.putString("schoolWeb", it.website.toString())
            bundle.putString("schoolDescription", it.overviewParagraph.toString())

            val fragment = DetailsFragment()
            fragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(R.id.frag_container, fragment)?.commit()

    }

    }

    private val schoolsViewModel: SchoolViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.schoolRv.apply {
            layoutManager =
                LinearLayoutManager(requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false)

            adapter = schoolAdapter

        }

        schoolsViewModel.schools.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                }
                is UIState.SUCCESS -> {
                    schoolAdapter.updateSchools(state.response)
                }
                is UIState.ERROR -> {
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error occurred")
                        .setMessage(state.error.localizedMessage)
                        .setPositiveButton("Retry") { dialog, _ ->
                            schoolsViewModel.getAllSchools()
                            dialog.dismiss()
                        }
                        .setNegativeButton("Dismiss") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        schoolsViewModel.getAllSchools()

        // Inflate the layout for this fragment
        return binding.root
    }


}