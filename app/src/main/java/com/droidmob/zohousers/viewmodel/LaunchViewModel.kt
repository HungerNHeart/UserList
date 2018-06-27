package com.droidmob.zohousers.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.PagedList
import android.os.Bundle
import com.droidmob.zohousers.repository.UserRepository
import com.droidmob.zohousers.repository.dto.common.UserData
import com.droidmob.zohousers.view.adapter.UserListAdapter


class LaunchViewModel : BaseViewModel() {

    val userPageList: LiveData<PagedList<UserData>> by lazy {
        UserRepository().getUsers(1)
    }
    override fun onCreate(bundle: Bundle?) {

    }
}