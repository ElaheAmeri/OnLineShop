package com.example.onlineshop.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.onlineshop.model.Category
import com.example.onlineshop.model.CategoryItemItem
import com.example.onlineshop.model.ProductItem
import com.example.onlineshop.network.ApiService
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(val ApiService: ApiService) {
    suspend fun getListOfProducts( orderly:String ):List<ProductItem>{

            return ApiService.getListOfProducts(orderly)
    }

    suspend fun  getCategory():List<CategoryItemItem>{
        return ApiService.getCategories()
    }

    suspend fun getOneProduct(id:Int):ProductItem{
        return ApiService.getProduct(id)
    }

}
