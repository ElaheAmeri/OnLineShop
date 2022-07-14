package com.example.onlineshop.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlineshop.R
import com.example.onlineshop.RemoveHtmlTag
import com.example.onlineshop.adapter.PagerDetailAdapter
import com.example.onlineshop.databinding.FragmentDetailProductBinding
import com.example.onlineshop.model.Image
import com.example.onlineshop.model.ProductItem
import com.example.onlineshop.model.Reviw
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator3

@AndroidEntryPoint
class DetailProductFragment : Fragment() {
    lateinit var binding: FragmentDetailProductBinding
    val viewModel: DetailProductViewModel by viewModels()
    var urlList = listOf<Image>()
    private val sharpRefListProductId="sharpRefListProductId"
    private lateinit var sharedPreferences: SharedPreferences
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

        val adapterPagerDetail =PagerDetailAdapter( requireContext(),urlList)
        binding.pagerDetailFragment.adapter =adapterPagerDetail
        val indicator:CircleIndicator3=binding.circleIndicator
        indicator.setViewPager(binding.pagerDetailFragment)


        viewModel.getProductReviews(itemId)
        val rvReviewAdapter=ReviewAdapter {}
        binding.rvDetail.adapter=rvReviewAdapter
        viewModel.detailProductLiveDataReviw.observe(viewLifecycleOwner){
            var listRevie: String
            for (i in it.indices){
                 listRevie= RemoveHtmlTag.html2text(it[i].review)
            }
            rvReviewAdapter.submitList(it)
        }
        observeProduceItem()



        sharedPreferences =requireActivity().getSharedPreferences(sharpRefListProductId,Context.MODE_PRIVATE)


        val editor:SharedPreferences.Editor =sharedPreferences.edit()
        editor.apply {
            putInt("productId",itemId)
        }.apply()



    }
    private fun observeProduceItem() {

        viewModel.detailProductLiveData.observe(viewLifecycleOwner) {

            if (it != null) {
                urlList=it.images
                binding.tvDetailName.text = it.name
                binding.tvRating.text = it.averageRating
                binding.tvDetailPrice.text = it.price
                val description =RemoveHtmlTag.html2text(it.description)
                binding.tvDescription.text =description

            }
        }
    }
}