package com.example.onlineshop.data

import com.example.onlineshop.model.*
import retrofit2.Response
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

    suspend fun search(search:String):List<ProductItem>{
        return productRemoteDataSource.search(search)
    }

    suspend fun registerCustomer(name:String,lastName:String,email:String): Response<Customer> {
        return productRemoteDataSource.registerCustomer(name,lastName,email)
    }

    suspend fun getProductReviews(id:Int):List<Reviw>{
        return productRemoteDataSource.getProductReviews(id)
    }
}