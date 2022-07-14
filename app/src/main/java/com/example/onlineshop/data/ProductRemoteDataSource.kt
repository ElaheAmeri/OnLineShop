package com.example.onlineshop.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.onlineshop.model.*
import com.example.onlineshop.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(private val ApiService: ApiService) {
    suspend fun getListOfProducts( orderly:String ):List<ProductItem>{

            return ApiService.getListOfProducts(orderly)
    }

    suspend fun  getCategory():List<CategoryItemItem>{
        return ApiService.getCategories()
    }

    suspend fun getOneProduct(id:Int):ProductItem{
        return ApiService.getProduct(id)
    }

    suspend fun getProductsOfCategory(category:String):List<ProductItem>{
        return ApiService.getProductsOfCategory(category)
    }

    suspend fun search(search:String):List<ProductItem>{
       return ApiService.search(search)
    }

    suspend fun registerCustomer(name:String,lastName:String,email:String): Response<Customer> {
        return ApiService.registerCustomer(name,lastName,email)
    }

    suspend fun getProductReviews(id:Int):List<Reviw>{
        return ApiService.getProductReviews(id)
    }

    suspend fun deleteReview(id:Int){
        return ApiService.deleteReview(id)
    }

    suspend fun editReview(id:Int,text:String,rating:Int,name:String):Reviw{
        return ApiService.editReview(id,text,rating,name)
    }

    suspend fun getOneReview(id:Int):Reviw{
        return ApiService.getReviewById(id)
    }
}
