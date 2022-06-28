package com.example.onlineshop.data

import com.example.onlineshop.model.ProductItem
import com.example.onlineshop.network.ApiService
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(val ApiService: ApiService) {
    suspend fun getListOfProducts( orderly:String ):List<ProductItem>{

            return ApiService.getListOfProducts(orderly)
    }

}