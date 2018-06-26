package com.droidmob.zohousers.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.droidmob.moviesurf_yify.adapter.decorator.VerticalSpaceItemDecoration
import com.droidmob.zohousers.R
import com.droidmob.zohousers.databinding.ActivityLaunchBinding
import com.droidmob.zohousers.view.adapter.UserListAdapter
import com.droidmob.zohousers.viewmodel.LaunchViewModel
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : BaseActivity<ActivityLaunchBinding, LaunchViewModel>() {

    lateinit var userListAdapter: UserListAdapter

    override fun setupLayoutRes() = R.layout.activity_launch

    override fun initialiseViewModel(): LaunchViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = this@LaunchActivity
                return LaunchViewModel(repo) as T
            }
        })[LaunchViewModel::class.java]
    }

    override fun onViewModelCreated() {
        userListAdapter = UserListAdapter()
        userList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        userList.adapter = userListAdapter
        userList.addItemDecoration(VerticalSpaceItemDecoration(16, VerticalSpaceItemDecoration.Position.BOTTOM))
        viewModel.userRepository.getUsers(1).observe(this, Observer {
            userListAdapter.submitList(it)
        })
    }


}
