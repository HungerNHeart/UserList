package com.droidmob.zohousers.util

import android.content.Context
import android.net.NetworkInfo
import android.net.ConnectivityManager
import java.io.IOException
import javax.inject.Singleton

@Singleton
class CodeSnippet(val context: Context) {
    // added as an instance method to an Activity
    fun isNetworkConnectionAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo ?: return false
        val network = info.state
        return network === NetworkInfo.State.CONNECTED || network === NetworkInfo.State.CONNECTING
    }
}