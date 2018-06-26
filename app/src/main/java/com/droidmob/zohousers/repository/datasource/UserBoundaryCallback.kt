package com.droidmob.zohousers.repository.datasource

import android.arch.paging.PagedList
import android.support.annotation.MainThread
import android.util.Log

import com.droidmob.zohousers.repository.dto.common.UserData
import com.droidmob.zohousers.repository.dto.response.UserResponse
import com.droidmob.zohousers.repository.webservice.ApiClient
import com.droidmob.zohousers.util.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executor

class UserBoundaryCallback(
        val threadExecutor: Executor,
        val apiClient: ApiClient,
        val insertOperation: (UserResponse) -> Unit,
        val pageSize: Int) : PagedList.BoundaryCallback<UserData>(){

    val helper = PagingRequestHelper(threadExecutor)

    override fun onZeroItemsLoaded() {
        helper.runIfNotRunning(PagingRequestHelper.RequestType.INITIAL) {
            val options = HashMap<String, String>()
            options["page"] = 1.toString()
            apiClient.zohoApiServices().getUsers(options)
                    .enqueue(createWebserviceCallback(it))
        }
    }

    @MainThread
    override fun onItemAtEndLoaded(itemAtEnd: UserData) {
        Log.d(TAG(),"onItemAtEndLoaded: "+itemAtEnd.id)
        /*helper.runIfNotRunning(PagingRequestHelper.RequestType.AFTER) {
            apiClient.zohoApiServices().getUsers().getTopAfter(
                    subreddit = subredditName,
                    after = itemAtEnd.name,
                    limit = networkPageSize)
                    .enqueue(createWebserviceCallback(it))
        }*/
    }


    private fun createWebserviceCallback(it: PagingRequestHelper.Request.Callback)
            : Callback<UserResponse> {
        return object : Callback<UserResponse> {
            override fun onFailure(
                    call: Call<UserResponse>,
                    t: Throwable) {
                Log.d(this.TAG(), "failure: "+t.toString())
                it.recordFailure(t)
            }

            override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>) {
                Log.d(this.TAG(), "success: "+response.toString())
                insertItemsIntoDb(response, it)
            }
        }
    }

    private fun insertItemsIntoDb(
            response: Response<UserResponse>,
            it: PagingRequestHelper.Request.Callback) {
        threadExecutor.execute {
            insertOperation(response.body()!!)
            it.recordSuccess()
        }
    }

}