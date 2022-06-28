package com.example.onlineshop.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.onlineshop.adapter.RecyclerViewAdapter
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
        var adapterRvListOflatestProducts=RecyclerViewAdapter({})
        binding.rvListOflatestProducts.adapter=adapterRvListOflatestProducts
        viewModel.listOflatestProducts.observe(viewLifecycleOwner){
            adapterRvListOflatestProducts.submitList(it) }

        var adapterRvListOfMostVisitedProducts=RecyclerViewAdapter({})
        binding.rvListOfMostVisitedProducts.adapter=adapterRvListOfMostVisitedProducts
        viewModel.listOflatestProducts.observe(viewLifecycleOwner){
            adapterRvListOfMostVisitedProducts.submitList(it) }

        var adapterRvListOfBestProducts=RecyclerViewAdapter({})
        binding.rvListOflatestProducts.adapter=adapterRvListOfBestProducts
        viewModel.listOfBestProducts.observe(viewLifecycleOwner){
            adapterRvListOflatestProducts.submitList(it) }

    }
}