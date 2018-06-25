package com.droidmob.zohousers.view.activity

import android.arch.lifecycle.ViewModelProviders
import com.droidmob.zohousers.R
import com.droidmob.zohousers.databinding.ActivityLaunchBinding
import com.droidmob.zohousers.viewmodel.LaunchViewModel

class LaunchActivity : BaseActivity<ActivityLaunchBinding, LaunchViewModel>() {


    override fun setupLayoutRes() = R.layout.activity_launch

    override fun initialiseViewModel(): LaunchViewModel {
        return ViewModelProviders.of(this).get(LaunchViewModel::class.java)
    }

    override fun onViewModelCreated() {
    }


}
