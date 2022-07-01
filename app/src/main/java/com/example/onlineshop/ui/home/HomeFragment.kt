package com.example.onlineshop.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.onlineshop.adapter.HomeRecyclerViewAdapter
import com.example.onlineshop.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    lateinit var binding:FragmentHomeBinding
    val viewModel:HomeFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapterRvListOflatestProducts = HomeRecyclerViewAdapter({})
        binding.rvListOflatestProducts.adapter = adapterRvListOflatestProducts
        viewModel.listOflatestProducts.observe(viewLifecycleOwner) {
            adapterRvListOflatestProducts.submitList(it)
        }

        val adapterRvListOfMostVisitProduct=HomeRecyclerViewAdapter({})
        binding.rvListOfMostVisitedProducts.adapter = adapterRvListOfMostVisitProduct
        viewModel.listOfMostVisitedProducts.observe(viewLifecycleOwner){
            adapterRvListOfMostVisitProduct.submitList(it)
        }

        val adapterRvListOfBestProduct=HomeRecyclerViewAdapter({})
        binding.rvListOfBestProducts.adapter =adapterRvListOfBestProduct
        viewModel.listOfBestProducts.observe(viewLifecycleOwner){
            adapterRvListOfBestProduct.submitList(it)
        }

    }
}