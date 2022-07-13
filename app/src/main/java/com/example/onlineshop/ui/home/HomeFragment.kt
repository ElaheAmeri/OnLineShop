package com.example.onlineshop.ui.home

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
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
    ): View {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root


    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isOnline(requireContext())){
            observeData()
            binding.tvSearch.setOnClickListener(){
                goToSearchFragment()
            }
        }else{
            AlertDialog.Builder(requireContext())
                .setTitle("خطا")
                .setMessage("ارتباط اینترنت خود را چک کنید ")
                .setPositiveButton("باشه") { _, _ ->isOnline(requireContext()) }
                .setCancelable(false)
                .show()
        }



    }
    private fun observeData(){
        val adapterRvListOfLatestProducts = HomeRecyclerViewAdapter { id -> goToDetailPage(id) }
        binding.rvListOflatestProducts.adapter = adapterRvListOfLatestProducts
        viewModel.listOfLatestProducts.observe(viewLifecycleOwner) {
            adapterRvListOfLatestProducts.submitList(it)
            binding.llHome.visibility=View.VISIBLE
            binding.animationViewHome.visibility=View.GONE
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

    }
    private fun goToDetailPage(productItem: ProductItem) {

        val bundle = bundleOf("ProductId" to productItem.id.toInt())
        findNavController().navigate(R.id.action_homeFragment_to_detailProductFragment, bundle)
    }

    private fun goToSearchFragment(){
        val  bundle = bundleOf("wordSearched" to binding.tvSearch.text)
        findNavController().navigate(R.id.action_homeFragment_to_searchFragment,bundle)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }
}