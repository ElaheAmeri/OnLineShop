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
    val status =MutableLiveData(ApiStatus.LOADING)
    val listOfLatestProducts = MutableLiveData<List<ProductItem>>()
    val listOfMostVisitedProducts = MutableLiveData<List<ProductItem>>()
    val listOfBestProducts = MutableLiveData<List<ProductItem>>()
    val specialProducts = MutableLiveData<ProductItem>()


    init {

        getListOfLatestProducts()
        getListOfBestProducts()
        getListOfMostVisitedProducts()
        changState()

    }
    private fun changState(){
        status.value=ApiStatus.DONE
    }



    private fun getListOfLatestProducts() {
        viewModelScope.launch {

            listOfLatestProducts.postValue(productRepository.getListOfProducts("date"))

        }

    }

    private fun getListOfMostVisitedProducts() {
        viewModelScope.launch {
            listOfMostVisitedProducts.postValue(productRepository.getListOfProducts("popularity"))
        }
    }

    private fun getListOfBestProducts() {
        viewModelScope.launch {
            listOfBestProducts.postValue(productRepository.getListOfProducts("rating"))
        }
    }


}

