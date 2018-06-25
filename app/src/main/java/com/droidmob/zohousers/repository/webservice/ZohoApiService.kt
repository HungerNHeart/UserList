package com.droidmob.zohousers.repository.webservice

import com.droidmob.zohousers.repository.dto.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ZohoApiService {
    @GET("users?page=1")
    fun getUsers(@QueryMap options: Map<String, String>): Call<UserResponse>
}