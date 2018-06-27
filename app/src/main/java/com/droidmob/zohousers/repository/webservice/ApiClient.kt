package com.droidmob.zohousers.repository.webservice

import android.content.Context
import com.droidmob.zohousers.BuildConfig
import com.droidmob.zohousers.util.CodeSnippet
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiClient(val context: Context) {

    @Inject
    lateinit var codeSnippet:  CodeSnippet

    private val maxAge: Int = 60

    val retrofit: Retrofit

    init {

        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()

        // addItem your other interceptors …
        // addItem logging as last interceptor
        httpClient.addInterceptor(logging)
        retrofit = Retrofit.Builder()
                .client(httpClient.build())
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