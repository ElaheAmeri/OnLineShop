package com.example.onlineshop.ui.resultofcategory

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
import com.example.onlineshop.databinding.FragmentResultOfCategoryBinding
import com.example.onlineshop.model.CategoryItemItem
import com.example.onlineshop.model.ProductItem
import com.example.onlineshop.ui.home.HomeFragmentViewModel
import com.example.onlineshop.ui.home.HomeRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultOfCategoryFragment : Fragment() {

    private lateinit var binding: FragmentResultOfCategoryBinding
    val viewModel: ResultOfCategoryFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentResultOfCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemId = requireArguments().getString("CategoryId", "")
        viewModel.getListProductOfCategory(itemId)
        val adapterRvResultOfCategory=ResultOfCategoryAdapter {}
        binding.rvResultOfCategory.adapter=adapterRvResultOfCategory
        viewModel.listOfCategory.observe(viewLifecycleOwner){
            adapterRvResultOfCategory.submitList(it)
        }
    }







}