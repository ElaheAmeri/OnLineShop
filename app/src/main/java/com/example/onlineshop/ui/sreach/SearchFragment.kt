package com.example.onlineshop.ui.sreach

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.onlineshop.databinding.FragmentSreachBinding
import com.example.onlineshop.ui.resultofcategory.ResultOfCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    lateinit var binding: FragmentSreachBinding
    val viewModel: SearchFragmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentSreachBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val wordSearched=arguments?.getString("wordSearched","a")
        if (wordSearched != null) {
            viewModel.search(wordSearched)
        }
        val adapterRvSearch= ResultOfCategoryAdapter { }
        binding.rvSearch.adapter=adapterRvSearch
        viewModel.listOfProductsSearched.observe(viewLifecycleOwner){
            adapterRvSearch.submitList(it)
        }
    }
}