package com.example.onlineshop.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentCategoryBinding
import com.example.onlineshop.model.CategoryItemItem
import com.example.onlineshop.ui.home.HomeRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    lateinit var binding: FragmentCategoryBinding
    val viewModel: CategoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterCategory = CategoryRecyclerViewAdapter { id -> goToResultOfCategoryPage(id) }
        binding.rvCategoryFragment.adapter = adapterCategory
        viewModel.listOfCategory.observe(viewLifecycleOwner) { adapterCategory.submitList(it) }

    }

    private fun goToResultOfCategoryPage(categoryItem: CategoryItemItem) {

        val bundle = bundleOf("CategoryId" to categoryItem.id.toString())
        findNavController().navigate(R.id.action_categoryFragment2_to_resultOfCategoryFragment, bundle)
    }
}