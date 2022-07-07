package com.example.onlineshop.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.ProductRepository
import com.example.onlineshop.model.Category
import com.example.onlineshop.model.Image
import com.example.onlineshop.model.ProductItem
import com.example.onlineshop.ui.home.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class ApiStatus { LOADING, DONE, ERROR }


@HiltViewModel
class DetailProductViewModel @Inject constructor(private val productRepository: ProductRepository)
    :ViewModel() {
        private val status = MutableLiveData<ApiStatus>()
        val detailProductLiveData=MutableLiveData<ProductItem>()

        fun getDetailProduct(id:Int){
            status.value = ApiStatus.LOADING
            viewModelScope.launch {
             detailProductLiveData.postValue(productRepository.getOneProduct(id))
            }
        }
}