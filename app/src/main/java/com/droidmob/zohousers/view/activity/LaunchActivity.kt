package com.droidmob.zohousers.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import com.droidmob.moviesurf_yify.adapter.decorator.VerticalSpaceItemDecoration
import com.droidmob.zohousers.R
import com.droidmob.zohousers.databinding.ActivityLaunchBinding
import com.droidmob.zohousers.view.adapter.UserListAdapter
import com.droidmob.zohousers.viewmodel.LaunchViewModel
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : BaseActivity<ActivityLaunchBinding, LaunchViewModel>() {

    private val userListAdapter = UserListAdapter()

    override fun setupLayoutRes() =
            R.layout.activity_launch

    override fun initialiseViewModel() =
            ViewModelProviders.of(this).get(LaunchViewModel::class.java)


    override fun onViewModelCreated() {
        userList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        userList.addItemDecoration(VerticalSpaceItemDecoration(16, VerticalSpaceItemDecoration.Position.BOTTOM))
        userList.adapter = userListAdapter
        viewModel.userPageList.observe(this, Observer {
            userListAdapter.submitList(it)
        })
    }


}
