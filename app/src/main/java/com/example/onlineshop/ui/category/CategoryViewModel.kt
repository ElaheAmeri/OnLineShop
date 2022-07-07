package com.example.onlineshop.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.ProductRepository
import com.example.onlineshop.model.CategoryItemItem
import com.example.onlineshop.model.ProductItem
import com.example.onlineshop.ui.home.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class ApiStatus { LOADING, DONE, ERROR }

@HiltViewModel
class CategoryViewModel @Inject constructor(val repository: ProductRepository):ViewModel() {
    val status = MutableLiveData<ApiStatus>()
    val listOfCategory = MutableLiveData<List<CategoryItemItem>>()


    init {
        getlistOfCategory()
    }
    fun getlistOfCategory() {
        status.value = ApiStatus.LOADING
        viewModelScope.launch {

            listOfCategory.postValue(repository.getCategory())
        }
    }
}