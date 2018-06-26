package com.droidmob.zohousers.viewmodel

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.droidmob.zohousers.repository.UserRepository
import java.util.concurrent.Executors

class LaunchViewModel(val context: Context) : BaseViewModel() {

    val userRepository = UserRepository(context)
    override fun onCreate(bundle: Bundle?) {
        Executors.newSingleThreadExecutor().execute {
            Log.d(TAG, "user->database: " + userRepository.database.users().getUsers().toString())
        }
    }
}