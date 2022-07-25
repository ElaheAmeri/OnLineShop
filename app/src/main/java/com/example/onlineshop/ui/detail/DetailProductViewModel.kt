package com.example.onlineshop.ui.detail

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.ProductRepository
import com.example.onlineshop.model.Category
import com.example.onlineshop.model.Image
import com.example.onlineshop.model.ProductItem
import com.example.onlineshop.model.Reviw
import com.example.onlineshop.ui.home.ApiStatus
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class ApiStatus { LOADING, DONE, ERROR }


@HiltViewModel
class DetailProductViewModel @Inject constructor(private val productRepository: ProductRepository)
    :ViewModel() {
        private val status = MutableLiveData<ApiStatus>()
        val detailProductLiveData=MutableLiveData<ProductItem>()
        val detailProductLiveDataReviw=MutableLiveData<List<Reviw>>()
     val sharpRefShopping="sharpRefShopping"
     lateinit var sharedPreferencesShopping: SharedPreferences

        fun getDetailProduct(id:Int){
            status.value = ApiStatus.LOADING
            viewModelScope.launch {
             detailProductLiveData.postValue(productRepository.getOneProduct(id))
            }
        }
    fun getProductReviews(productId : Int){
        viewModelScope.launch {
            detailProductLiveDataReviw.postValue(productRepository.getProductReviews(productId))
        }

    }





    fun addProductSelectedToSharedPref(listProductItemId: Int,context: Context){
        sharedPreferencesShopping=
            context.getSharedPreferences(sharpRefShopping, Context.MODE_PRIVATE)
        val editorShopping=sharedPreferencesShopping.edit()
        val gson= Gson()
        val jsonStr=gson.toJson(listProductItemId)
        editorShopping.putString("orders",jsonStr)
        editorShopping.apply()
    }
}