package com.droidmob.zohousers

import android.app.Application
import android.content.Context
import com.droidmob.zohousers.di.AppComponent
import com.droidmob.zohousers.di.AppModule
import com.droidmob.zohousers.di.DaggerAppComponent
import com.droidmob.zohousers.repository.webservice.ApiClient

class AppController: Application() {
    companion object {
        lateinit var appController: AppController
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appController = this
        appComponent =  DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent.inject(this)
    }
}