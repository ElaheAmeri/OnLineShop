package com.example.onlineshop.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.onlineshop.R
import com.example.onlineshop.adapter.CategoryRecyclerViewAdapter
import com.example.onlineshop.databinding.FragmentCategoryBinding
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.ui.home.HomeFragmentViewModel
import dagger.hilt.EntryPoint
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

        val adapterCategory = CategoryRecyclerViewAdapter({})
        binding.rvCategoryFragment.adapter = adapterCategory
        viewModel.listOfCategory.observe(viewLifecycleOwner) { adapterCategory.submitList(it) }

    }
}