package com.example.onlineshop.ui.resultofcategory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.ProductRepository
import com.example.onlineshop.model.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class ResultOfCategoryFragmentViewModel @Inject constructor(private val productRepository: ProductRepository)
    : ViewModel() {

    val listOfCategory=MutableLiveData<List<ProductItem>>()

    fun getListProductOfCategory(category:String){
        viewModelScope.launch {
            listOfCategory.postValue(productRepository.getProductsOfCategory(category))
        }
    }
}