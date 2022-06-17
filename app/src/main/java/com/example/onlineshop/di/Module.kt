package com.example.onlineshop.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.onlineshop.data.database.AppDatabase
import com.example.onlineshop.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object Module {


//    @Singleton
//    @Provides
//    fun provideDatabase(@ApplicationContext context: Context): AppDatabase{
//        return Room.databaseBuilder(context.applicationContext,
//        AppDatabase::class.java,"db")
//            .allowMainThreadQueries()
//            .build()
//
//    }

    @Singleton
    @Provides
    fun getApiService():ApiService{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://woocommerce.maktabsharif.ir/wp-json/wc/v3")
            .build()
        val onloneShopApiService =retrofit.create(ApiService::class.java)
        return onloneShopApiService
    }
}