package com.example.onlineshop.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.model.ProductItem
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


        val adapterRvListOfLatestProducts = HomeRecyclerViewAdapter { id -> goToDetailPage(id) }
        binding.rvListOflatestProducts.adapter = adapterRvListOfLatestProducts
        viewModel.listOfLatestProducts.observe(viewLifecycleOwner) {
            adapterRvListOfLatestProducts.submitList(it)
        }

        val adapterRvListOfMostVisitProduct=HomeRecyclerViewAdapter {  id -> goToDetailPage(id)}
        binding.rvListOfMostVisitedProducts.adapter = adapterRvListOfMostVisitProduct
        viewModel.listOfMostVisitedProducts.observe(viewLifecycleOwner){
            adapterRvListOfMostVisitProduct.submitList(it)
        }

        val adapterRvListOfBestProduct=HomeRecyclerViewAdapter { id -> goToDetailPage(id) }
        binding.rvListOfBestProducts.adapter =adapterRvListOfBestProduct
        viewModel.listOfBestProducts.observe(viewLifecycleOwner){
            adapterRvListOfBestProduct.submitList(it)
        }

        binding.tvSearch.setOnClickListener(){
            goToSearchFragment()
        }
    }
    private fun goToDetailPage(productItem: ProductItem) {

        val bundle = bundleOf("ProductId" to productItem.id.toInt())
        findNavController().navigate(R.id.action_homeFragment_to_detailProductFragment, bundle)
    }

    fun goToSearchFragment(){
        val  bundle = bundleOf("wordSearched" to binding.tvSearch.text)
        findNavController().navigate(R.id.action_homeFragment_to_searchFragment,bundle)
    }
}