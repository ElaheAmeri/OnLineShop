package com.example.onlineshop.ui.login


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.widget.EditText
import androidx.lifecycle.*
import com.example.onlineshop.data.ProductRepository
import com.example.onlineshop.model.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class ApiStatus { LOADING, DONE, ERROR }

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(private val productRepository: ProductRepository
,private val app: Application
) : AndroidViewModel(app) {
    private val sharpRef="sharpRef"
    var name:String=""
    var lastName:String=""
    var email:String=""
    var customerId:Int=-1
    private lateinit var sharedPreferences: SharedPreferences
   val statusLivedata=MutableLiveData<Int>()
    val customerLiveData = MutableLiveData<Customer>()
    init {
        customerIsLogin()
    }




    fun registerCustomer(name:String,lastName:String,email:String){

        viewModelScope.launch {
            productRepository.registerCustomer(name,lastName,email)
                .let{
                statusLivedata.postValue(it.code())
                    customerLiveData.postValue(it.body())
            }

        }
    }

    fun saveCustomerToSharP( customer: Customer){

       sharedPreferences =app.getSharedPreferences(sharpRef,Context.MODE_PRIVATE)
        val editor:SharedPreferences.Editor =sharedPreferences.edit()
        editor.apply {
            putString("customerName",customer.firstName)
            putString("customerLastName",customer.lastName)
            putString("customerEmail",customer.email)
            putInt("customerId",customer.id)
        }.apply()
    }

    fun customerIsLogin():Boolean{
        val sharedPreferences:
                SharedPreferences = app.getSharedPreferences(sharpRef,Context.MODE_PRIVATE)
         name = sharedPreferences.getString("customerName","").toString()
         lastName = sharedPreferences.getString("customerLastName","").toString()
         email = sharedPreferences.getString("customerEmail","").toString()
         customerId=sharedPreferences.getInt("customerId",-1)
        return (lastName.isEmpty())

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