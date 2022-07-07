package com.example.onlineshop.data

import com.example.onlineshop.model.Category
import com.example.onlineshop.model.CategoryItemItem
import com.example.onlineshop.model.ProductItem
import javax.inject.Inject


class ProductRepository @Inject constructor(private val productRemoteDataSource :ProductRemoteDataSource) {

    suspend fun getListOfProducts( orderly:String ):List<ProductItem>{
        return productRemoteDataSource.getListOfProducts(orderly)
    }

    suspend fun  getCategory():List<CategoryItemItem>{
        return productRemoteDataSource.getCategory()
    }

    suspend fun  getOneProduct(id:Int):ProductItem{
        return productRemoteDataSource.getOneProduct(id)
    }

    suspend fun getProductsOfCategory(category:String):List<ProductItem>{
        return productRemoteDataSource.getProductsOfCategory(category)
    }
}