package com.example.onlineshop.network

import com.example.onlineshop.model.Category
import com.example.onlineshop.model.CategoryItemItem
import com.example.onlineshop.model.ProductItem
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/"
const val CONSUMER_SECRET ="cs_294e7de35430398f323b43c21dd1b29f67b5370b"
const val CONSUMER_KEY ="ck_63f4c52da932ddad1570283b31f3c96c4bd9fd6f"

interface ApiService {
    @GET("products")
    suspend fun getListOfProducts(
        @Query("orderby") orderly: String, //= "date"
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET
    ):List<ProductItem>

    @GET("products/categories")
    suspend fun getCategories(
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET,
    ):List<CategoryItemItem>


}