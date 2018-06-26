package com.droidmob.zohousers.viewmodel

import android.content.Context
import android.os.Bundle
import com.droidmob.zohousers.repository.UserRepository

class LaunchViewModel(val context: Context) : BaseViewModel() {

    val userRepository = UserRepository(context)
    override fun onCreate(bundle: Bundle?) {
    }


}