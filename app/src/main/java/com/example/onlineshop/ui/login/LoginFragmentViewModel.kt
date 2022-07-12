package com.example.onlineshop.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.ProductRepository
import com.example.onlineshop.model.Customer
import com.example.onlineshop.model.ProductItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    val listOfCustomer = MutableLiveData<Customer>()

    fun registerCustomer(name:String,lastName:String,email:String){
        viewModelScope.launch {
            productRepository.registerCustomer(name,lastName,email)
        }
    }
    }