package com.droidmob.zohousers.view.activity

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.droidmob.zohousers.viewmodel.LaunchViewModel
import com.droidmob.zohousers.R

class LaunchActivity : AppCompatActivity() {

    val viewModel: LaunchViewModel by lazy {
        ViewModelProviders.of(this).get(LaunchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        viewModel.onCreate(intent.extras)
    }
}
