package com.manish.androidassignment.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.manish.androidassignment.data.model.ResponseModel
import com.manish.androidassignment.databinding.FragmentItemBinding
import com.manish.androidassignment.ui.adapters.ResponseAdapter
import com.manish.androidassignment.util.NetworkResult
import com.manish.androidassignment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemFragment : Fragment() {

    private var _binding: FragmentItemBinding?=null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var responseAdapter:ResponseAdapter
    private var responseList = ResponseModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding =  FragmentItemBinding.inflate(inflater, container, false)

        setupRecyclerview()

        mainViewModel.getResponseData()

        mainViewModel.responseData.observe(viewLifecycleOwner,{response->

            when(response){

                is NetworkResult.Loading ->{
                    showShimmerEffect()
                }

                is NetworkResult.Success ->{
                    hideShimmerEffect()
                    response.data?.let {
                        responseAdapter.setData(it) }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(requireContext()
                        ,"No Internet Connection"
                        , Toast.LENGTH_SHORT).show()
                }
            }



        })
        return binding.root
    }

    private fun setupRecyclerview() {
        responseAdapter = ResponseAdapter(responseList)
        showShimmerEffect()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = responseAdapter
        }
    }

    private fun showShimmerEffect(){
        binding.recyclerView.showShimmer()
    }

    private fun hideShimmerEffect(){
        binding.recyclerView.hideShimmer()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}