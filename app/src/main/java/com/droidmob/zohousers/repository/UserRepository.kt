package com.droidmob.zohousers.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.content.Context
import android.support.annotation.NonNull
import android.util.Log
import com.droidmob.zohousers.repository.database.ZohoDatabase
import com.droidmob.zohousers.repository.datasource.UserBoundaryCallback
import com.droidmob.zohousers.repository.dto.common.UserData
import com.droidmob.zohousers.repository.dto.response.UserResponse
import com.droidmob.zohousers.repository.webservice.ApiClient
import com.droidmob.zohousers.util.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class UserRepository(
        val context: Context) {

    val database= ZohoDatabase.create(context)
    val apiClient by lazy { ApiClient(context) }

    fun loadNewUser(){
        val option = HashMap<String, String>()
        option["page"] = 1.toString()
        apiClient.zohoApiServices().getUsers(option)
                .enqueue(object : Callback<UserResponse>{
                    override fun onFailure(call: Call<UserResponse>?, t: Throwable?) {
                        Log.d(this.TAG(), "failure: "+t.toString())
                    }

                    override fun onResponse(call: Call<UserResponse>?, response: Response<UserResponse>?) {
                        Log.d(this.TAG(), "success: "+response.toString())
                        if (response!=null && response.isSuccessful && response.body()!=null){
                            insertIntoDatabase(response.body()!!)
                        }
                    }

                })
    }

    fun getUsers(pageSize: Int): LiveData<PagedList<UserData>> {
        val boundaryCallback = UserBoundaryCallback(
                apiClient = apiClient,
                insertOperation = this::insertIntoDatabase,
                threadExecutor = Executors.newSingleThreadExecutor(),
                pageSize = 3)
        val dataSourceFactory = database.users().getUser()
        return LivePagedListBuilder(dataSourceFactory, pageSize)
            .setBoundaryCallback(boundaryCallback).build()
    }

    fun insertIntoDatabase(@NonNull userResponse: UserResponse) {
        val users = userResponse.users
        database.runInTransaction {
            database.users().insert(users)
        }
    }
}