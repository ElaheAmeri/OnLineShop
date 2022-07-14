package com.example.onlineshop.ui.sreach

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.ProductRepository
import com.example.onlineshop.model.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel@Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

     val listOfProductsSearched = MutableLiveData<List<ProductItem>>()

    fun search(search:String){
        viewModelScope.launch {
            listOfProductsSearched.postValue(productRepository.search(search))

        }
    }
}