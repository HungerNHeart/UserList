package com.droidmob.zohousers.di

import com.droidmob.zohousers.AppController
import com.droidmob.zohousers.repository.BaseRepository
import com.droidmob.zohousers.repository.webservice.ApiClient
import com.droidmob.zohousers.viewmodel.BaseViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(injectorHelper: AppController)
    fun inject(injectorHelper: ApiClient)
    fun inject(injectorHelper: BaseViewModel)
    fun inject(baseRepository: BaseRepository)
}