package com.droidmob.zohousers.repository

import android.content.Context
import com.droidmob.zohousers.AppController
import com.droidmob.zohousers.repository.webservice.ApiClient
import javax.inject.Inject

abstract class BaseRepository {
    @Inject lateinit var apiClient: ApiClient
    @Inject lateinit var context: Context
    init {
        AppController.appComponent.inject(this)
    }
}