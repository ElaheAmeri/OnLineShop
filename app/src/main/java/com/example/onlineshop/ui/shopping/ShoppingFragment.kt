package com.example.onlineshop.ui.shopping

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.onlineshop.R
import com.example.onlineshop.databinding.FragmentHomeBinding
import com.example.onlineshop.databinding.FragmentShoppingBinding
import com.example.onlineshop.model.ProductItem
import com.example.onlineshop.ui.home.HomeFragmentViewModel
import com.example.onlineshop.ui.resultofcategory.ResultOfCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingFragment : Fragment() {
    lateinit var binding: FragmentShoppingBinding
    val viewModel: ShoppingFragmentViewModel by viewModels()
    private val sharpRefListProductId="sharpRefListProductId"
    private lateinit var sharedPreferences: SharedPreferences
    private var listProductId= arrayListOf<Int>()
    var listProduct= arrayListOf<ProductItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentShoppingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }
}