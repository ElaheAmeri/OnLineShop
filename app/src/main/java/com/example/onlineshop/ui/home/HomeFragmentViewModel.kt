package com.example.onlineshop.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.ProductRepository
import com.example.onlineshop.model.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class ApiStatus { LOADING, DONE, ERROR }


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val productRepository: ProductRepository) :
ViewModel() {
    private val status = MutableLiveData<ApiStatus>()
    val listOfLatestProducts = MutableLiveData<List<ProductItem>>()
    val listOfMostVisitedProducts = MutableLiveData<List<ProductItem>>()
    val listOfBestProducts = MutableLiveData<List<ProductItem>>()


    init {
        getListOfLatestProducts()
        getListOfBestProducts()
        getListOfMostVisitedProducts()
    }

    private fun getListOfLatestProducts() {
        status.value = ApiStatus.LOADING
        viewModelScope.launch {

            listOfLatestProducts.postValue(productRepository.getListOfProducts("date"))
        }
    }

    private fun getListOfMostVisitedProducts() {
        status.value = ApiStatus.LOADING
        viewModelScope.launch {
            listOfMostVisitedProducts.postValue(productRepository.getListOfProducts("popularity"))
        }
    }

    private fun getListOfBestProducts() {
        status.value = ApiStatus.LOADING
        viewModelScope.launch {
            listOfBestProducts.postValue(productRepository.getListOfProducts("rating"))
        }

    }


}

