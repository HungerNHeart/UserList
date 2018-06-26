package com.droidmob.zohousers.util

import android.content.Context
import android.net.NetworkInfo
import android.net.ConnectivityManager
import java.io.IOException

class CodeSnippet(val context: Context) {
    // added as an instance method to an Activity
    fun isNetworkConnectionAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo ?: return false
        val network = info.state
        return network === NetworkInfo.State.CONNECTED || network === NetworkInfo.State.CONNECTING
    }

    fun loadJSONFromAsset(fileName: String): String? {
        val json: String?
        try {
            val inputStream = context.getAssets().open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
}