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
class HomeFragmentViewModel @Inject constructor(val productRepository: ProductRepository) :
ViewModel(){
    val status = MutableLiveData<ApiStatus>()
    val list = MutableLiveData<List<ProductItem>>()

    init{
        getList()
    }
    fun getList() {
        status.value = ApiStatus.LOADING
        viewModelScope.launch {

            list.postValue(productRepository.getListOfProducts("date"))
        }
    }
}

