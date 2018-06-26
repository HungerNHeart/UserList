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

class ApiClient(val context: Context) {

    val codeSnippet=  CodeSnippet(context)

    private val maxAge: Int = 60

    val retrofit: Retrofit
    private val maxStale: Int = 60 * 60 * 24 * 28
    private var REWRITE_CACHE_CONTROL_INTERCEPTOR: Interceptor

    init {
        REWRITE_CACHE_CONTROL_INTERCEPTOR = Interceptor{
            chain: Interceptor.Chain? -> if(codeSnippet.isNetworkConnectionAvailable())
            chain?.proceed(chain.request())?.newBuilder()
                    ?.header("Cache-Control", "public, max-age=" + maxAge)
                    ?.build()else chain?.proceed(chain.request())?.newBuilder()
                ?.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                ?.build()
        }
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
        //setup cache
        val httpCacheDirectory = File(context.cacheDir, "responses")
        val cacheSize:Long = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)

        //add cache to the client
        httpClient.cache(cache)
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