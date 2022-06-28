package com.example.onlineshop.data

import com.example.onlineshop.model.ProductItem
import javax.inject.Inject


class ProductRepository @Inject constructor(val productRemoteDataSource :ProductRemoteDataSource) {

    suspend fun getListOfProducts( orderly:String ):List<ProductItem>{
        return productRemoteDataSource.getListOfProducts(orderly)
    }
}