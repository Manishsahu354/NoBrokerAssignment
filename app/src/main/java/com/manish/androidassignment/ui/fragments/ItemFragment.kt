package com.manish.androidassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.manish.androidassignment.data.model.ResponseModelItem
import com.manish.androidassignment.databinding.FragmentItemBinding
import com.manish.androidassignment.ui.adapters.ItemClickListener
import com.manish.androidassignment.ui.adapters.ResponseAdapter
import com.manish.androidassignment.util.NetworkResult
import com.manish.androidassignment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class ItemFragment : Fragment(), ItemClickListener {

    private var _binding: FragmentItemBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var responseAdapter: ResponseAdapter
    private var responseList: MutableList<ResponseModelItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentItemBinding.inflate(inflater, container, false)

        setupRecyclerview()

        mainViewModel.getResponseData()



        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filter(it) }
                return true
            }

        })

        mainViewModel.responseData.observe(viewLifecycleOwner, { response ->

            when (response) {

                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }

                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let {
                        responseList = it
                        responseAdapter.setData(it)
                    }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(), "No Internet Connection", Toast.LENGTH_SHORT
                    ).show()
                }
            }


        })
        return binding.root
    }

    private fun setupRecyclerview() {
        responseAdapter = ResponseAdapter(responseList, this)
        showShimmerEffect()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = responseAdapter
        }
    }

    private fun filter(text: String) {
        val filteredList: MutableList<ResponseModelItem> = ArrayList()

        for (item in responseList) {
            if (item.title!!.lowercase(Locale.getDefault())
                    .contains(text.lowercase(Locale.getDefault()))
            ) {
                filteredList.add(item)
            }
        }
        responseAdapter.filterList(filteredList)
    }

    override fun onItemClicked(responseItem: ResponseModelItem) {
        findNavController().navigate(
            ItemFragmentDirections.actionItemFragmentToDetailsFragment(
                responseItem
            )
        )

    }

    private fun showShimmerEffect() {
        binding.recyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.recyclerView.hideShimmer()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}