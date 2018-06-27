package com.droidmob.zohousers.di

import android.content.Context
import com.droidmob.zohousers.repository.webservice.ApiClient
import com.droidmob.zohousers.util.CodeSnippet
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule(@ApplicationContext @Named("applicationContext") val context: Context) {

    @Singleton
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideCodeSnippet() = CodeSnippet(context)

    @Singleton
    @Provides
    fun provideApiClient() = ApiClient(context)

}