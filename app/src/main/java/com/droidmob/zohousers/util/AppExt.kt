package com.droidmob.zohousers.util

import android.util.Log


fun Any.Log(message: String){
    Log.d(javaClass.simpleName, message)
}