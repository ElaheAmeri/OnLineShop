package com.example.onlineshop.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.onlineshop.adapter.PagerDetailAdapter
import com.example.onlineshop.databinding.FragmentDetailProductBinding
import com.example.onlineshop.model.Image
import com.example.onlineshop.model.ProductItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailProductFragment : Fragment() {
    lateinit var binding: FragmentDetailProductBinding
    val viewModel: DetailProductViewModel by viewModels()
    private var urlList = listOf<Image>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentDetailProductBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemId = requireArguments().getInt("ProductId", -1)
        viewModel.getDetailProduct(itemId)
        observeProduceItem()
        val adapter =PagerDetailAdapter( requireContext(),urlList)
        binding.pagerDetailFragment.adapter =adapter

    }

    @SuppressLint("SetTextI18n")
    private fun observeProduceItem() {
        viewModel.detailProductLiveData.observe(viewLifecycleOwner) {
            if (it != null) {
                urlList=it.images
                binding.tvDetailName.text = it.name
                binding.tvRating.text = it.averageRating
                binding.tvDetailPrice.text = it.price
                binding.tvDescription.text =it.description
            }
        }
    }



}