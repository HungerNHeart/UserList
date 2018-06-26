package com.droidmob.zohousers

import android.app.Application
import android.content.Context
import com.droidmob.zohousers.repository.webservice.ApiClient

class AppController: Application() {
    companion object {
        val appController = this
    }
}