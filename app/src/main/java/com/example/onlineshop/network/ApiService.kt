package com.example.onlineshop.network


import com.example.onlineshop.model.*
import retrofit2.Response
import retrofit2.http.*


const val BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/"
const val CONSUMER_SECRET ="cs_294e7de35430398f323b43c21dd1b29f67b5370b"
const val CONSUMER_KEY ="ck_63f4c52da932ddad1570283b31f3c96c4bd9fd6f"

interface ApiService {
    @GET("products")
    suspend fun getListOfProducts(
        @Query("orderby") orderly: String, //= "date"
        @Query("per_page") perPage: Int = 20,
        @Query("exclude") except: Int = 608,
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET
    ):List<ProductItem>

    @GET("products/categories")
    suspend fun getCategories(
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET,
    ):List<CategoryItemItem>

    @GET("products/{id}")
    suspend fun getProduct(
        @Path("id") id: Int,
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET,
    ):ProductItem

    @GET("products")
    suspend fun getProductsOfCategory(
        @Query("category") categoryId: String,
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET,
        @Query("per_page") perPage: Int = 20,
        @Query("exclude") except: Int = 608
    ):List<ProductItem>



    @GET("products")
    suspend fun search(
        @Query("search") searchWord: String,
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET,
        @Query("per_page") perPage: Int = 20,

    ):List<ProductItem>

    @FormUrlEncoded
    @POST("customers")
    suspend fun registerCustomer(

        @Field("first_name") firstName: String,
        @Field("last_name") lastName :String,
        @Field("email") email: String,
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET
    ): Response<Customer>


    @POST("orders")
    suspend fun order(
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET,
        @Body shoppingItems: Order
    ):Order

    @GET("products/reviews")
    suspend fun getProductReviews(
        @Query("product") product_id : Int,
        @Query("per_page") page: Int = 55,
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET

    ): List<Reviw>

    @POST("products/reviews")
    suspend fun createReview(
        @Query("consumer_key") consumerKey:String = CONSUMER_KEY,
        @Query("consumer_secret") consumerSecret:String = CONSUMER_SECRET,
        @Body review: Reviw
    ): Reviw

}