package com.example.onlineshop.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.ProductRepository
import com.example.onlineshop.model.Category
import com.example.onlineshop.model.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class ApiStatus { LOADING, DONE, ERROR }


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(val productRepository: ProductRepository) :
ViewModel() {
    val status = MutableLiveData<ApiStatus>()
    val listOflatestProducts = MutableLiveData<List<ProductItem>>()
    val listOfMostVisitedProducts = MutableLiveData<List<ProductItem>>()
    val listOfBestProducts = MutableLiveData<List<ProductItem>>()

    init {
        getlistOflatestProducts()
        getListOfBestProducts()
        getListOfMostVisitedProducts()
    }

    fun getlistOflatestProducts() {
        status.value = ApiStatus.LOADING
        viewModelScope.launch {

            listOflatestProducts.postValue(productRepository.getListOfProducts("date"))
        }
    }

    fun getListOfMostVisitedProducts() {
        status.value = ApiStatus.LOADING
        viewModelScope.launch {
            listOfMostVisitedProducts.postValue(productRepository.getListOfProducts("popularity"))
        }
    }

    fun getListOfBestProducts() {
        status.value = ApiStatus.LOADING
        viewModelScope.launch {
            listOfBestProducts.postValue(productRepository.getListOfProducts("rating"))
        }

    }


}

