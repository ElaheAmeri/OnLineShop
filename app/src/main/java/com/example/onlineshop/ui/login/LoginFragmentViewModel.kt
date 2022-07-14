package com.example.onlineshop.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.*
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.data.ProductRepository
import com.example.onlineshop.model.Customer
import com.example.onlineshop.model.ProductItem
import com.example.onlineshop.ui.category.ApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class ApiStatus { LOADING, DONE, ERROR }

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private val sharpRef="sharpRef"
    var name:String=""
    var lastName:String=""
    var email:String=""
    var customerId:Int=-1
    private lateinit var sharedPreferences: SharedPreferences
   val statusLivedata=MutableLiveData<Int>()
    val customerLiveData = MutableLiveData<Customer>()



    fun registerCustomer(name:String,lastName:String,email:String){

        viewModelScope.launch {
            productRepository.registerCustomer(name,lastName,email)
                .let{
                statusLivedata.postValue(it.code())
                    customerLiveData.postValue(it.body())
            }

        }
    }

    fun saveCustomerToSharP(context: Context, customer: Customer){

       sharedPreferences =context.getSharedPreferences(sharpRef,Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor =sharedPreferences.edit()
        editor.apply {
            putString("customerName",customer.firstName)
            putString("customerLastName",customer.lastName)
            putString("customerEmail",customer.email)
            putInt("customerId",customer.id)
        }.apply()
    }

    fun customerIsLogin(context: Context):Boolean{
        val sharedPreferences:
                SharedPreferences = context.getSharedPreferences(sharpRef,Context.MODE_PRIVATE)
         name = sharedPreferences.getString("customerName","").toString()
         lastName = sharedPreferences.getString("customerLastName","").toString()
         email = sharedPreferences.getString("customerEmail","").toString()
         customerId=sharedPreferences.getInt("customerId",-1)
        return (name.isBlank())

    }
    fun completeField(editText: EditText):Boolean{
        return if (editText.text.isNullOrBlank()){
            editText.error = "این فیلد خالی است"
            false
        }else{
            true
        }
    }
}