package com.droidmob.zohousers.repository.webservice

import com.droidmob.zohousers.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiClient {
    val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(
                        MoshiConverterFactory
                                .create(
                                        Moshi
                                                .Builder()
                                                .add(KotlinJsonAdapterFactory())
                                                .build()))
                .build()
    }

    fun zohoApiServices() = retrofit.create(ZohoApiService::class.java)
}