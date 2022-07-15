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
        val adapterPagerDetail =PagerDetailAdapter()
        binding.pagerDetailFragment.adapter =adapterPagerDetail

        viewModel.detailProductLiveData.observe(viewLifecycleOwner) {
            binding.llHome.visibility=View.VISIBLE
            binding.animationViewHome.visibility=View.GONE

            adapterPagerDetail.submitList(it.images)

            if (it != null) {
                urlList=it.images
                binding.tvDetailName.text = it.name
                binding.tvRating.text = it.averageRating
                binding.tvDetailPrice.text = it.price
                val description =RemoveHtmlTag.html2text(it.description)
                binding.tvDescription.text =description

            }
        }



        viewModel.getProductReviews(itemId)
        val rvReviewAdapter=ReviewAdapter {id->goToReviewFragment(id)}
        binding.rvDetail.adapter=rvReviewAdapter
        viewModel.detailProductLiveDataReviw.observe(viewLifecycleOwner){
            rvReviewAdapter.submitList(it)
        }

        sharedPreferences =requireActivity().getSharedPreferences(sharpRefListProductId,Context.MODE_PRIVATE)


        val editor:SharedPreferences.Editor =sharedPreferences.edit()
        editor.apply {
            putInt("productId",itemId)
        }.apply()



    }
    private fun observeProduceItem() {


    }
    private fun goToReviewFragment(reviw: Reviw){
        val  bundle = bundleOf("reviewId" to reviw.id )
        findNavController().navigate(R.id.action_detailProductFragment_to_reviewFragment,bundle)
    }
}